package com.mypay.model;

import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	
	private ArrayList<User> users;
	private Connection conn = null;
	
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
	
	public String login(int account, String password) throws SQLException {
		String nombre = null;
		String apellido = null;
		final String getLogin = "select nombre, apellido from Usuario where idUsuario = ? AND contrasena = ?";
		PreparedStatement login=null;
		
		
		login = conn.prepareStatement(getLogin);
		login.setInt(1, account);
		login.setString(2, password);
		
		ResultSet rs = login.executeQuery();
		
		if(rs.next()) {
			nombre = rs.getString("nombre");
			apellido = rs.getString("apellido");
			return nombre+" "+apellido;
		}
		else {
			return null;
		}
		
	}
	
	public boolean transfer(float saldoSrc, float saldoDest, float cantidad, int idUsuario, int cuentaDestino) throws SQLException {
		boolean bool = false;
		final String updateUser = "update Usuario set saldo = ? where idUsuario = ?";
		final String insertTran = "insert into Movimiento(registro, tipo, cantidad, idUsuario_fk, operacion) values(CURRENT_TIMESTAMP,?,?,?,?)";
		PreparedStatement uu=null, it=null;
		float nuevoSaldoSrc = saldoSrc-cantidad;
		float nuevoSaldoDest = saldoDest+cantidad;

    	conn.setAutoCommit(false);
    	try {
            uu = conn.prepareStatement(updateUser);
            
            uu.setFloat(1, nuevoSaldoSrc);
            uu.setInt(2, idUsuario);
            uu.executeUpdate();
            
            uu.setFloat(1, nuevoSaldoDest);
            uu.setInt(2, cuentaDestino);
            uu.executeUpdate();
            
    		it = conn.prepareStatement(insertTran);
    		it.setBoolean(1, false);
    		it.setFloat(2, cantidad);
    		it.setInt(3, idUsuario);
    		it.setString(4, "Transferencia");
    		it.execute();

    		it.setBoolean(1, true);
    		it.setFloat(2, cantidad);
    		it.setInt(3, cuentaDestino);
    		it.setString(4, "Transferencia");
    		it.execute();
    		
    		conn.commit();
    		bool = true;
		}catch (Exception e) {
			conn.rollback();
		}finally {
			if(uu!=null) {
				uu.close();
			}
			if(it!=null) {
				it.close();
			}
			
		}
		
		
		
		return bool;
	}
	
	public Float getSaldo(int idUsuario) throws SQLException {
		Float f = null;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select saldo from Usuario where idUsuario ="+idUsuario);
        if(rs.next())
        {
        	f = rs.getFloat("saldo");
        }
        	st.close();
        	return f;
	}
	
	public byte[] getFingerPrint(int idUsuario) throws SQLException {
		byte[] fp = null;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select huella from Usuario where idUsuario ="+idUsuario);
        if(rs.next())
        {
        	fp = rs.getBytes("huella");
        }
        st.close();
        return fp;
		
	}
	
	public boolean transaction(int idUsuario, float saldo, float cantidad, boolean tipo) throws SQLException {
		final String updateUser = "update Usuario set saldo = ? where idUsuario = ?";
		final String insertTran = "insert into Movimiento(registro, tipo, cantidad, idUsuario_fk) values(CURRENT_TIMESTAMP,?,?,?)";
		PreparedStatement uu=null, it=null;
		float nuevoSaldo = (tipo) ? saldo+cantidad : saldo-cantidad;
		boolean bool = false;
    	conn.setAutoCommit(false);
    	try {
            uu = conn.prepareStatement(updateUser);
            
            uu.setFloat(1, nuevoSaldo);
            uu.setInt(2, idUsuario);
            uu.executeUpdate();	
            
    		it = conn.prepareStatement(insertTran);
    		it.setBoolean(1, tipo);
    		it.setFloat(2, cantidad);
    		it.setInt(3, idUsuario);
    		it.execute();
    		conn.commit();
    		bool = true;
		}catch (Exception e) {
			conn.rollback();
		}finally {
			if(uu!=null) {
				uu.close();
			}
			if(it!=null) {
				it.close();
			}
			
		}
    	return bool;
        //conn.setAutoCommit(false);
		//conn.commit();
        //String query = "insert into Usuario(huella, saldo) values (?, ?)";
        //PreparedStatement pst = conn.prepareStatement(query);
        //pst.setBytes(1, huella);
        //pst.setFloat(2, saldo);
        //pst.execute();
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
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers(){
		return users;
	}
	
}
