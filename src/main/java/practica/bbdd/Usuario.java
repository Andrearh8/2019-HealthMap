package practica.bbdd;

/*
 * La clase Usuario contiene los atributos con los que se guardan los usuarios dentro de la base de datos. Estos son el id, el nombre y la clave
 */
public class Usuario implements Comparable<Usuario>{

	private int id;
	private String nombre;
	private String clave;
	
	//Método constructor
	public Usuario(int id, String nombre, String clave){
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		
	}
	//Método constructor
	public Usuario(){
		this.id = 0;
		this.nombre = "";
		this.clave = "";
	
	}
	//A continuación están todos los métodos de get y set 
	public String clave(){
		return clave;
	}

	public void clave(String clave){
		this.clave = clave;
	}

	
	public int Id(){
		return this.id;
	}

	public void Id(int id){
		this.id = id;
	}

	public String nombre(){
		return this.nombre;
	}

	public void nombre(String usu){
		this.nombre = usu;
	}

	//Método para convertir en String
	public String toString(){
		return " (Usuario " + this.id + ":" + this.nombre + ":" + this.clave + ") ";
	}

	public int compareTo(Usuario o){
		//Usuario u = (Usuario) o;
		return this.nombre.compareToIgnoreCase(o.nombre); //Ordenar por nombre
	}

	public boolean equals(Object o){
		Usuario u = (Usuario) o;
		return this.compareTo(u) == 0; //Dos usuarios son iguales si tienen el mismo nombre
	}
	/*
	 * El método validar usuario inicia la conexión con la base de datos y llama al método necesario para la consulta en la base de datos
	 *Este método devuelve Usuario, si este valor es vacío indica que no ha habido ninguna coincidencia en la base de datos
	 */
	public Usuario validarUsuario(String usuario, String pwd){
		Usuario usu = new Usuario();
		BBDD miBd=null ;
		try{
			miBd = new BBDD();
			miBd.abrirConexion();
			usu = miBd.getUsuario("Select * from Usuarios where usuario='" + usuario +"' and clave='" + pwd + "'");
			//miBd.cerrarConexion();
		}catch(HealthMapException e){
			System.out.println("Error en HealthMap " + e.getMessage());			
		}catch(Exception e){
			System.out.println("Error desconocido " + e.getMessage());
		}
		finally {
			try {
				miBd.cerrarConexion();
			}catch(Exception e){
				System.out.println("Se ha producido un error " + e.getMessage());
			}
		}
		return usu;
	}
}
