package practica.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import practica.bbdd.Usuario;

/**
 * Servlet implementation class InicioSesion
 */
public class InicioSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InicioSesion() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Recibe los valores de usuario y contraseña introducidos por el cliente
		String sUsuario = request.getParameter("txtUsuario");
		String sPwd = request.getParameter("txtPwd");

		if (sUsuario != null && sPwd != null){ //Ni usuario ni contraseña están vacíos
			
			//Se comprueba si el usuario está en la base de datos
			Usuario usu = new Usuario().validarUsuario(sUsuario, sPwd); 
			// Si usuario es null es que no se ha validado
			if (usu != null){
				//El usuario está en la base de datos
				System.out.println(usu.nombre()); //Imprime en consola el usuario
				//COOKIE
				Cookie miCookie = new Cookie("nick",sUsuario); //Crea la cookie
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				HttpSession session = request.getSession(); //Genera la sesión. 
				session.setAttribute("IdUsuario",usu.nombre()); //La variable de sesión el es usuario
				
				response.sendRedirect("logado/HealthMap.jsp"); // Cuando se ha validado redirige a HealthMap.js
				System.out.println("creada la cookie y la variable de sesion"); 
			}else{
				System.out.println("El usuario no existe"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=invalido"); //Redirige a index.jsp con el estado 'invalido' 
			}
		}
	}
}