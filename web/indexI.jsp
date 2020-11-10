<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inscripciones</h1>
        <p><a href="inicioI?action=add">Nuevo</a></p>

        <table border="1">
            <tr>
                <th>Id</th>
                <th>Id_curso</th>
                <th>Id_estudiante</th>
                <th>Nota</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="item" items="${inscripciones}">
                <tr>
                    <td>${item.id_inscripcion}</td>
                    <td>${item.id_curso}</td>
                    <td>${item.id_estudiante}</td>
                    <td>${item.nota_final}</td> 
                    <td><a href="inicioI?action=edit&id_inscripcion=${item.id_inscripcion}">Editar</a></td>
                    <td><a href="inicioI?action=delete&id_incripcion=${item.id_inscripcion}" onclick="return(confirm('Esta Seguro'))">Eliminar</a></td>
                </tr>
            </c:forEach> 
        </table>
    </body>
</html>

