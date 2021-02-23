package com.carlos.me.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String host = "localhost:3306/supermercado";
    private static final String USUARIO = "root";
    private static final String CLAVE = "123asd"+ "&useSSL=false";
    private static final String CONEXION = "jdbc:mysql://" + host + "?user=" + USUARIO + "&password=" + CLAVE + "&useSSL=false";

    public Connection conectar() {
        Connection conexion = null;
        
        try {            
        	
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(CONEXION);
            System.out.println("Conexion OK");

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error en la conexi?n");
            e.printStackTrace();
        }
        
        return conexion;
    }

    public static void main(String[] args) {

    }
}
