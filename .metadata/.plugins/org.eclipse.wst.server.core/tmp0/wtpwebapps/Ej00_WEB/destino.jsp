<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos enviados</title>
</head>
<body>


<%
System.out.println("===============================");
System.out.println(request.getParameter("nombre"));
System.out.println(request.getParameter("mail"));
%>

	<p align="center">
		<table border="0" cellspacing="5">
			<tr>
				<th align="right">El nombre enviado es:</th>
				<td align="left">
					<div id="nombre">
						<%=request.getParameter("nombre")%>
					</div>
				</td>
			</tr>
			<tr>
				<th align="right">El mail enviado es:</th>
				<td align="left"><div id="mail"><%=request.getParameter("mail")%></div></td>
			</tr>
			<tr>
				<td align="right"><a href="index.html">inicio</a></td>
			</tr>
		</table>
	</p>

</body>
</html>