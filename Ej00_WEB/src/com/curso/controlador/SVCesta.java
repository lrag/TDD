package com.curso.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curso.modelo.entidad.Detalle;

@WebServlet("/SVCesta")
public class SVCesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SVCesta() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("cesta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String producto = request.getParameter("producto");
		Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
		Detalle d = new Detalle(producto, cantidad);
		
		HttpSession sesion = request.getSession(true);
		System.out.println(sesion.getId()+", ADD:"+d);
		List<Detalle> cesta = (List<Detalle>) sesion.getAttribute("cesta");
		if(cesta == null) {
			cesta = new ArrayList<>();
			sesion.setAttribute("cesta", cesta);
		}		
		cesta.add(d);
		
		response.sendRedirect("SVCesta");
	}

}
