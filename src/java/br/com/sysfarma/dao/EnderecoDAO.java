package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Endereco;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {

    public void inserir(Endereco e) throws SQLException {
        
        CidadeDAO cdao = new CidadeDAO();
        
        cdao.inserir(e.getCidade());
        
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO Endereco");
        sql.append("(Pessoa_id, cep, logradouro, numero, compl, Cidade_id) ");
        sql.append("VALUE(?,?,?,?,?,?)");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        exec.setInt(1, e.getPessoa().getId());
        exec.setString(2, e.getCep());
        exec.setString(3, e.getLogradouro());
        exec.setString(4, e.getNumero());
        exec.setString(5, e.getCompl());
        exec.setInt(6, e.getCidade().getId());
        exec.executeUpdate();
    }

    public void alterar(Endereco e) throws SQLException {
        
        CidadeDAO cdao = new CidadeDAO();
        
        cdao.inserir(e.getCidade());
        
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE Endereco SET ");
        sql.append("cep = ?, ");
        sql.append("logradouro = ?, ");
        sql.append("numero = ?, ");
        sql.append("compl = ?, ");
        sql.append("Cidade_id = ? ");
        sql.append("WHERE Pessoa_id = ?");

        Connection conexao = Conexao.getConexao();
        PreparedStatement exec = conexao.prepareStatement(sql.toString());

        exec.setString(1, e.getCep());
        exec.setString(2, e.getLogradouro());
        exec.setString(3, e.getNumero());
        exec.setString(4, e.getCompl());
        exec.setInt(5, e.getCidade().getId());
        exec.setInt(6, e.getPessoa().getId());
        exec.executeUpdate();
    }

    public void deletar(Integer id_pessoa) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM Endereco ");
        sql.append("WHERE Pessoa_id = ?");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setInt(1, id_pessoa);
        exec.executeUpdate();
    }
}
