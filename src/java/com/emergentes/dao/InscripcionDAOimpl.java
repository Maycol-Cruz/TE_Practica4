package com.emergentes.dao;

import com.emergentes.modelo.Inscripcion;
import com.emergentes.utiles.ConexionBD4;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOimpl extends ConexionBD4 implements InscripcionDAO {

    @Override
    public void insert(Inscripcion inscripcion) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into inscripciones (id_curso,id_estudiante,nota_final) values (?, ?, ?)");
            ps.setInt(1, inscripcion.getId_curso() );
            ps.setInt(2, inscripcion.getId_estudiante());
            ps.setInt(3, inscripcion.getNota_final());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void update(Inscripcion inscripcion) throws Exception {

        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE inscripciones set id_curso = ?, id_estudiante = ?, nota_final = ? where id_inscripcion = ?");
            ps.setInt(1, inscripcion.getId_curso() );
            ps.setInt(2, inscripcion.getId_estudiante());
            ps.setInt(3, inscripcion.getNota_final());
            ps.setInt(4, inscripcion.getId_inscripcion() );
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public void delete(int id_inscripcion) throws Exception {
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM inscripciones WHERE id_inscripcion = ?");
            ps.setInt(1, id_inscripcion);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
    }

    @Override
    public Inscripcion getById(int id_inscripcion) throws Exception {

        Inscripcion ins = new Inscripcion();
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("select * from inscripciones WHERE id_inscripcion = ?");
            ps.setInt(1, id_inscripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ins.setId_inscripcion(rs.getInt("id_inscripcion"));
                ins.setId_curso(rs.getInt("id_curso"));
                ins.setId_estudiante(rs.getInt("id_estudiante"));
                ins.setNota_final(rs.getInt("nota_final"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar4();
        }
        return ins;

    }

    @Override
    public List<Inscripcion> getAll() throws Exception {

        List<Inscripcion> lista = null;
        try {
            this.conectar4();
            PreparedStatement ps = this.conn.prepareStatement("select * FROM inscripciones");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Inscripcion>();
            while (rs.next()) {
                Inscripcion ins = new Inscripcion();
                ins.setId_inscripcion(rs.getInt("id_inscripcion"));
                ins.setId_curso(rs.getInt("id_curso"));
                ins.setId_estudiante(rs.getInt("id_estudiante"));
                ins.setNota_final(rs.getInt("nota_final"));
                lista.add(ins);
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
