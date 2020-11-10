package com.emergentes.utiles;
import java.sql.*;

public class ConexionBD4 {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_calificaciones";
    static String usuario = "root";
    static String password = "";
    
    protected Connection conn = null;
    
    public ConexionBD4(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null){
                System.out.println("Conexion exitosa");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Error en driver" + e.getMessage());
        }catch(SQLException e){
            System.out.println("Error al conectar" + e.getMessage());
        }    
    }
    
    public Connection conectar4(){
        return conn;
    }
    
    public void desconectar4(){
        System.out.println("Cerrando la BD:" + conn);
        try{
            conn.close();
        }catch(SQLException ex){
            System.out.println("Error al cerrar la BD: " + ex.getMessage());
        }
    }
}

