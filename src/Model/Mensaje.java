package Model;

public class Mensaje {
		
		private int id;
		private int dniUsuario;
		private int dniPaciente;
		private String usuario;
		private int rol;
		private int leido;
		private int fecha;
		private String data;
		private String asunto;

		
		public Mensaje(int id,int dniUsuario, int dniPaciente, int leido, String data,int fecha,String asunto,String usuario,int rol) {
			super();
			this.id=id;
			this.rol=rol;
			this.usuario=usuario;
			this.dniUsuario = dniUsuario;
			this.dniPaciente = dniPaciente;
			this.leido = leido;
			this.fecha=fecha;
			this.data = data;
			this.asunto=asunto;
		}
		
		public int getDniUsuario() {
			return dniUsuario;
		}
		public void setDniUsuario(int dniUsuario) {
			this.dniUsuario = dniUsuario;
		}
		public int getDniPaciente() {
			return dniPaciente;
		}
		public void setDniPaciente(int dniPaciente) {
			this.dniPaciente = dniPaciente;
		}
		public int getFecha() {
			return fecha;
		}
		public void setFecha(int fecha) {
			this.fecha = fecha;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String toString() {
			return asunto +" "+ String.valueOf(fecha).substring(6, 8)+"-"+String.valueOf(fecha).substring(4, 6)+"-"+String.valueOf(fecha).substring(0, 4);
			
		}

		public int getId() {
			return id;
		}

		public int getLeido() {
			return leido;
		}

		public String getAsunto() {
			return asunto;
		}

		public void setLeido(int leido) {
			this.leido = leido;
		}

		public String getUsuario() {
			return usuario;
		}

		public int getRol() {
			return rol;
		}
		
}
