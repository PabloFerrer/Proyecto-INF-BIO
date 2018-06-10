package Model;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Control.ControladorMedico;
import View.Formulario;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
	public static String BBDDName = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_heartlight";
//	public static String BBDDName = "jdbc:mariadb://127.0.0.1:3306/p2_heartlight";
	public static String user = "pi2_heartlight";
	public static String pass = "pepino_fresco";
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
	
	
	
	public static void crearPaciente(Formulario formulario,Medico med,ControladorMedico cm){
		String numDni = formulario.getDni().getText();
		int numm=Integer.parseInt(numDni);
		
		String numSS = formulario.getSs().getText();
		long numm2= Long.parseLong(numSS);
		
		String apellidos = formulario.getApellido1().getText()+" "+formulario.getApellido2().getText();
		
		String nombre = formulario.getNombre().getText();
		
		String sqql = "insert into Paciente (dni,nSS,apellido,nombre,ubicacion,genero,foto) values (?,?,?,?,?,?,?)";
		PreparedStatement pst; 
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			Conexion.c = DriverManager.getConnection(Conexion.BBDDName, Conexion.user, Conexion.pass);
			pst = Conexion.c.prepareStatement(sqql);
			pst.setInt(1,numm);
			pst.setLong(2,numm2);
			pst.setString(3,apellidos);
			pst.setString(4, formulario.getNombre().getText());
			pst.setString(5,formulario.getLugar().getText());
			pst.setInt(6, ((formulario.getRdbtnMasculino().isSelected())? Constantes.MASCULINO:Constantes.FEMENINO));
			pst.setBinaryStream(7, cm.getFis() );
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		
		String sentencia=" insert into medicoPaciente(dnimedico,dnipaciente) values ("+med.getDni()+","+formulario.getDni().getText()+");";
		Conexion.sentenciaSQL(sentencia);
	
		try {
			med.aniadirpaciente(new Paciente(formulario.getNombre().getText(),formulario.getApellido1().getText()+" "+formulario.getApellido2().getText(),formulario.getDni().getText()+Utilidades.letraDNI(Integer.parseInt(formulario.getDni().getText())),Long.parseLong(formulario.getSs().getText()),formulario.getLugar().getText(),ImageIO.read(cm.getImagen()),new Vector<ECG>()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//escribirPaciente(formulario.getNombre().getText(), formulario.getApellido1().getText(),formulario.getApellido2().getText(), formulario.getDni().getText(), formulario.getSs().getText(), formulario.getLugar().getText(),formulario.getDireccion().getText() , formulario.getUrgencia().getSelectedItem().toString());
		
		formulario.dispose();
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
				String nickname = rs.getString("nick");
				int dni=rs.getInt("dni");
				String rol="";
				String nombre = rs.getString("nombre");
				String ape=rs.getString("apellido");
				String password = rs.getString("contrasena");
				String ubicacion=rs.getString("Ubicacion");
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
				
				users.add(new Usuario(nombre,ape,nickname,rol,password,dni,ubicacion));
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
			c = DriverManager.getConnection(BBDDName, user, pass);
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
			c = DriverManager.getConnection(BBDDName, user, pass);
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
		Image foto = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select Paciente.dni,Paciente.nSS,Paciente.apellido,Paciente.nombre,Paciente.ubicacion,Paciente.genero,Paciente.foto\r\n" + 
					"from Paciente\r\n" + 
					"join medicoPaciente on Paciente.dni = medicoPaciente.dniPaciente\r\n" + 
					"join Medico on Medico.dni = medicoPaciente.dniMedico"
					+ " where Medico.dni = "+m.getDni()+" ;");
			while (rs.next()) {
				int dni = rs.getInt("dni");
				System.out.println(dni);
				long nss = rs.getLong("nss");
				String ape=rs.getString("apellido");
				String nombre = rs.getString("nombre");
				String ubicacion=rs.getString("Ubicacion");
				int genero=rs.getInt("genero");
				InputStream is = rs.getBinaryStream("foto");
				
				if(is!=null){
					foto = ImageIO.read(is);	
				}else if(genero == Constantes.MASCULINO){
					foto = ImageIO.read(new File("Resource/Imagenes/Hombre.png"));
				}else{
					foto = ImageIO.read(new File("Resource/Imagenes/Mujer.png"));
				}
				
				
				
				pac.add(new Paciente(nombre,ape,dni+Utilidades.letraDNI(dni),ubicacion,genero,nss,foto));//añadir nss, cambiar en el constructor!
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
		Image foto = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Paciente.dni,Paciente.nSS,Paciente.nombre,Paciente.apellido,Paciente.Ubicacion,genero,foto FROM Paciente;");
			while (rs.next()) {
				int dni = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String ape=rs.getString("apellido");
				String ubicacion=rs.getString("Ubicacion");
				int genero=rs.getInt("genero");
				long nss = rs.getLong("nss");
				InputStream is = rs.getBinaryStream("foto");
				
				if(is!=null){
					foto = ImageIO.read(is);	
				}else if(genero == Constantes.MASCULINO){
					foto = ImageIO.read(new File("Resource/Imagenes/Hombre.png"));
				}else{
					foto = ImageIO.read(new File("Resource/Imagenes/Mujer.png"));
				}
				
				
				pac.add(new Paciente(nombre,ape,dni+Utilidades.letraDNI(dni),ubicacion,genero,nss,foto));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return pac;
	}
	
	static public Usuario consultaLogin(String nick, String passw) {
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
			ResultSet rs = stmt.executeQuery("SELECT Mensaje.id,Mensaje.DniUsuario,Mensaje.dniPaciente,Mensaje.leido,Mensaje.datos,Mensaje.fecha,Mensaje.asunto,Usuario.Nombre,Usuario.apellido,Usuario.rol FROM Mensaje join Usuario on Usuario.dni=Mensaje.DniUsuario where dniPaciente="+pac.getDni().substring(0, pac.getDni().length()-1)+" order by fecha desc;");
			while (rs.next()) {
				men.add(new Mensaje(rs.getInt("Mensaje.id"),rs.getInt("Mensaje.DniUsuario"),rs.getInt("Mensaje.dniPaciente"),rs.getInt("Mensaje.leido"),rs.getString("Mensaje.datos"),rs.getInt("Mensaje.fecha"),rs.getString("Mensaje.asunto"),rs.getString("Usuario.Nombre")+" "+rs.getString("Usuario.apellido"),rs.getInt("Usuario.rol")));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM ECG where dniPaciente="+pac.getDni().substring(0, pac.getDni().length()-1)+" order by id desc;");
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
	
	static public Object[] getAgenda(int fecha,int dni) {
		Object[] aux=new Object[3];
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String a="SELECT data,fecha,dniMedico FROM agenda where dniMedico="+dni+" AND fecha="+fecha+";";
			ResultSet rs = stmt.executeQuery(a);
			if (rs.next()) {
				
				aux[0]=rs.getString("data");
				aux[1]=rs.getInt("fecha");
				aux[2]=rs.getInt("dniMedico");
				System.out.println("LEIDO"+aux[1]);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return aux;
	}
	static public boolean dayHaveSomething(int fecha,int dni) {
		boolean hay=false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection(BBDDName, user, pass);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fecha FROM agenda where dniMedico="+dni+" AND fecha="+fecha+";");
			if (rs.next()) {
				hay=true;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return hay;
	}
	
	static public Vector<Integer> dayofMonthWithSomething(int year, int month,int dni) {
		Vector<Integer> hay=new Vector<Integer>();
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection(BBDDName, user, pass);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT fecha FROM agenda where dniMedico="+dni+" AND fecha like '"+year+""+(month < 10 ? "0" : "") +""+ month+"%' order by fecha;");
		while (rs.next()) {
			hay.add(rs.getInt("fecha"));
		}
		rs.close();
		stmt.close();
		c.close();
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
	return hay;
}
	
	public static boolean dniPaciente(Formulario form){
		boolean comprobado = true;
		try{
			
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection(BBDDName, user, pass);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Paciente.dni FROM Paciente;");
		while (rs.next()) {
			int dni = rs.getInt("dni");
			if(Integer.parseInt(form.getDni().getText())==dni){
				comprobado = false;
			}
		}
		}catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		
		return comprobado;
	}
	
	public static boolean dniTecnico(Formulario form){
		boolean comprobado = true;
		try{
			
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection(BBDDName, user, pass);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Tecnico.dni FROM Tecnico;");
		while (rs.next()) {
			int dni = rs.getInt("dni");
			if(Integer.parseInt(form.getDni().getText())==dni){
				comprobado = false;
			}
		}
		}catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		
		return comprobado;
	}
	
	public static boolean dniMedico(Formulario form){
		boolean comprobado = true;
		try{
			
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection(BBDDName, user, pass);
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Medico.dni FROM Medico;");
		while (rs.next()) {
			int dni = rs.getInt("dni");
			if(Integer.parseInt(form.getDni().getText())==dni){
				comprobado = false;
			}
		}
		}catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		
		return comprobado;
	}
}
