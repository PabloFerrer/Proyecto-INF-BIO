package Model;

import java.util.ArrayList;

public class Tecnico extends Usuario{
	private int turno;
	
	public Tecnico(String user, String rol, String con, int dni, int turno) {
		super(user, rol, con, dni);
		this.turno = turno;
		
	}
	
	public Tecnico(Usuario us, int turno) {
		super(us.getNombre(), us.getApellido(), us.getUser(), us.getRol(), us.getCon(), us.getDni(), us.getUbicacion());
		this.turno=turno;
		
	}
	
}
