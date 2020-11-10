package com.emergentes.controlador;

import com.emergentes.dao.EstudianteDAO;
import com.emergentes.dao.EstudianteDAOimpl;
import com.emergentes.modelo.Estudiante;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InicioE", urlPatterns = {"/inicioE"})
public class InicioE extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EstudianteDAO dao = new EstudianteDAOimpl();
            int id;
            Estudiante est = new Estudiante();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("estudiante", est);
                    request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id_estudiante"));
                    est = dao.getById(id);
                    System.out.println(est);
                    request.setAttribute("estudiante", est);
                    request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id_estudiante"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicioE");
                    break;
                case "view":
                    List<Estudiante> lista = dao.getAll();
                    request.setAttribute("estudiantes", lista);
                    request.getRequestDispatcher("indexE.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_estudiante = Integer.parseInt(request.getParameter("id_estudiante"));
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");

        Estudiante est = new Estudiante();

        est.setId_estudiante(id_estudiante);
        est.setNombre(nombre);
        est.setApellidos(apellidos);
        est.setCorreo(correo);

        if (id_estudiante == 0) {
            try {
                EstudianteDAO dao = new EstudianteDAOimpl();
                dao.insert(est);
                response.sendRedirect(request.getContextPath() + "/inicioE");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                EstudianteDAO dao = new EstudianteDAOimpl();
                dao.update(est);
                response.sendRedirect(request.getContextPath() + "/inicioE");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}

