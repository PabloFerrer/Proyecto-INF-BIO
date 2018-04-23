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
public class Medico {
	private String nombre;
	private String apellidos;
	private String dni;
	private String ss;
	private String hospital;
	private String numero;
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

	/**
	 * Getter del atributo nombre del medico
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter del atributo nombre del medico
	 * @param nombre String 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter del atributo apellidos del medico
	 * @return String apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Setter del atributo apellidos del medico
	 * @param apellidos String 
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Getter del atributo dni del medico
	 * @return String dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * Setter del atributo dni del medico
	 * @param dni String 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * Getter del atributo seguridad social del medico
	 * @return String ss
	 */
	public String getSs() {
		return ss;
	}
	/**
	 * Setter del atributo seguridad social del medico
	 * @param ss String 
	 */
	public void setSs(String ss) {
		this.ss = ss;
	}
	/**
	 * Getter del atributo hospital del medico
	 * @return String hospital
	 */
	public String getHospital() {
		return hospital;
	}
	/**
	 * Getter del atributo hospital del medico
	 * @param hospital String 
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	/**
	 * Getter del atributo numero del medico
	 * @return String numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * Setter del atributo numero del medico
	 * @param numero String 
	 */
	public void setNumero(String numero) {
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
	
	/**
	 * Constructor de la clase Medico
	 * 
	 * @param nombre String 
	 * @param apellidos String 
	 * @param dni String 
	 * @param ss String 
	 * @param hospital String 
	 * @param numero String 
	 * @param pacientes ArrayList de Paciente 
	 */
	public Medico(String nombre, String apellidos, String dni, String ss, String hospital, String numero,ArrayList<Paciente>pacientes) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.ss = ss;
		this.hospital = hospital;
		this.numero = numero;
		this.pacientes = pacientes;
	}
	
	public Medico() {
		super();
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
