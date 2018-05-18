package Model;

import java.awt.Image;
import java.util.Vector;

/**
 * La clase Paciente crea un objeto paciente el cual tiene una serie
 * de atributos que podemos observar en las declaraciones. 
 * 
 * @author Heartlight
 * 
 * @version Final
 */
public class Paciente {
	private String id;
	private String nombre;
	private String apellido;
	private String dni;
	private int ss;
	private String poblacion;
	private Image foto;
	private Vector<ECG> ecgs;
	private Vector<Mensaje> mensajes;

	/**
  	* Constructor de la clase Paciente
  	* @param nombre String 
  	* @param apellido String 
  	* @param dni String 
  	* @param ss String 
  	* @param poblacion String 
  	* @param calle String 
  	* @param foto String 
  	* @param importancia String 
  	* @param comentarios String 
  	* @param ecgs Vector de ECG 
  	*/
	public Paciente(String nombre, String apellido, String dni, int ss, String poblacion,
			Image foto,Vector<ECG> ecgs) {
		super();
		this.nombre =nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.ss = ss;
		this.poblacion = poblacion;
		this.foto = foto;
		this.ecgs = ecgs;
		mensajes=new Vector<Mensaje>();
	}
	
	public Paciente(String nombre,String apellido,String dni,String ubicacion){
		this.poblacion=ubicacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		mensajes=new Vector<Mensaje>();
	}
	
	public Paciente(){
		
	}
	/**
	 * Getter del atributo nombre del paciente
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter del atributo nombre del paciente
	 * @param nombre String 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter del atributo apellido del paciente
	 * @return apellido String 
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * Setter del atributo apellido del paciente
	 * @param apellido String 
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * Getter del atributo dni del paciente
	 * @return String dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * Setter del atributo dni del paciente
	 * @param dni String 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getId() {
		return id;
	}

	/**
	 * Getter del atributo ss del paciente
	 * @return String numero
	 */
	public int getSs() {
		return ss;
	}
	/**
	 * Setter del atributo ss del paciente
	 * @param numero String 
	 */
	public void setSs(int numero) {
		this.ss = numero;
	}
	/**
	 * Getter del atributo poblacion del paciente
	 * @return String poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * Setter del atributo poblacion del paciente
	 * @param poblacion String 
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * Getter del atributo foto del paciente
	 * @return String foto
	 */
	public Image getFoto() {
		return foto;
	}
	/**
	 * Setter del atributo foto del paciente
	 * @param foto String 
	 */
	public void setFoto(Image foto) {
		this.foto = foto;
	}

	
	/**
	 * Getter de la lista de electrocardiogramas del paciente
	 * @return Vector de ECG ecgs
	 */
	public Vector<ECG> getEcgs() {
		return ecgs;
	}
	/**
	 * Setter de la lista de electrocardiogramas del paciente
	 * @param ecgs Vector de ECG 
	 */
	public void setEcgs(Vector<ECG> ecgs) {
		this.ecgs = ecgs;
	}

	public Vector<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Vector<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	
	
}
