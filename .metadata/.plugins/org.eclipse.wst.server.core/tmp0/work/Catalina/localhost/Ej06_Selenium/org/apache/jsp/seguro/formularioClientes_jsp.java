/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.24
 * Generated at: 2020-03-23 10:45:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.seguro;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class formularioClientes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">    \r\n");
      out.write("    <title>Listado de clientes</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/bootstrap-theme.min.css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"page-header text-center\">\r\n");
      out.write("        <h1 class=\"titulo\">Formulario de clientes</h1> \r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("\t<form name=\"formulario\" action=\"SVClientes\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"accion\"/>\r\n");
      out.write("\r\n");
      out.write("\t    <div class=\"row\">\r\n");
      out.write("\t        <div class=\"col-xs-2\"></div>  \r\n");
      out.write("\t        <div class=\"text-center col-xs-8\">  \r\n");
      out.write("\t\t\t\t<input type=\"submit\" id=\"btnInsertar\"  value=\"Insertar\"  class=\"btn btn-primary\" onclick=\"document.formulario.accion.value='insertar'\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" id=\"btnModificar\" value=\"Modificar\" class=\"btn btn-primary\" onclick=\"document.formulario.accion.value='modificar'\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" id=\"btnBorrar\"    value=\"Borrar\"    class=\"btn btn-primary\" onclick=\"document.formulario.accion.value='borrar'\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"Cancelar\"  class=\"btn btn-primary\"/>\r\n");
      out.write("\t        </div>\r\n");
      out.write("\t        <div class=\"col-xs-2\"></div>  \r\n");
      out.write("\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t\t</br>\r\n");
      out.write("\t\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"idCliente\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${clienteSel.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("\r\n");
      out.write("\t    <div class=\"row\">\r\n");
      out.write("\t        <div class=\"col-xs-2\"></div>\r\n");
      out.write("\t        <div class=\"col-xs-8 form-horizontal\">\r\n");
      out.write("\t            <div class=\"form-group\">\r\n");
      out.write("\t                <label class=\"control-label col-xs-2\" for=\"nombre\">Nombre</label>\r\n");
      out.write("\t                <div class=\"col-xs-8\">\r\n");
      out.write("\t                    <input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${clienteSel.nombre}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t            </div>  \r\n");
      out.write("\t            <div class=\"form-group\">\r\n");
      out.write("\t                <label class=\"control-label col-xs-2\" for=\"director\">Dirección</label>\r\n");
      out.write("\t                <div class=\"col-xs-8\">\r\n");
      out.write("\t                    <input type=\"text\" id=\"director\" name=\"direccion\" class=\"form-control\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${clienteSel.direccion}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t            </div>  \r\n");
      out.write("\t            <div class=\"form-group\">\r\n");
      out.write("\t                <label class=\"control-label col-xs-2\" for=\"telefono\">Teléfono</label>\r\n");
      out.write("\t                <div class=\"col-xs-8\">\r\n");
      out.write("\t                    <input type=\"text\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${clienteSel.telefono}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t            </div>  \r\n");
      out.write("\t        <div class=\"col-xs-2\"></div>        \r\n");
      out.write("\t        </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
