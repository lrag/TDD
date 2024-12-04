package com.curso.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curso.modelo.entidad.Usuario;

@WebServlet("/SVLogin")
public class SVLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SVLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String pw    = request.getParameter("pw");
		
		String redirect = "seguro/SVClientes";
		//Simplificando...
		if(login.equals("harry") && pw.equals("callahan")){
			Usuario usuario = new Usuario(1, "Harry Callahan", "harry", null, "ADMIN");
			iniciarSesion(usuario, request);
		} else if(login.equals("bud") && pw.equals("spencer")){
			Usuario usuario = new Usuario(1, "Bud Spencer", "aaa", null, "USR");
			iniciarSesion(usuario, request);
		} else {
			redirect = "login.html";
		}

		response.sendRedirect(redirect);	
	}

	private void iniciarSesion(Usuario usuario, HttpServletRequest request) {
		HttpSession sesion = request.getSession(false);
		if(sesion != null) {
			sesion.invalidate();
		}
		sesion = request.getSession(true);
		sesion.setAttribute("usuario", usuario);
	}
	
}
