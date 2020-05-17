/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarma.dao.custom;

import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class Funcionario_EnderecoDAO {

    public ArrayList<Funcionario_Endereco> listar() throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Pessoa as p ");
        sql.append("INNER JOIN Funcionario as f ");
        sql.append("INNER JOIN Endereco as e ");
        sql.append("INNER JOIN Cidade as c ");
        sql.append("ON p.id = f.Pessoa_id AND p.id = e.Pessoa_id AND e.Cidade_id = c.id ");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        ResultSet r = exec.executeQuery();

        ArrayList<Funcionario_Endereco> lista = new ArrayList<>();
        Funcionario_Endereco fe;

        while (r.next()) {
            fe = new Funcionario_Endereco();
            fe.getFuncionario().setId(r.getInt(1));
            fe.getFuncionario().setNome(r.getString(2));
            fe.getFuncionario().setTel(r.getString(3));
            fe.getFuncionario().setEmail(r.getString(5));
            fe.getFuncionario().setSenha(r.getString(6));
            fe.getFuncionario().setAdmin(r.getBoolean(7));
            fe.getEndereco().setCep(r.getString(9));
            fe.getEndereco().setLogradouro(r.getString(10));
            fe.getEndereco().setNumero(r.getString(11));
            fe.getEndereco().setCompl(r.getString(12));
            fe.getEndereco().getCidade().setId(r.getInt(13));
            fe.getEndereco().getCidade().setNome(r.getString(15));
            fe.getEndereco().getCidade().setUf(r.getString(16));
            
            lista.add(fe);
        }
        
        return lista.isEmpty()? null:lista;
    }
}
