package Model;

public class Administrador extends Usuario{
	private int vencimiento;
	public Administrador(String user, String rol, String con, int dni) {
		super(user, rol, con, dni);
	}

}
