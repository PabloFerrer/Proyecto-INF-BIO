package Model;

import java.util.ArrayList;

public class Administrador extends Usuario{
	private int vencimiento;
	public Administrador(String user, String rol, String con, int dni) {
		super(user, rol, con, dni);
	}
	
	public Administrador(Usuario us, int vencimiento) {
		super(us.getNombre(), us.getApellido(), us.getUser(), us.getRol(), us.getCon(), us.getDni(), us.getUbicacion());
		this.vencimiento=vencimiento;
	}

}
