package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Endereco;
import br.com.sysfarma.domain.Cliente;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public void inserir(Cliente c, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("INSERT INTO Pessoa");
        sql.append("(nome, tel) ");
        sql.append("VALUE(?,?)");

        PreparedStatement exec = conectar.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

        exec.setString(1, c.getNome());
        exec.setString(2, c.getTel());
        exec.executeUpdate();

        ResultSet r = exec.getGeneratedKeys();

        if (r.next()) {
            c.setId(r.getInt(1));
            e.getPessoa().setId(r.getInt(1));
        }

        sql = new StringBuilder();

        sql.append("INSERT INTO Cliente");
        sql.append("(Pessoa_id, cpf, dt_nasc) ");
        sql.append("VALUE(?,?,?)");

        exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, c.getId());
        exec.setString(2, c.getCpf());
        exec.setDate(3, c.getDt_nasc());

        exec.executeUpdate();

        EnderecoDAO edao = new EnderecoDAO();
        edao.inserir(e);
    }

    public void alterar(Cliente c, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("UPDATE Pessoa SET ");
        sql.append("nome = ?, ");
        sql.append("tel = ? ");
        sql.append("WHERE id = ?");

        PreparedStatement exec = conectar.prepareStatement(sql.toString());
        exec.setString(1, c.getNome());
        exec.setString(2, c.getTel());
        exec.setInt(3, c.getId());

        exec.executeUpdate();

        sql = new StringBuilder();

        sql.append("UPDATE Cliente SET ");
        sql.append("cpf = ?, ");
        sql.append("dt_nasc = ? ");
        sql.append("WHERE Pessoa_id = ?");

        exec = conectar.prepareStatement(sql.toString());

        exec.setString(1, c.getCpf());
        exec.setDate(2, c.getDt_nasc());
        exec.setInt(3, c.getId());
        exec.executeUpdate();

        e.getPessoa().setId(c.getId());

        EnderecoDAO edao = new EnderecoDAO();
        edao.alterar(e);

    }

    public void deletar(Integer id_pessoa) throws SQLException {

        VendaDAO vdao = new VendaDAO();
        vdao.DeletarPorCliente(id_pessoa);

        EnderecoDAO edao = new EnderecoDAO();
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Cliente ");
        sql.append("WHERE Pessoa_id = ?");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, id_pessoa);
        exec.executeUpdate();

        edao.deletar(id_pessoa);

        sql = new StringBuilder();

        sql.append("DELETE FROM Pessoa ");
        sql.append("WHERE id = ?");

        exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, id_pessoa);
        exec.executeUpdate();

    }

    public boolean verificarCPF(Integer id, String cpf) throws SQLException {
        StringBuilder sql = new StringBuilder();

        if (id == null) {
            sql.append("SELECT Pessoa_id, cpf FROM Cliente ");
            sql.append("WHERE cpf = ?");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, cpf);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        } else {

            sql.append("SELECT Pessoa_id, cpf FROM Cliente ");
            sql.append("WHERE cpf = ? AND Pessoa_id != ? ");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, cpf);
            exec.setInt(2, id);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        }

        return false;
    }

    public Cliente consultarPorId(Integer id) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Pessoa as p ");
        sql.append("INNER JOIN Cliente as cl ");
        sql.append("ON p.id = ? AND cl.Pessoa_id = ? ");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, id);
        exec.setInt(2, id);

        ResultSet r = exec.executeQuery();
        if (r.next()) {
            Cliente cl = new Cliente();
            cl.setId(r.getInt(1));
            cl.setNome(r.getString(2));
            cl.setTel(r.getString(3));
            cl.setCpf(r.getString(5));
            cl.setDt_nasc(r.getDate(6));
            return cl;
        }

        return null;

    }
}
