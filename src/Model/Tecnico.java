package Model;

import java.util.ArrayList;

public class Tecnico extends Usuario{
	private int turno;
	private int estado;
	public Tecnico(String user, String rol, String con, int dni, int turno, int estado) {
		super(user, rol, con, dni);
		this.turno = turno;
		this.estado = estado;
	}
	
	public Tecnico(Usuario us, int turno,int estado) {
		super(us.getNombre(), us.getApellido(), us.getUser(), us.getRol(), us.getCon(), us.getDni(), us.getUbicacion());
		this.turno=turno;
		this.estado=estado;
	}
	
}
