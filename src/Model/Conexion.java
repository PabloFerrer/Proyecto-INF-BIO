package Model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {

	private static String BBDDName = "Proyecto2.1.db";
	private static Connection c = null;
	private static Statement stmt = null;

	public static boolean sentenciaSQL(String sql) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		return true;
	}
	static public void InsertarNuevoECG(ECG ecg) {
		try {
//			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
//
//			c.setAutoCommit(false);
		    
		    String sql="insert into ECG(fecha,leido,puntos,puntosegundo,comentarioTecnico,dniTecnico,dniPaciente) values('"+ecg.getFecha()+"',"+Constantes.NO_LEIDO+",'"+ecg.getPuntos()+"',"+ecg.getPuntosporsec()+",'"+ecg.getComentarios()+"',"+ecg.getDniTec()+","+ecg.getDniPac()+");";
		    sentenciaSQL(sql);
//		    PreparedStatement pst;
//		    pst = c.prepareStatement(sql);
//		    pst.setInt(1, ecg.getFecha());
//		    pst.setInt(2, Constantes.NO_LEIDO);
//		    pst.setString(3, ecg.getPuntos());
//		    pst.setInt(4, ecg.getPuntosporsec());
//		    pst.setString(5, ecg.getComentarios());
//		    pst.setInt(6, ecg.getDniTec());
//		    pst.setInt(7, ecg.getDniPac());
//		    
//		    pst.executeUpdate();
//		    pst.close();
//			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage()+" "+e.getCause() );
		}
	}
	
	static public Vector<Usuario> consultarUsuarios(){
		Vector<Usuario> users=new Vector<Usuario>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario;");
			while (rs.next()) {
				String nick = rs.getString("nick");
				int dni=rs.getInt("dni");
				String rol="";
				switch(rs.getInt("rol")){
				case Constantes.ADMINISTRADOR:
					rol="admin";
					break;
				case Constantes.MEDICO:
					rol="medico";
					break;
				case Constantes.TECNICO:
					rol="tecnico";
					break;
			}
				
				users.add(new Usuario(nick,rol,dni));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return users;
	}
	//public Paciente(String id,String nombre,String apellido,String dni)
	static public ArrayList<Paciente> consultaPacTec() {
		ArrayList<Paciente> pac=new ArrayList<Paciente>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Paciente.dni,Paciente.nombre,Paciente.apellido,Paciente.Ubicacion FROM Paciente;");
			while (rs.next()) {
				int dni = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String ape=rs.getString("apellido");
				String ubicacion=rs.getString("Ubicacion");
				
				pac.add(new Paciente(nombre,ape,dni+"",ubicacion));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return pac;
	}
	
	static public Usuario consultaLogin(String nick, String pass) {
		Usuario a=new Usuario(null,null,null,0);
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO where nick like '"+nick+"';");
			if (rs.next()) {
				String nickname = rs.getString("nick");
				String password = rs.getString("contrasena");
				String rol="";
				int dni=rs.getInt("dni");
				switch(rs.getInt("rol")){
					case Constantes.ADMINISTRADOR:
						rol="admin";
						break;
					case Constantes.MEDICO:
						rol="medico";
						break;
					case Constantes.TECNICO:
						rol="tecnico";
						break;
				}
				a=new Usuario(nickname,rol,password,dni);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println("Consulta terminada, ingreso de usuario " + a.getUser());
		return a;
	}

}
