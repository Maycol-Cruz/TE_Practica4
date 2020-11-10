package com.emergentes.controlador;

import com.emergentes.dao.InscripcionDAO;
import com.emergentes.dao.InscripcionDAOimpl;
import com.emergentes.modelo.Inscripcion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InicioI", urlPatterns = {"/inicioI"})
public class InicioI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            InscripcionDAO dao = new InscripcionDAOimpl();
            int id;
            Inscripcion ins = new Inscripcion();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("inscripcion", ins);
                    request.getRequestDispatcher("frminscripcion.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id_inscripcion"));
                    ins = dao.getById(id);
                    System.out.println(ins);
                    request.setAttribute("inscripcion", ins);
                    request.getRequestDispatcher("frminscripcion.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id_inscripcion"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicioI");
                    break;
                case "view":
                    List<Inscripcion> lista = dao.getAll();
                    request.setAttribute("inscripciones", lista);
                    request.getRequestDispatcher("indexI.jsp").forward(request, response);
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
        int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
        int id_curso = Integer.parseInt(request.getParameter("id_curso"));
        int id_estudiante = Integer.parseInt(request.getParameter("id_estudiante"));
        int nota_final = Integer.parseInt(request.getParameter("nota_final"));
        

        Inscripcion ins = new Inscripcion();

        
        ins.setId_inscripcion(id_inscripcion);
        ins.setId_curso(id_curso);
        ins.setId_estudiante(id_estudiante);
        ins.setNota_final(nota_final);
        if (id_inscripcion == 0) {
            try {
                InscripcionDAO dao = new InscripcionDAOimpl();
                dao.insert(ins);
                response.sendRedirect(request.getContextPath() + "/inicioI");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                InscripcionDAO dao = new InscripcionDAOimpl();
                dao.update(ins);
                response.sendRedirect(request.getContextPath() + "/inicioI");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}