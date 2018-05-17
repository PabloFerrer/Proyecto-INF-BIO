package Model;

import java.util.ArrayList;

/**
 * La clase medico crea un objeto medico el cual tiene una serie
 * de atributos que podemos observar en las declaraciones. 
 * 
 * @author Heartlight
 * 
 * @version Final
 */
public class Medico extends Usuario{
	
	private int ss;
	private int numero;
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

	
	/**
	 * Getter del atributo seguridad social del medico
	 * @return String ss
	 */
	public int getSs() {
		return ss;
	}
	/**
	 * Setter del atributo seguridad social del medico
	 * @param ss String 
	 */
	public void setSs(int ss) {
		this.ss = ss;
	}
	
	/**
	 * Getter del atributo numero del medico
	 * @return String numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Setter del atributo numero del medico
	 * @param numero String 
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * Getter de la lista de pacientes del medico
	 * @return ArrayList de Paciente pacientes
	 */
	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}
	/**
	 * Setter de la lista de pacientes del medico
	 * @param pacientes ArrayList de Paciente pacientes
	 */
	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public Medico() {
		super();
	}
	
	public Medico(String user, String rol, String con, int dni, int ss, int numero,
			ArrayList<Paciente> pacientes) {
		super(user, rol, con, dni);
		this.ss = ss;
		this.numero = numero;
		this.pacientes = pacientes;
	}
	
	public Medico(Usuario us,int ss, int numero) {
		super(us.getNombre(), us.getApellido(), us.getUser(), us.getRol(), us.getCon(), us.getDni(), us.getUbicacion());
		this.ss = ss;
		this.numero = numero;
		this.pacientes = new ArrayList<Paciente>();
	}
	public Medico(String nombre, String apellido, String user, String rol, String con, int dni, String ubicacion,
			int ss, int numero, ArrayList<Paciente> pacientes) {
		super(nombre, apellido, user, rol, con, dni, ubicacion);
		this.ss = ss;
		this.numero = numero;
		this.pacientes = pacientes;
	}
	
	public Medico(int ss, int numero, ArrayList<Paciente> pacientes) {
		super();
		this.ss = ss;
		this.numero = numero;
		this.pacientes = pacientes;
	}
	/**
	 * Metodo que nos permite aniadir un paciente al medico
	 * @param p Paciente
	 */
	public void aniadirpaciente(Paciente p) {
		pacientes.add(p);
	}
	/**
	 * Metodo que nos permite eliminar un paciente de la lista
	 * de pacientes del medico
	 * @param p Paciente
	 */
	public void eliminarpaciente(Paciente p) {
		pacientes.remove(p);
	}
}
