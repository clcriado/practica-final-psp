package com.carlos.me;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.carlos.me.Conexion.Conexion;
import com.carlos.me.Modelo.Empleado;
import com.carlos.me.Modelo.Producto;

public class Servidor {
	public static void main(String[] arg) {
		boolean conexion = true;
		ServerSocket servidor = null;
		Socket clienteConectado = null;
		InputStream entrada = null;
		OutputStream salida = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;
		Connection con = (new Conexion().conectar());
		Statement stm = null;
		ResultSet rs = null;

		try {
			int numeroPuerto = 6000;// Puerto
			servidor = new ServerSocket(numeroPuerto);
			System.out.println("MODO ACTIVADO: SERVIDOR.");
			System.out.println("Programa iniciado, esperando respuesta...\n");
			clienteConectado = servidor.accept();
			System.out.println("Conexion establecida.\n");

			// CREO FLUJO DE ENTRADA DEL CLIENTE
			entrada = clienteConectado.getInputStream();
			flujoEntrada = new DataInputStream(entrada);

			// CREO FLUJO DE SALIDA AL CLIENTEs
			salida = clienteConectado.getOutputStream();
			flujoSalida = new DataOutputStream(salida);



			while (true) {

				String mensajeRecibido = flujoEntrada.readUTF(); // Leemos el mensaje que nos ha llegado.

				// Si el mensaje contiene Login, entonces realizamos una comprobación de logeo.
				if (mensajeRecibido.contains("Login")) {
					String[] usuario = mensajeRecibido.split(";");
					String query = "SELECT * FROM empleado WHERE ID_Empleado = " + usuario[1];

					stm = con.createStatement();
					rs = stm.executeQuery(query);

					Empleado e = new Empleado();

					while (rs.next()) {
						e.setId(rs.getString("ID_Empleado"));
						e.setNombre(rs.getString("Nombre"));
						e.setFecha_contratacion(rs.getString("Fecha_Contratacion"));
						e.setUltima_Sesion(rs.getString("Ultima_Sesion"));

						System.out.println("Una caja ha iniciado sesión como: " + e.toString());
						flujoSalida.writeUTF(e.toString());

					}
				} else if (mensajeRecibido.contains("Cobro")) {
					String[] producto = mensajeRecibido.split(";");

					String query = "SELECT * FROM producto WHERE ID_Producto = " + producto[1];
					stm = con.createStatement();
					rs = stm.executeQuery(query);

					Producto p = new Producto();

					Integer numProducto = Integer.parseInt(producto[1]);
					Integer restarUnidades = Integer.parseInt(producto[2]);

					while (rs.next()) {
						p.setID_Producto(rs.getInt("ID_Producto"));
						p.setNombre_Producto(rs.getString("Nombre_Producto"));
						p.setPrecio_Venta(rs.getInt("Precio_Venta"));
						p.setPrecio_Proveedor(rs.getInt("Precio_Proveedor"));
						p.setCantidad_Stock(rs.getInt("Cantidad_Stock"));
						
						if (p.getCantidad_Stock() != 0 && p.getCantidad_Stock() >= restarUnidades) {
							Integer resta = p.getCantidad_Stock() - restarUnidades;
							String query2 = "UPDATE producto SET Cantidad_Stock = " + resta + " WHERE ID_Producto = 2";
							stm.executeUpdate(query2);
							System.out.println("Se ha restado el stock al producto.");
							for(int i = 0;i<=restarUnidades;i++) {
								System.out.println(java.time.LocalDate.now());
								String query3 = "INSERT INTO compra VALUES ('"+numProducto+"','"+java.time.LocalDate.now()+"')";
								stm.executeUpdate(query3);
								System.out.println("Se ha añadido a la base de datos las compras.");
							}
							
							if(resta == 0) {
								try {
									enviarConGMail("LaQueSeHaLiado@SOS.com","Prueba","Esto es una prueba");
								} catch(NoClassDefFoundError e) {
									System.out.println("No se ha podido enviar el correo, pero se ha realizado la operacion.");
								}
							}

						} else {
							System.out.println("No se ha podido realizar el encargo porque no hay stock.");
						}
					}
				}
			}
		}
		// SI NO ES NINGUNA DE LAS OTRAS OPCIONES DA ERROR.
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// FINALMENTE CERRAMOS TODAS LAS CONEXIONES EXISTENTES.
			try {
				con.close();
				stm.close();
				rs.close();
				entrada.close();
				flujoEntrada.close();
				salida.close();
				flujoSalida.close();
				clienteConectado.close();
				servidor.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
			}
		}
	} private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "tic3agg@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "Passswrd";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Passswrd");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
}