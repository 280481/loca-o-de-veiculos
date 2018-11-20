package br.com.rff.DAO;

import br.com.rff.model.Usuario;
import br.com.rff.services.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {

    private Connection conn;

    public DaoUsuario() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    public String Autenticar(Usuario user) {
        Usuario u = user;
        String sql = "select login, senha, nivel from usuario where login = ? and senha = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getSenha());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nivel");
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01 : " + ex.getMessage());
        }
        return null;
    }

}
