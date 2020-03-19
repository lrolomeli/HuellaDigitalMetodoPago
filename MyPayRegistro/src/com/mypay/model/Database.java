package com.mypay.model;

import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	
	private ArrayList<User> users;
	private Connection conn;
	
	public Database() {
		users = new ArrayList<User>();
	}
	
	public void connect() throws Exception {
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
	
	public void registerUser(FingerPrint huella) throws SQLException {
		final String insertUser = "insert into Usuario(huella, saldo) values(?,?)";
		PreparedStatement iu=null;
		
    	try {
            iu = conn.prepareStatement(insertUser);
            
            iu.setBytes(1, huella.getFingerPrint());
            iu.setFloat(2, 100);
            iu.executeUpdate();	

    		System.out.println("transaccion exitosa");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(iu!=null) {
				iu.close();
			}

		}
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
}
