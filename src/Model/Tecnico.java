package Model;

public class Tecnico extends Usuario{
	private int turno;
	private int estado;
	public Tecnico(String user, String rol, String con, int dni, int turno, int estado) {
		super(user, rol, con, dni);
		this.turno = turno;
		this.estado = estado;
	}
	
}
