<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Materias</h1>
        <p><a href="inicioC?action=add">Nuevo</a></p>

        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="item" items="${curso}">
                <tr>
                    <td>${item.id_curso}</td>
                    <td>${item.descripcion}</td>
                    <td><a href="inicioC?action=edit&id_curso=${item.id_curso}">Editar</a></td>
                    <td><a href="inicioC?action=delete&id_curso=${item.id_curso}" onclick="return(confirm('Esta Seguro'))">Eliminar</a></td>
                </tr>
            </c:forEach> 
        </table>
    </body>
</html>
