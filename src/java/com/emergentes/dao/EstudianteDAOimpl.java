package com.emergentes.dao;

import com.emergentes.modelo.Estudiante;
import com.emergentes.utiles.ConexionBD4;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOimpl extends ConexionBD4 implements EstudianteDAO {

    @Override
    public void insert(Estudiante estudiante) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into estudiantes(nombre, apellidos, correo) values (?, ?, ?)");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCorreo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void update(Estudiante estudiante) throws Exception {

        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE estudiantes set nombre = ?, apellidos = ?, correo = ? where id_estudiante = ?");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCorreo());
            ps.setInt(4, estudiante.getId_estudiante());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM estudiantes WHERE id_estudiante = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public Estudiante getById(int id_estudiante) throws Exception {

        Estudiante est = new Estudiante();
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM estudiantes WHERE id_estudiante = ? ");
            ps.setInt(1, id_estudiante);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                est.setId_estudiante(rs.getInt("id_estudiante"));
                est.setNombre(rs.getString("nombre"));
                est.setApellidos(rs.getString("apellidos"));
                est.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
        return est;

    }

    @Override
    public List<Estudiante> getAll() throws Exception {

        List<Estudiante> lista = null;
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM estudiantes");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Estudiante>();
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId_estudiante(rs.getInt("id_estudiante"));
                est.setNombre(rs.getString("nombre"));
                est.setApellidos(rs.getString("apellidos"));
                est.setCorreo(rs.getString("correo"));
                lista.add(est);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
        return lista;
    }
}
