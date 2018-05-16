package Model;

import java.io.IOException;

public class Utilidades {

    private static final String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

    int[] valors = new int[0];

    static public String letraDNI(int dni) {
    	int nif = Integer.valueOf(dni)%23;
        return letras[nif];
    }
}