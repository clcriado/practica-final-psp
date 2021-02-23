package com.carlos.me;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.carlos.me.Conexion.Conexion;
import com.carlos.me.Modelo.Empleado;

public class Caja1 {

	public static void main(String[] args) {

		String Host = "localhost"; // IP del Servidor al que nos vamos a conectar.
		int Puerto = 6000; // Puerto remoto.

		// Declaramos los flujos de entrada y salida.
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;

		Socket Cliente = null; // Creamos la conexion.
		Scanner sc = new Scanner(System.in); // Creamos la entrada de datos.

		try {
			System.out.println("MODO ACTIVADO: CAJA 1.");
			System.out.println("Programa Caja iniciado, esperando respuesta...\n");

			Cliente = new Socket(Host, Puerto); // Le damos los valores a la conexion.

			// Creamos el flujo de entrada y salida del servidor.
			flujoSalida = new DataOutputStream(Cliente.getOutputStream());
			flujoEntrada = new DataInputStream(Cliente.getInputStream());

			System.out.println("Conexion establecida, introduce tu ID de usuario.\n");
			Connection con = (new Conexion().conectar());
			Statement stm = null;
			ResultSet rs = null;

			String id = sc.nextLine(); // Obtenemos el Id para iniciar sesion.
			flujoSalida.writeUTF(id); // Enviamos el ID al servidor para comprobar si existe el empleado.
			
			while(true) {
				String[] empleado = flujoEntrada.readUTF().split(";");
				Empleado e = new Empleado(empleado[0],empleado[1],empleado[2],empleado[3]);
				
				if (e.getId() != null) {
					String mensaje = "La Caja 1 se ha conectado al servidor.";
					flujoSalida.writeUTF(mensaje);
					while (true) {
						System.out.println("Empleado: " + e.getNombre());
						System.out.println("Aplicacion Caja iniciada, elija una opcion.");
						System.out.println("1.- Cobrar Compra");
						System.out.println("2.- Obtener la caja del día");
						System.out.println("3.- Salir");

						String opcion = sc.nextLine(); // Obtenemos la opcion elegida.

						// SI LA OPCION ES 1 ENVIAMOS UN MENSAJE.
						if (opcion.equals("1")) {
							opcion = null;
							System.out.println("ARTÍCULOS DE LOS BUENOS:");
							System.out.println("1. Disco duro");
							System.out.println("2. USB");
							System.out.println("3. Monitor");
							System.out.println("4. Ratón \n");
							System.out.println("Seleccione el artículo que desea:");

							Integer articulo = sc.nextInt(); // Obtenemos la opcion elegida.

							System.out.println("\n¿Cuántas unidades?");

							Integer unidades = sc.nextInt(); // Obtenemos la opcion elegida.

							String cobro = "Cobro;" + articulo + ";" + unidades;

							flujoSalida.writeUTF(cobro);
							System.out.println(cobro);
						}

						// SI LA OPCION ES 2 ESPERAMOS A RECIBIR UN MENSAJE.
						else if (opcion.equals("2")) {
							opcion = null;
							
						}

						// SI LA OPCION ES 3 CERRAMOS EL PROGRAMA.
						else if (opcion.equals("3")) {
							opcion = null;
							System.out.println("Conexion finalizada, muchas gracias.\n");
							System.exit(0);

							// SI NO ES NINGUNA DE LAS OTRAS OPCIONES DA ERROR.
						} else {
							System.out.println("Entrada erronea, vuelve a intentarlo.\n");
						}
					}
				} else {
					System.out.println("El empleado introducido no existe.");
				}
			}

		} catch (Exception e) {
			// System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
			e.printStackTrace();
			// CERRAR STREAMS Y SOCKETS

		} finally {
			// FINALMENTE CERRAMOS TODAS LAS CONEXIONES EXISTENTES.
			try {
				String mensaje = "La Caja 1 se ha desconectado al servidor.";
				flujoEntrada.close();
				flujoSalida.close();
				Cliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}