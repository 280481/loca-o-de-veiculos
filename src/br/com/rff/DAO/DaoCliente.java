/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.DAO;

import br.com.rff.interfaces.DAOInterface;
import br.com.rff.model.Cliente;
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
public class DaoCliente implements DAOInterface {

    private Connection conn;

    public DaoCliente() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    @Override
    public void salvar(Object model) {
        if (model instanceof Cliente) {
            Cliente cliente = (Cliente) model;
            String sql = "update cliente set nome = ? where idcliente=?;";
            if (cliente.getIdCliente() == -1) {
                sql = "insert into cliente(nome, idcliente) values(?,?);";
            }

            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setString(1, cliente.getNome());
                ps.setInt(2, cliente.getIdCliente());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<Object> getAll() {
        ArrayList<Object> minhaLista = new ArrayList<>();
        String sql = "select * from cliente;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                minhaLista.add(cliente);
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
        if (model instanceof Cliente && model != null) {
            String sql = "select * from cliente where idcliente = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                Cliente cliente = new Cliente();
                ps.setInt(1, cliente.getIdCliente());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    cliente.setIdCliente(rs.getInt("idcliente"));
                    cliente.setNome(rs.getString("nome"));

                    return cliente;
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
        if (model instanceof Cliente) {
            Cliente cliente = (Cliente) model;
            String sql = "delete from cliente where idcliente = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);

                ps.setInt(1, cliente.getIdCliente());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getLast() {
        String sql = "select * from cliente where idcliente = (select max(idcliente) from cliente);";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));

                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }
}
