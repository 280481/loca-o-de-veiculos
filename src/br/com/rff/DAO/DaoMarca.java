/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.DAO;

import br.com.rff.interfaces.DAOInterface;
import br.com.rff.model.Marca;
import br.com.rff.services.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author refra
 */
public class DaoMarca implements DAOInterface {

    private Connection conn;

    public DaoMarca() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    @Override
    public void salvar(Object model) {
        if (model instanceof Marca) {
            Marca mar = (Marca) model;
            String sql = "update marca set titulo = ? where idmarca = ? ;";
            if (mar.getIdMarca() == -1) {
                sql = "insert into marca(titulo, idmarca) values(?,?);";
            }

            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setString(1, mar.getTitulo());
                ps.setInt(2, mar.getIdMarca());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> minhaLista = new ArrayList<>();
        String sql = "select * from marca;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                marca.setTitulo(rs.getString("titulo"));
                minhaLista.add(marca);
            } else {
                return null;
            }
            return minhaLista;

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public Object getByID(Object model) {
        if (model instanceof Marca && model != null ) {
            String sql = "select * from marca where idmarca = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                Marca marca = new Marca();
                ps.setInt(1, marca.getIdMarca());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    marca.setIdMarca(rs.getInt("idmarca"));
                    marca.setTitulo(rs.getString("titulo"));
                    return marca;
                } else {
                    return null;
                }

            } catch (SQLException ex) {
                System.out.println("#01");
            }
        }
        return null;
    }

    public void remover(Object model) {

        if (model instanceof Marca) {
            Marca marca = (Marca) model;
            String sql = "delete from marca where idmarca = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);

                ps.setInt(1, marca.getIdMarca());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getLast() {
        String sql = "select * from marca where idmarca = (select max(idmarca) from marca);";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                marca.setTitulo(rs.getString("titulo"));
                return marca;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }
}
