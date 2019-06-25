<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio Sesión</title>
</head>
<body>
<%
	String usuario = (String) session.getAttribute("IdUsuario");

	if (usuario != null){
%>
	<h2  align="center"> Ha iniciado sesión</h2>

		<div align="right">
			<a href="./../CerrarSesion" target="_self">Cerrar sesión</a>
		</div>	
<%
	}else{
		response.sendRedirect("index.jsp?estado=ilegal");
	}
%>
</body>
</html>