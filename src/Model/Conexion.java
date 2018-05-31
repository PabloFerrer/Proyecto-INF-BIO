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

	private static String BBDDName = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_heartlight\";";
	private static String user = "pi2_heartlight";
	private static String pass = "pepino_fresco";
	public static Connection c = null;
	private static Statement stmt = null;

	public static boolean sentenciaSQL(String sql) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
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
//			Class.forName("org.mariadb.jdbc.Driver");
//			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_heartlight", "pi2_heartlight", "pepino_fresco"+BBDDName);
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
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
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
	
	public static Medico consultaMed(Usuario us){
		Medico m= new Medico(us,0,0);
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select Medico.nColegiado,Medico.nTelefono "
					+ "from Medico" + 
					" where Medico.dni = " + us.getDni() + ";");
			if (rs.next()) {
				int nC = rs.getInt("nColegiado");
				int nT = rs.getInt("nTelefono");
				
				m = new Medico(us,nC,nT);
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return m;
		
	}
	
	public static Administrador obtenerAdmin(Usuario us){
		Administrador a = new Administrador(us,0);
		
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_heartlight", "pi2_heartlight", "pepino_fresco");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select Administrador.vencimientoClave "
					+ "from Administrador"
					+ " where Administrador.dni = " + us.getDni() + ";");
			if(rs.next()){
				int vc = rs.getInt("vencimientoClave");
				
				a = new Administrador(us,vc);
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );	
		}
		return a;
	}

	public static Tecnico obtenerTecnico(Usuario us){
		Tecnico t = new Tecnico(us,0);
		
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_heartlight", "pi2_heartlight", "pepino_fresco");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select Tecnico.turno "
					+ "from tecnico"
					+ " where tecnico.dni = " + us.getDni() + ";");
			if(rs.next()){
				int tur = rs.getInt("turno");
				
				t = new Tecnico(us,tur);
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );	
		}
		return t;
	}
	
	
	static public ArrayList<Paciente> consultaPacMed(Medico m) {
		ArrayList<Paciente> pac=new ArrayList<Paciente>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select Paciente.dni,Paciente.nSS,Paciente.apellido,Paciente.nombre,Paciente.ubicacion\r\n" + 
					"from Paciente\r\n" + 
					"join medicoPaciente on Paciente.dni = medicoPaciente.dniPaciente\r\n" + 
					"join Medico on Medico.dni = medicoPaciente.dniMedico"
					+ " where Medico.dni = "+m.getDni()+" ;");
			while (rs.next()) {
				int dni = rs.getInt("dni");
				System.out.println(dni);
				int nss = rs.getInt("nss");
				String ape=rs.getString("apellido");
				String nombre = rs.getString("nombre");
				String ubicacion=rs.getString("Ubicacion");
				
				pac.add(new Paciente(nombre,ape,dni+Utilidades.letraDNI(dni),ubicacion));//añadir nss, cambiar en el constructor!
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return pac;
	}
	//public Paciente(String id,String nombre,String apellido,String dni)
	static public ArrayList<Paciente> consultaPacTec() {
		ArrayList<Paciente> pac=new ArrayList<Paciente>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Paciente.dni,Paciente.nombre,Paciente.apellido,Paciente.Ubicacion FROM Paciente;");
			while (rs.next()) {
				int dni = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String ape=rs.getString("apellido");
				String ubicacion=rs.getString("Ubicacion");
				
				pac.add(new Paciente(nombre,ape,dni+Utilidades.letraDNI(dni),ubicacion));
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
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO where nick like '"+nick+"';");
			if (rs.next()) {
				String nickname = rs.getString("nick");
				String password = rs.getString("contrasena");
				String rol="";
				String nombre = rs.getString("nombre");
				String ape=rs.getString("apellido");
				String ubicacion=rs.getString("Ubicacion");
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
				a=new Usuario(nombre,ape,nickname,rol,password,dni,ubicacion);
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
	static public Vector<Mensaje> consultarMensajes(Paciente pac){
		Vector<Mensaje> men=new Vector<Mensaje>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Mensaje where dniPaciente="+pac.getDni().substring(0, pac.getDni().length()-1)+" order by fecha desc;");
			while (rs.next()) {
				men.add(new Mensaje(rs.getInt("id"),rs.getInt("DniUsuario"),rs.getInt("dniPaciente"),rs.getInt("leido"),rs.getString("datos"),rs.getInt("fecha"),rs.getString("asunto")));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return men;
	}
	
	static public Vector<ECG> consultaECG(Paciente pac) {
		Vector<ECG> ecg=new Vector<ECG>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ECG where dniPaciente="+pac.getDni().substring(0, pac.getDni().length()-1)+" order by fecha desc;");
			while (rs.next()) {
				ecg.add(new ECG(rs.getInt("id"),rs.getInt("fecha"),rs.getInt("fechadediagnostico"),(rs.getInt("leido")==Constantes.LEIDO),rs.getInt("dnimedico"),rs.getInt("dniTecnico"),rs.getInt("dniPaciente")
						,rs.getString("comentarioTecnico"),rs.getInt("pulsaciones"),rs.getString("diagnostico"),rs.getInt("puntoSegundo")
						,rs.getString("puntos")
						));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return ecg;
	}
	
	static public String getUserName(int dni) {
		String name="";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre,apellido FROM usuario where dni="+dni+";");
			if (rs.next()) {
				name=rs.getString("nombre")+" "+rs.getString("apellido");
				System.out.println("LECTOR:" +name);
				
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return name;
	}
}
