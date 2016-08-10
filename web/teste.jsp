<%-- 
    Document   : teste
    Created on : 01/07/2016, 15:44:41
    Author     : caio
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:useBean id="clienteDAO" class="persistencia.ClienteDAO"/>
        <table>
            <tr>
                <td>Nome</td>
                <td>email</td>
            </tr>
            <c:forEach var="cliente" items="${ClienteDAO.clientes}">
            <tr>
                <td>${clientes.nome}</td>
                <td>${clientes.email}</td>
            </tr>
            </c:forEach>
    </table> 
          
</body>
</html>
