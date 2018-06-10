package Model;

import java.io.IOException;
import java.util.Vector;

public class Utilidades {

    private static final String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

    int[] valors = new int[0];

    static public String letraDNI(int dni) {
    	int nif = Integer.valueOf(dni)%23;
        return letras[nif];
    }
    
	private static int pivotear( Vector<ECG>L, Vector<Paciente>M,int ini, int fin){
		int i=ini;
		int med=(fin+ini)/2;
		ECG auxi=L.get(med);
		Paciente auxi2=M.get(med);
		M.remove(med);
		M.insertElementAt(M.get(ini), med);
		M.remove(ini);
		M.insertElementAt(auxi2, ini);
		L.remove(med);
		L.insertElementAt(L.get(ini), med);
		L.remove(ini);
		L.insertElementAt(auxi, ini);
		
		int p=L.get(ini).getFecha();
		for(int j=ini+1;j<=fin;++j){
			if(L.get(j).getFecha()>=p){
				i++;
				if(i!=j){
					ECG aux=L.get(j);
					Paciente aux2=M.get(j);
					M.remove(j);
					M.insertElementAt(M.get(i), j);
					M.remove(i);
					M.insertElementAt(aux2, i);
					L.remove(j);
					L.insertElementAt(L.get(i), j);
					L.remove(i);
					L.insertElementAt(aux, i);
				}
			} 
		} 
		ECG aux=L.get(i);
		Paciente aux2=M.get(i);
		M.remove(i);
		M.insertElementAt(M.get(ini), i);
		M.remove(ini);
		M.insertElementAt(aux2, ini);
		L.remove(i);
		L.insertElementAt(L.get(ini), i);
		L.remove(ini);
		L.insertElementAt(aux, ini);
		
		return i;
	}
	/**
	 * Metodo de ordenacion para vector de ECG
	 * @param L Vector de ECG a ordenar
	 * @param m Vector de Pacientes a ordenar
	 * @param ini indice del primer valor
	 * @param fin indice del ultimo valor
	 */
	public static void quicksort( Vector<ECG> L, Vector<Paciente> m,int ini, int fin){
		if(ini<fin){
			int x=pivotear(L,m,ini,fin);
			quicksort(L,m,ini,x-1);
			quicksort(L,m,x+1,fin);
		}
	}
	
public static void mergesort(Mensaje[] L,int ini,int fin,char concept){
		
		if(ini<fin){
			int med=(ini+fin)/2;
			Mensaje[] Liz=new Mensaje[L.length+1];
			Mensaje[] Lde=new Mensaje[L.length+1];
			for(int i=0;i<=med;i++){
				Liz[i]=L[i];
			}
			for(int i=med+1;i<=fin;i++){
				Lde[i]=L[i];
			}
			
			mergesort(Liz,ini,med,concept);
			mergesort(Lde,med+1,fin,concept);
			merge(Liz,Lde,L,ini,fin,med,concept);
			
		}
	}
	private static void merge(Mensaje[] Liz,Mensaje[] Lde,Mensaje[] L,int ini,int fin, int med,char conc){
		Mensaje aux;
		if(conc=='<') {
			aux= new Mensaje(0, 0, 0, 0, "", Integer.MAX_VALUE, "", "", 0);
		} else {
			aux= new Mensaje(0, 0, 0, 0, "", Integer.MIN_VALUE, "", "", 0);
		}
		Liz[med+1]=aux;
		Lde[fin+1]=aux;
		int i=ini;
		int j=med+1;
		for(int cont=ini;cont<=fin;cont++){
			if((conc=='<')?Liz[i].getFecha()<Lde[j].getFecha():Liz[i].getFecha()>Lde[j].getFecha()){
				L[cont]=Liz[i];
				i++;
			} else {
				L[cont]=Lde[j];
				j++;
			}
		}
	}
	
	public static int buscarECGBin(Paciente p, ECG ecg, int ini, int fin) {
		if(ini>fin) {
				return -1;
		} else {
			int m=(ini+fin)/2;
			if(p.getEcgs().get(m).getId()==ecg.getId()) {
				return m;
			} else if(p.getEcgs().get(m).getId()<ecg.getId()) {
				return buscarECGBin(p,ecg,ini,m-1);
			} else {
				return buscarECGBin(p,ecg,m+1,fin);
			}
		}
	}
	
	public static void quicksortPalabras(String[] L,int ini, int fin){
		if(ini<fin){
			int i=pivotearPalabras(L,ini,fin);
			quicksortPalabras(L,ini,i-1);
			quicksortPalabras(L,i+1,fin);
		} 
	}
	
	private static int pivotearPalabras(String[] L,int ini, int fin){
		int i=ini;
		String p=L[ini];
		for(int j=ini+1;j<=fin;++j){
			if(L[j].length()>=p.length()){
				i++;
				if(i!=j){
					String aux=L[i];
					L[i]=L[j];
					L[j]=aux;
				}
			} 
		} 
		String aux=L[ini];
		L[ini]=L[i];
		L[i]=aux;
		
		return i;
	}
}