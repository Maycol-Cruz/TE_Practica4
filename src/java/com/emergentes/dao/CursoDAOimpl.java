package com.emergentes.dao;

import com.emergentes.modelo.Curso;
import com.emergentes.utiles.ConexionBD4;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOimpl extends ConexionBD4 implements CursoDAO {

    @Override
    public void insert(Curso curso) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into curso(descripcion) values (?)");
            ps.setString(1, curso.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void update(Curso curso) throws Exception {

        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE curso set descripcion = ? where id_curso = ?");
            ps.setString(1, curso.getDescripcion() );
            ps.setInt(2, curso.getId_curso() );
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void delete(int id_curso) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM curso WHERE id_curso = ?");
            ps.setInt(1, id_curso);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public Curso getById(int id_curso) throws Exception {

        Curso cur = new Curso();
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM curso WHERE id_curso = ? limit 1");
            ps.setInt(1, id_curso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cur.setId_curso(rs.getInt("id_curso"));
                cur.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
        return cur;
    }

    @Override
    public List<Curso> getAll() throws Exception {

        List<Curso> lista = null;
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM curso");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Curso>();
            while (rs.next()) {
                Curso cur = new Curso();
                cur.setId_curso(rs.getInt("id_curso"));
                cur.setDescripcion(rs.getString("descripcion"));
                lista.add(cur);
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