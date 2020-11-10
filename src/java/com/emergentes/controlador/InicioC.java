package com.emergentes.controlador;

import com.emergentes.dao.CursoDAO;
import com.emergentes.dao.CursoDAOimpl;
import com.emergentes.modelo.Curso;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InicioC", urlPatterns = {"/inicioC"})
public class InicioC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CursoDAO dao = new CursoDAOimpl();
            int id;
            Curso cur = new Curso();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("curso", cur);
                    request.getRequestDispatcher("frmcurso.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id_curso"));
                    cur = dao.getById(id);
                    System.out.println(cur);
                    request.setAttribute("curso", cur);
                    request.getRequestDispatcher("frmcurso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id_curso"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicioC");
                    break;
                case "view":
                    List<Curso> lista = dao.getAll();
                    request.setAttribute("curso", lista);
                    request.getRequestDispatcher("indexC.jsp").forward(request, response);
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
        int id_curso = Integer.parseInt(request.getParameter("id_curso"));
        String descripcion = request.getParameter("descripcion");

        Curso cur = new Curso();

        cur.setId_curso(id_curso);
        cur.setDescripcion(descripcion);
        if (id_curso == 0) {
            try {
                CursoDAO dao = new CursoDAOimpl();
                dao.insert(cur);
                response.sendRedirect(request.getContextPath() + "/inicioC");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                CursoDAO dao = new CursoDAOimpl();
                dao.update(cur);
                response.sendRedirect(request.getContextPath() + "/inicioC");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}