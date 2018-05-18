package Model;

import java.util.Vector;

/**
 * Objeto que representa la forma numerica de una grafica de Electrocardiograma
 * junto a sus datos necesarios para conocer su origen
 * @author HeartLight
 *
 */
public class ECG {
	
	public ECG(int id,int fecha, int fechaDiag, boolean leido, int dniMed, int dniTec, int dniPac,
			String comentarios, int pulsa, String diagnostico, int puntosporsec, String puntos) {
		super();
		this.id=id;
		this.nombre=fecha+"";
		this.fecha = fecha;
		this.fechaDiag = fechaDiag;
		this.leido = leido;
		this.dniMed = dniMed;
		this.dniTec = dniTec;
		this.dniPac = dniPac;
		Comentarios = comentarios;
		this.pulsa = pulsa;
		Diagnostico = diagnostico;
		this.puntosporsec = puntosporsec;
		this.puntos = puntos;
	}
	public int getId() {
		return id;
	}
	private int id;
	private int fecha;
	private int fechaDiag;
	private boolean leido;
	private int dniMed;
	private int dniTec;
	private int dniPac;
	private String Comentarios;
	private int pulsa;
	private String Diagnostico;
	private int puntosporsec;
	private String nombre;
	private String puntos;
	
	
	/**
	 * Setter para modificar el diagnostico
	 * @param diagnostico nuevo diagnostico
	 */
	public void setDiagnostico(String diagnostico) {
		Diagnostico = diagnostico;
	}
/**
 * Constructor de un ECG
 * @param fecha Fecha de subida
 * @param nombreTec Nombre del tecnico que lo hizo
 * @param comentarios Comentario del tecnico
 * @param diagnostico Diagnostico del doctor
 * @param puntosporsec Cantidad de puntos por segundo del ecg
 * @param nombre nombre del archivo del ECG
 * @param puntos Puntos del ECG
 */
	public ECG(int fecha,int dniTec, String comentarios, String diagnostico, int puntosporsec, String nombre,
			String puntos) {
		this.dniTec=dniTec;
		this.fecha = fecha;
		Comentarios = comentarios;
		Diagnostico = diagnostico;
		this.puntosporsec = puntosporsec;
		this.nombre = nombre;
		this.puntos = puntos;
		leido=false;
	}
	/**
	 * Constructor de un ECG
	 * @param fecha Fecha de subida
	 * @param nombreTec Nombre del tecnico que lo hizo
	 * @param comentarios Comentario del tecnico
	 * @param diagnostico Diagnostico del doctor
	 * @param puntosporsec Cantidad de puntos por segundo del ecg
	 * @param nombre nombre del archivo del ECG
	 * @param puntos Puntos del ECG
	 * @param leido boolean si el medico lo ha leido o no
	 */
	public ECG(int fecha,int dniTec, String comentarios, String diagnostico, int puntosporsec, String nombre,
			String puntos,boolean leido) {
		this.dniTec=dniTec;
		this.fecha = fecha;
		Comentarios = comentarios;
		Diagnostico = diagnostico;
		this.puntosporsec = puntosporsec;
		this.nombre = nombre;
		this.puntos = puntos;
		this.leido=leido;
	}
	public ECG(int fecha,int dniTec,int dniPac, String comentarios, int puntosporsec, 
			String puntos,boolean leido) {
		this.dniTec=dniTec;
		this.dniPac=dniPac;
		this.fecha = fecha;
		Comentarios = comentarios;
		this.puntosporsec = puntosporsec;
		this.puntos = puntos;
		this.leido=leido;
	}
	public int getDniPac() {
		return dniPac;
	}
	public int getDniTec() {
		return dniTec;
	}
	/**
	 * Getter para saber si fue leido o no
	 * @return true=leido 
	 */
	public boolean isLeido() {
		return leido;
	}
/**
 * Constructor de ECG sencillo leido por el tecnico
 * @param puntosporsec Cantidad de puntos por segundo del ecg
 * @param nombre nombre del archivo del ECG
 * @param puntos Puntos del ECG
 */
	public ECG(int puntosporsec, String nombre,
			String puntos) {
		this.puntosporsec = puntosporsec;
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
/**
 * Getter de la fecha
 * @return Fecha en la que se hizo el ECG
 */
	public int getFecha() {
		return fecha;
	}

	/**
	 * Getter del comentario que hizo el tecnico
	 * @return el comentario que hizo el tecnico
	 */
	public String getComentarios() {
		return Comentarios;
	}
	/**
	 * Getter del diagnostico que hizo el medico
	 * @return el diagnostico que hizo el medico
	 */
	public String getDiagnostico() {
		return Diagnostico;
	}
	/**
	 * Getter del nombre del ECG
	 * @return el nombre del ECG
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Getter de la cantidad de puntos por segundo
	 * @return cantidad de puntos en un segundo
	 */
	public int getPuntosporsec() {
		return puntosporsec;
	}
	/**
	 * Getter de todos los puntos que conforman el ECG
	 * @return Puntos del ECG
	 */
	public String getPuntos() {
		return puntos;
	}

	/**
	 * Setter de leido
	 * @param leido establecer si el ECG fue leido o no
	 */
	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	private static int pivotear( Vector<ECG>L,int ini, int fin){
		int i=ini;
		int med=(fin+ini)/2;
		ECG auxi=L.get(med);
		L.remove(med);
		L.insertElementAt(L.get(ini), med);
		L.remove(ini);
		L.insertElementAt(auxi, ini);
		int p=L.get(ini).getFecha();
		for(int j=ini+1;j<=fin;++j){
			if(L.get(j).getFecha()<=p){
				i++;
				if(i!=j){
					ECG aux=L.get(j);
					L.remove(j);
					L.insertElementAt(L.get(i), j);
					L.remove(i);
					L.insertElementAt(aux, i);
				}
			} 
		} 
		ECG aux=L.get(i);
		L.remove(i);
		L.insertElementAt(L.get(ini), i);
		L.remove(ini);
		L.insertElementAt(aux, ini);
		
		return i;
	}
	/**
	 * Metodo de ordenacion para vector de ECG
	 * @param L Vector a ordenar
	 * @param ini indice del primer valor
	 * @param fin indice del ultimo valor
	 */
	static void quicksort( Vector<ECG> L,int ini, int fin){
		if(ini<fin){
			int x=pivotear(L,ini,fin);
			quicksort(L,ini,x-1);
			quicksort(L,x+1,fin);
		}
	}
	

}
