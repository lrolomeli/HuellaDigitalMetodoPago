package com.mypay.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private Connection conn;
	
	public Database() {
		
	}
	
	public void connect() throws SQLException {
        try {
        	String dbURL = "jdbc:sqlserver://DESKTOP-RAH6KA8\\SQLEXPRESS:1433;databaseName=MyPayDB;user=servidorMyPay;password=payme123";
        	conn = DriverManager.getConnection(dbURL);
        	if (conn != null) {
        	    System.out.println("Connected");
        	    DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
        	}
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	public void disconnect() {
		try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
/*
	public int registerUser(FingerPrint huella) throws SQLException {
		final String insertUser = "insert into Usuario(huella, saldo) OUTPUT INSERTED.[idUsuario] values(?,?)";
		PreparedStatement iu=null;
		int userId = -1;
		
        iu = conn.prepareStatement(insertUser); 
        iu.setBytes(1, huella.getFingerPrint());
        iu.setFloat(2, 100);
        
        ResultSet rs = iu.executeQuery();
        
        if(rs.next()) {
        	userId = rs.getInt("idUsuario");
        }
        iu.close();
	        
		return userId;
	}
*/
	
	public int registerUser(User user) throws SQLException {
		final String insertUser = "insert into Usuario(huella, saldo, nombre, apellido, fechaNac, genero, usuario, contrasena) OUTPUT INSERTED.[idUsuario] values(?,?,?,?,?,?,?,?)";
		PreparedStatement iu=null;
		int userId = -1;
		
        iu = conn.prepareStatement(insertUser);
        iu.setBytes(1, user.getFingerPrint().getFPArray());
        iu.setFloat(2, 0);
        iu.setString(3, user.getFirstName());
        iu.setString(4, user.getLastName());
        iu.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
        iu.setString(6, user.getGender().toString());
        iu.setString(7, user.getUserName());
        iu.setString(8, user.getPassword());        
        
        ResultSet rs = iu.executeQuery();
        
        if(rs.next()) {
        	userId = rs.getInt("idUsuario");
        }
        iu.close();
	        
		return userId;
	}
	
	
}
