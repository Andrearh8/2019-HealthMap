package practica.bbdd;

import java.sql.*;

/*
 * La clase BBDD contiene los métodos necesarios para:
 * -Realizar la conexión con la base de datos
 * -Cerrar la conexión con la base de datos
 * -Comprobar si el usuario forma parte de la base de datos
 */
public class  BBDD {
	private Connection conn;
	private PreparedStatement pst;
	private String usuario;
	private String password;
		
	public BBDD(){		
		
		inicializa();
	}
	//Inicializa las variables
	private void inicializa(){
		this.conn = null;
		this.usuario = "root";
		this.password = "";
	}

	//El método abrir conexión conecta con la base de datos 	
	public void abrirConexion(){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/DatosHealthMap?serverTimezone=UTC",usuario, password);
			if(conn!=null) {	
				System.out.print("Conexión correcta:");}
				//this.stm = conn.createStatement();
			//this.pst = conn.prepareStatement();
		 } catch (SQLException e){
        	throw new HealthMapException("Error al abrir la conexión: " + e.getMessage());
        } catch (ClassNotFoundException e){
        	throw new HealthMapException("Error al abrir la conexión: " + e.getMessage());
        } catch (Exception e){
        	throw new HealthMapException("Error desconocido" + e.getMessage());
        }
	}
	
	//El método cerrarConexion cierra la conexión con la base de datos
	public void cerrarConexion(){
		try{
			if (this.pst != null){
				this.pst.close();
			}
			if (this.conn != null){
				this.conn.close();
			}
		}catch (SQLException e){
			throw new HealthMapException("Error : " + e.getMessage());
		}catch (Exception e){
			throw new HealthMapException("Error desconocido" + e.getMessage());
		}
	}
	
	//El método getUsuario comprueba si el usuario forma parte de la bases de datos
	public Usuario getUsuario(String sql){
		Usuario usu = null;
		ResultSet rs=null;
		try{
			//se realiza la consulta a la tabla Usuarios de la base de datos
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()){
				usu = new Usuario(rs.getInt("Id"), rs.getString("Usuario"), rs.getString("Clave"));
			}
		}catch (SQLException e){
			throw new HealthMapException("Error : " + e.getMessage());
		}catch(Exception e){
			throw new HealthMapException("Error indefinido. "+ e.getMessage());
		}finally{
			//En el bloque finally se cierra el Statement y el Resultset
			try{
				if(rs!=null) {
					rs.close();
				}
				if(pst!=null) {
				pst.close();
				}
			}catch (Exception e) {
                System.out.print("Error: No es posible cerrar la conexión.");
            }
		}
	//si el usuario no coincide con ninguno de la base de datos el valor devuelto será null
	return usu;		
	}
}


