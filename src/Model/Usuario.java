package Model;

/**
 * Objeto Usuario que representa las identificaciones y contrasena de 
 * una persona para acceder a la aplicacion, incluyendo a su rol para saber 
 * 
 * @author HeartLight
 * 
 * @version Final
 * 
 *
 */
public class Usuario {
		private String nombre;
		private String apellido;
		private String user;
		private String rol;
		private String con;
		private int dni;
		private String ubicacion;
		
		public String getNombre() {
			return nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public String getUbicacion() {
			return ubicacion;
		}

		public int getDni() {
			return dni;
		}
		public Usuario() {
			
		}
		
		
		public Usuario(String nombre, String apellido, String user, String rol, String con, int dni, String ubicacion) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.user = user;
			this.rol = rol;
			this.con = con;
			this.dni = dni;
			this.ubicacion = ubicacion;
		}

		public Usuario(String user, String rol, String con, int dni) {
			super();
			this.user = user;
			this.rol = rol;
			this.con = con;
			this.dni = dni;
		}
		
		public Usuario(String user, String rol, int dni) {
			super();
			this.user = user;
			this.rol = rol;
			this.dni = dni;
		}

		

		/**
		 * Getter del nombre de Usuario
		 * @return nombre de Usuario
		 */
		public String getUser() {
			return user;
		}
		/**
		 * Getter del rol
		 * @return (medico,admin,tecnico)
		 */
		public String getRol() {
			return rol;
		}
		/**
		 * Getter de la contrasena del Usuario
		 * @return Contrasena
		 */
		public String getCon() {
			return con;
		}

		
		
		
}
