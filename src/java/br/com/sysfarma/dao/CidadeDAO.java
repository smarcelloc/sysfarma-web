package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Cidade;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CidadeDAO {

    public void inserir(Cidade c) throws SQLException {
        Cidade c2 = consultarPorNomeUF(c);

        if (c2 != null) {
            c.setId(c2.getId());
        } else {

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO Cidade");
            sql.append("(nome, uf) ");
            sql.append("VALUE(?,?)");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            exec.setString(1, c.getNome());
            exec.setString(2, c.getUf());

            exec.executeUpdate();

            ResultSet r = exec.getGeneratedKeys();

            if (r.next()) {
                c.setId(r.getInt(1));
            }

        }
    }

    public Cidade consultarPorNomeUF(Cidade c) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Cidade ");
        sql.append("WHERE nome = ? AND uf = ?");

        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        exec.setString(1, c.getNome());
        exec.setString(2, c.getUf());
        ResultSet r = exec.executeQuery();

        if (r.next()) {
            c.setId(r.getInt(1));
            return c;
        }

        return null;
    }

}
