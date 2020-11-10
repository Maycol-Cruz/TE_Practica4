<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="${inscripcion.id_inscripcion == 0}">Nuevo</c:if>
            <c:if test="${inscripcion.id_inscripcion != 0}">Editar</c:if>
                inscripcion
            </h1>
            <form action="inicioI" method="post">
                <input type="hidden" name="id_inscripcion" value="${inscripcion.id_inscripcion}"/>
            <table>
                <tr>
                    <td>Materia:</td>
                    <td>
                       <td><input type="text" name="id_curso" value="${inscripcion.id_curso}"/></td>  
                    </td>
                </tr>
                <tr>
                    <td>Estudiante:</td>
                    <td>
                       <td><input type="text" name="id_estudiante" value="${inscripcion.id_estudiante}"/></td> 
                    </td>
                
                    <td>Nota:</td>
                    <td><input type="text" name="nota_final" value="${inscripcion.nota_final}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"/></td>
                </tr>
            </table>
        </form>    
    </body>
</html>
