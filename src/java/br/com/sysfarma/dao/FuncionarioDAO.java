package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Endereco;
import br.com.sysfarma.domain.Funcionario;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    private static final String TOKEN_SENHA = "TGIpX2PcuxgYh9Wchwzvmg5d5mMqo6VR";

    public void inserir(Funcionario f, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("INSERT INTO Pessoa");
        sql.append("(nome, tel) ");
        sql.append("VALUE(?,?)");

        PreparedStatement exec = conectar.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

        exec.setString(1, f.getNome());
        exec.setString(2, f.getTel());
        exec.executeUpdate();

        ResultSet r = exec.getGeneratedKeys();

        if (r.next()) {
            f.setId(r.getInt(1));
            e.getPessoa().setId(r.getInt(1));
        }

        sql = new StringBuilder();

        sql.append("INSERT INTO Funcionario");
        sql.append("(Pessoa_id, email, senha, admin) ");
        sql.append("VALUE(?,?,MD5(?),?)");

        exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, f.getId());
        exec.setString(2, f.getEmail());
        exec.setString(3, f.getSenha() + TOKEN_SENHA);
        exec.setBoolean(4, f.getAdmin());

        exec.executeUpdate();

        EnderecoDAO edao = new EnderecoDAO();
        edao.inserir(e);
    }

    public void alterar(Funcionario f, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("UPDATE Pessoa SET ");
        sql.append("nome = ?, ");
        sql.append("tel = ? ");
        sql.append("WHERE id = ?");

        PreparedStatement exec = conectar.prepareStatement(sql.toString());
        exec.setString(1, f.getNome());
        exec.setString(2, f.getTel());
        exec.setInt(3, f.getId());

        exec.executeUpdate();

        sql = new StringBuilder();

        sql.append("UPDATE Funcionario SET ");
        sql.append("email = ?, ");
        sql.append("admin = ? ");
        sql.append("WHERE Pessoa_id = ?");

        exec = conectar.prepareStatement(sql.toString());

        exec.setString(1, f.getEmail());
        exec.setBoolean(2, f.getAdmin());
        exec.setInt(3, f.getId());
        exec.executeUpdate();

        e.getPessoa().setId(f.getId());

        EnderecoDAO edao = new EnderecoDAO();
        edao.alterar(e);

    }

    public void deletar(Integer id_pessoa) throws SQLException {
        EnderecoDAO edao = new EnderecoDAO();
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Funcionario ");
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

    public void alterarSenha(Integer pessoa_id, String senha_nova) throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE Funcionario SET ");
        sql.append("senha = MD5(?) ");
        sql.append("WHERE Pessoa_id = ?");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        exec.setString(1, senha_nova + TOKEN_SENHA);
        exec.setInt(2, pessoa_id);
        exec.executeUpdate();
    }

    public boolean verificarEmail(Integer id, String email) throws SQLException {
        StringBuilder sql = new StringBuilder();

        if (id == null) {
            sql.append("SELECT Pessoa_id, email FROM Funcionario ");
            sql.append("WHERE email = ?");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, email);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        } else {

            sql.append("SELECT Pessoa_id, email FROM Funcionario ");
            sql.append("WHERE email = ? AND Pessoa_id != ? ");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, email);
            exec.setInt(2, id);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        }

        return false;
    }

    public boolean autenticarLogin(String email, String senha) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT email, senha FROM funcionario ");
        sql.append("WHERE email = ? AND senha = MD5(?)");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());
        exec.setString(1, email);
        exec.setString(2, senha + TOKEN_SENHA);
        ResultSet r = exec.executeQuery();

        return !r.next();
    }
}
