package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Endereco;
import br.com.sysfarma.domain.Fornec;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecDAO {

    public void inserir(Fornec fo, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("INSERT INTO Pessoa");
        sql.append("(nome, tel) ");
        sql.append("VALUE(?,?)");

        PreparedStatement exec = conectar.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

        exec.setString(1, fo.getNome());
        exec.setString(2, fo.getTel());
        exec.executeUpdate();

        ResultSet r = exec.getGeneratedKeys();

        if (r.next()) {
            fo.setId(r.getInt(1));
            e.getPessoa().setId(r.getInt(1));
        }

        sql = new StringBuilder();

        sql.append("INSERT INTO Fornec");
        sql.append("(Pessoa_id, cnpj, ramal) ");
        sql.append("VALUE(?,?,?)");

        exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, fo.getId());
        exec.setString(2, fo.getCnpj());
        exec.setString(3, fo.getRamal());

        exec.executeUpdate();

        EnderecoDAO edao = new EnderecoDAO();
        edao.inserir(e);
    }

    public void alterar(Fornec fo, Endereco e) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Connection conectar = Conexao.getConexao();

        sql.append("UPDATE Pessoa SET ");
        sql.append("nome = ?, ");
        sql.append("tel = ? ");
        sql.append("WHERE id = ?");

        PreparedStatement exec = conectar.prepareStatement(sql.toString());
        exec.setString(1, fo.getNome());
        exec.setString(2, fo.getTel());
        exec.setInt(3, fo.getId());

        exec.executeUpdate();

        sql = new StringBuilder();

        sql.append("UPDATE Fornec SET ");
        sql.append("cnpj = ?, ");
        sql.append("ramal = ? ");
        sql.append("WHERE Pessoa_id = ?");

        exec = conectar.prepareStatement(sql.toString());

        exec.setString(1, fo.getCnpj());
        exec.setString(2, fo.getRamal());
        exec.setInt(3, fo.getId());
        exec.executeUpdate();

        e.getPessoa().setId(fo.getId());

        EnderecoDAO edao = new EnderecoDAO();
        edao.alterar(e);

    }

    public void deletar(Integer id_pessoa) throws SQLException {
        EnderecoDAO edao = new EnderecoDAO();
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Fornec ");
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

    public boolean verificarCNPJ(Integer id, String cnpj) throws SQLException {
        StringBuilder sql = new StringBuilder();

        if (id == null) {
            sql.append("SELECT Pessoa_id, cnpj FROM Fornec ");
            sql.append("WHERE cnpj = ?");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, cnpj);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        } else {

            sql.append("SELECT Pessoa_id, cnpj FROM Fornec ");
            sql.append("WHERE cnpj = ? AND Pessoa_id != ? ");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, cnpj);
            exec.setInt(2, id);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        }

        return false;
    }
    
    
     public boolean verificarNome(Integer id, String nome) throws SQLException {
        StringBuilder sql = new StringBuilder();

        if (id == null) {
            sql.append("SELECT p.nome, p.id, f.Pessoa_id FROM Fornec as f ");
            sql.append("INNER JOIN Pessoa as p ");
            sql.append("ON p.id = f.Pessoa_id AND p.nome = ? ");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            exec.setString(1, nome);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        } else {

            sql.append("SELECT p.nome, p.id, f.Pessoa_id FROM Fornec as f ");
            sql.append("INNER JOIN Pessoa as p ");
            sql.append("ON p.id = f.Pessoa_id AND  p.nome = ? AND p.id = ?");

            Connection conectar = Conexao.getConexao();

            PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
            exec.setInt(1, id);
            exec.setString(2, nome);
            ResultSet r = exec.executeQuery();

            if (!r.next()) {
                return true;
            }
        }

        return false;
    }
    

    public Fornec consultarPorId(Integer id) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT p.*, f.* FROM Fornec as f ");
        sql.append("INNER JOIN Pessoa as p ");

        sql.append("ON f.Pessoa_id = ? AND p.id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setInt(1, id);
        exec.setInt(2, id);
        ResultSet r = exec.executeQuery();

        if (r.next()) {
            Fornec f = new Fornec();
            f.setId(r.getInt(1));
            f.setNome(r.getString(2));
            f.setTel(r.getString(3));
            f.setCnpj(r.getString(5));
            f.setRamal(r.getString(6));
            
            return f;
        }

        return null;
    }
}
