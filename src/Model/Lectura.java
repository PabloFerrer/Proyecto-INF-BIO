package Model;

/*import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * La clase lectura es una clase de una version anterior de cuando el programa funcionaba con txt
 * 
 * @author Heartlight
 * 
 * @version Final 
 */
public class Lectura{
	

//	/**
//	 * Metodo que nos permite leer aquellos electrocardiogramas 
//	 * que ya hallan sido leidos y guardarlos para poder operar 
//	 * con ellos en otras clases
//	 * 
//	 * @param f File 
//	 * @return ECG
//	 */
//	public static ECG lecturaEcgYaGuardado(File f) {
//		int pun=0;
//		int fecha=0;
//		String comen="";
//		String diag="";
//		String tec="";
//		int dnitec=0;
//		boolean lei=false;
//		String puntos="";
//		try(Scanner br = new Scanner(new FileReader (f))) {
//			fecha=Integer.parseInt(br.nextLine());
//			if(br.nextLine().equals("leido")) {
//				lei=true;
//			}
//			tec=br.nextLine();
//			dnitec=Integer.parseInt(br.nextLine());
//			pun=Integer.parseInt(br.nextLine());
//			puntos=br.nextLine();
//			if(br.hasNextLine()) {
//				comen=br.nextLine();
//			}
//			if(br.hasNextLine()) {
//				diag=br.nextLine();
//			}
//			
//			
//		}
//			catch(Exception e){
//				//e.printStackTrace();
//			}
//		
//		
//		return new ECG(fecha,tec,dnitec,comen,diag,pun,f.getName().replaceAll(".txt", ""),puntos,lei);
//		}
	
//	/**
//	 * Metodo que nos permite obtener de un arhivo de texto plano que contiene un paciente
//	 * los electrocardiogramas de dicho paciente para poder operar con ellos posteriormente
//	 * en otros metodos o en otras clases.
//	 * 
//	 * @param f File 
//	 * @param pacien PacienteTecnico 
//	 * @param b String 
//	 * 
//	 * @return aux Object[] 
//	 */
//	public static Object[] lecturaEcg(File f, Paciente pacien, String b) {
//		Object[] aux=new Object[2];
//		Calendar c= Calendar.getInstance();
//		String no="ECG_"+Integer.toString(c.get(Calendar.YEAR))+""+Integer.toString(c.get(Calendar.MONTH))+""+Integer.toString(c.get(Calendar.DATE))+"_"+pacien.getId()+"_"+b;
//		int pun=-1;
//		String puntos="";
//		File archivo = f;
//		try(BufferedReader br = new BufferedReader(new FileReader (archivo))) {
//			
//					pun=Integer.parseInt(br.readLine());
//				
//					puntos=br.readLine();
//					
//					
//					aux[1]=new ECG(pun,no,puntos);
//					aux[0]=no;
//					return aux;
//		}
//			catch(Exception e){
//				JOptionPane.showMessageDialog(null, "El archivo no tiene el formato correcto");
//			}
//		aux[1]=new ECG(0,"","");
//		aux[0]="";
//		return aux;
//		}
//	
////	/**
//	 * Metodo que nos permite leer de un archivo plano el contenido de un medico, 
//	 * a partir de este obtenemos los pacientes de dicho medico y finalmente
//	 * obtenemos los electrocardiogramas de cada paciente.
//	 * 
//	 * @param us Usuario 
//	 * 
//	 * @return m Medico 
//	 */
//	public static Medico lectura_medico(Usuario us) {
//		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
//		Medico m = new Medico();
//		File file = null;
//		file = new File("Resource/Medicos/" + us.getUser()+ ".txt");
//		try(Scanner sc = new Scanner(new FileReader(file))) {
//			String[] datos;
//			String palabra = sc.nextLine();
//			datos = palabra.split(";");
//			Scanner sc2;
//			while(sc.hasNextLine()){
//				Vector<ECG> ecgs = new Vector<ECG>();
//				String[] array ;
//				String paciente = sc.nextLine();
//				File file2 = null;
//				file2 = new File("Resource/Pacientes/"+paciente+".txt");
//				if(file2.exists()){
//					sc2 = new Scanner(new FileReader(file2));
//					array = sc2.nextLine().split(";");;
//					String foto = sc2.nextLine();
//					String importancia = sc2.nextLine();
//					String comentarios = sc2.nextLine();
//					while(sc2.hasNextLine()){
//						File f=new File("Resource/ECG/"+sc2.nextLine()+".txt");
//						if(f.exists()){
//							ecgs.add((ECG)lecturaEcgYaGuardado(f));
//						}
//					}
//					
//					Paciente p = new Paciente(array[0],array[1],array[2],array[3],array[4],array[5],foto,importancia,comentarios,ecgs);
//					ECG.quicksort(p.getEcgs(), 0, p.getEcgs().size()-1);
//					pacientes.add(p);
//				}
//			}
////			public Medico(String nombre, String apellido, String user, String rol, String con, int dni, String ubicacion,
////					String ss, String numero, ArrayList<Paciente> pacientes) {
//			m = new Medico(datos[0],datos[1],us.getUser(),us.getRol(),us.getCon(),Integer.parseInt(datos[2].substring(0, datos[2].length()-2)),datos[4],datos[3],datos[5],pacientes);
//		}catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return m;
//	}
//	
	
	}
