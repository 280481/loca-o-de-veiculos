/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rff.DAO;

import br.com.rff.interfaces.DAOInterface;
import br.com.rff.model.Automovel;
import br.com.rff.model.Modelo;
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
public class DaoAutomovel implements DAOInterface {

    private Connection conn;

    public DaoAutomovel() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    @Override
    public void salvar(Object model) {
        if (model instanceof Automovel) {
            Automovel auto = (Automovel) model;
            String sql = "update automovel set idModelo = ?, placa = ?, cor = ?, "
                    + " ano = ?, tipoCombustivel = ?, kmAtual = ?, renavam = ?, "
                    + "chasis = ?, vlLocacaoHora = ?, vlLocacaoKm = ?, situacao = ? where idAutomovel = ? ;";
            if (auto.getIdAutomovel() == -1) {
                sql = "insert into automovel(idModelo, placa, cor, ano,"
                        + "tipoCombustivel, kmAtual, renavam, chasis,"
                        + " vlLocacaoHora, vlLocacaoKm, situacao, idAutomovel) values(?,?,?,?,?,?,?,?,?,?,?,?);";
            }

            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setInt(1, auto.getModelo().getIdModelo());
                ps.setString(2, auto.getPlaca());
                ps.setString(3, auto.getCor());
                ps.setString(4, auto.getAno());
                ps.setString(5, auto.getTipoCombustivel());
                ps.setDouble(6, auto.getKmAtual());
                ps.setString(7, auto.getRenavam());
                ps.setString(8, auto.getChasis());
                ps.setDouble(9, auto.getVlLocacaoHora());
                ps.setDouble(10, auto.getVlLocacaoKm());
                ps.setString(11, auto.getSituacao());
                ps.setInt(12, auto.getIdAutomovel());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Model Incorreto");
        }
    }

    public ArrayList<Object> getAll() {
        ArrayList<Object> minhaLista = new ArrayList<>();
        String sql = "select * from automovel;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Automovel auto = new Automovel();
                auto.setAno(rs.getString("ano"));
                auto.setChasis(rs.getString("chasis"));
                auto.setCor(rs.getString("cor"));
                auto.setKmAtual(rs.getDouble("kmatual"));
                auto.setPlaca(rs.getString("placa"));
                auto.setRenavam(rs.getString("renavam"));
                auto.setSituacao(rs.getString("situacao"));
                auto.setTipoCombustivel(rs.getString("tipocombustivel"));
                auto.setVlLocacaoHora(rs.getDouble("vllocacaohora"));
                auto.setVlLocacaoKm(rs.getDouble("vllocacaokm"));
                auto.setIdAutomovel(rs.getInt("idautomovel"));

                DaoModelo modelo = new DaoModelo();
                auto.setModelo((Modelo) modelo.getByID(rs.getInt("idmodelo")));

                minhaLista.add(auto);
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
        if (model instanceof Automovel && model != null) {
            String sql = "select * from automovel where idautomovel = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                Automovel auto = (Automovel) model;
                ps.setInt(1, auto.getIdAutomovel());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    auto.setAno(rs.getString("ano"));
                    auto.setChasis(rs.getString("chasis"));
                    auto.setCor(rs.getString("cor"));
                    auto.setKmAtual(rs.getDouble("kmatual"));
                    auto.setPlaca(rs.getString("placa"));
                    auto.setRenavam(rs.getString("renavam"));
                    auto.setSituacao(rs.getString("situacao"));
                    auto.setTipoCombustivel(rs.getString("tipocombustivel"));
                    auto.setVlLocacaoHora(rs.getDouble("vllocacaohora"));
                    auto.setVlLocacaoKm(rs.getDouble("vllocacaokm"));
                    auto.setIdAutomovel(rs.getInt("idautomovel"));

                    DaoModelo modelo = new DaoModelo();
                    auto.setModelo((Modelo) modelo.getByID(rs.getInt("idmodelo")));
                    return auto;
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
        if (model instanceof Automovel) {
            Automovel auto = (Automovel) model;
            String sql = "delete from automovel where idautomovel = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);

                ps.setInt(1, auto.getIdAutomovel());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getLast() {
        String sql = "select * from automovel where idautomovel = (select max(idautomovel) from automovel);";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Automovel auto = new Automovel();
                auto.setAno(rs.getString("ano"));
                auto.setChasis(rs.getString("chasis"));
                auto.setCor(rs.getString("cor"));
                auto.setKmAtual(rs.getDouble("kmatual"));
                auto.setPlaca(rs.getString("placa"));
                auto.setRenavam(rs.getString("renavam"));
                auto.setSituacao(rs.getString("situacao"));
                auto.setTipoCombustivel(rs.getString("tipocombustivel"));
                auto.setVlLocacaoHora(rs.getDouble("vllocacaohora"));
                auto.setVlLocacaoKm(rs.getDouble("vllocacaokm"));
                auto.setIdAutomovel(rs.getInt("idautomovel"));

                DaoModelo modelo = new DaoModelo();
                auto.setModelo((Modelo) modelo.getByID(rs.getInt("idmodelo")));
                return auto;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }
}
