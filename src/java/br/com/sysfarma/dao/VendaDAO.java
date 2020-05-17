package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Venda;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaDAO {

    public void inserir(Venda v) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO Venda");
        sql.append("(total, dt_vendida, pgto, Cliente_Pessoa_id) ");
        sql.append("VALUE(?,?,?,?)");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setDouble(1, v.getTotal());
        exec.setDate(2, v.getDt_vendida());
        exec.setString(3, v.getPgto());
        exec.setInt(4, v.getCliente().getId());
        exec.executeUpdate();
    }

    public void alterar(Venda v) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE Venda SET ");
        sql.append("total = ?, ");
        sql.append("dt_vendida = ?, ");
        sql.append("pgto = ?, ");
        sql.append("Cliente_Pessoa_id = ? ");
        sql.append("WHERE id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setDouble(1, v.getTotal());
        exec.setDate(2, v.getDt_vendida());
        exec.setString(3, v.getPgto());
        exec.setInt(4, v.getCliente().getId());
        exec.setInt(5, v.getId());
        exec.executeUpdate();
    }

    public void deletar(Integer id) throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Venda ");
        sql.append("WHERE id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setInt(1, id);
        exec.executeUpdate();

    }

    public Venda consultarPorId(Integer id) throws SQLException {
        StringBuilder sql = new StringBuilder();
        ClienteDAO cdao = new ClienteDAO();

        sql.append("SELECT * FROM Venda ");
        sql.append("WHERE id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setInt(1, id);
        ResultSet r = exec.executeQuery();

        if (r.next()) {
            Venda v = new Venda();
            ClienteDAO cl = new ClienteDAO();

            v.setId(1);
            v.setTotal(r.getDouble(2));
            v.setDt_vendida(r.getDate(3));
            v.setPgto(r.getString(4));
            v.setCliente(cl.consultarPorId(r.getInt(5)));

            return v;

        }

        return null;
    }

    public ArrayList<Venda> listar() throws SQLException {

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement("SELECT * FROM Venda");

        ResultSet r = exec.executeQuery();
        ArrayList<Venda> list = new ArrayList<>();

        while (r.next()) {
            Venda v = new Venda();
            ClienteDAO cl = new ClienteDAO();

            v.setId(r.getInt(1));
            v.setTotal(r.getDouble(2));
            v.setDt_vendida(r.getDate(3));
            v.setPgto(r.getString(4));
            v.setCliente(cl.consultarPorId(r.getInt(5)));

            list.add(v);

        }

        return list;
    }

    public void DeletarPorCliente(Integer pessoa_id) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Venda ");
        sql.append("WHERE Cliente_Pessoa_id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setInt(1, pessoa_id);
        exec.executeUpdate();
    }
}
