package Model;

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
	private String ss;
	private String poblacion;
	private String calle;
	private String foto;
	private String importancia;
	private String comentarios;
	private Vector<ECG> ecgs;

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
	public Paciente(String nombre, String apellido, String dni, String ss, String poblacion, String calle,
			String foto, String importancia, String comentarios,Vector<ECG> ecgs) {
		super();
		this.nombre =nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.ss = ss;
		this.poblacion = poblacion;
		this.calle = calle;
		this.foto = foto;
		this.importancia = importancia;
		this.comentarios = comentarios;
		this.ecgs = ecgs;
	}
	
	public Paciente(String nombre,String apellido,String dni,String ubicacion){
		this.poblacion=ubicacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
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
	public String getSs() {
		return ss;
	}
	/**
	 * Setter del atributo ss del paciente
	 * @param numero String 
	 */
	public void setSs(String numero) {
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
	 * Getter del atributo calle del paciente
	 * @return String calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * Setter del atributo calle del paciente
	 * @param calle String 
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * Getter del atributo foto del paciente
	 * @return String foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * Setter del atributo foto del paciente
	 * @param foto String 
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/**
	 * Getter del atributo importancia del paciente
	 * @return String importancia
	 */
	public String getImportancia() {
		return importancia;
	}
	/**
	 * Setter del atributo importancia del paciente
	 * @param importancia String 
	 */
	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}
	/**
	 * Getter del atributo comentarios del paciente
	 * @return String comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}
	/**
	 * Setter del atributo comentarios del paciente
	 * @param comentarios String 
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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

	
	
}
