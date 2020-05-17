package br.com.sysfarma.dao.custom;

import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente_EnderecoDAO {

    public ArrayList<Cliente_Endereco> listar() throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Pessoa as p ");
        sql.append("INNER JOIN Cliente as cl ");
        sql.append("INNER JOIN Endereco as e ");
        sql.append("INNER JOIN Cidade as c ");
        sql.append("ON p.id = cl.Pessoa_id AND p.id = e.Pessoa_id AND e.Cidade_id = c.id ");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        ResultSet r = exec.executeQuery();

        ArrayList<Cliente_Endereco> lista = new ArrayList<>();
        Cliente_Endereco fe;

        while (r.next()) {
            fe = new Cliente_Endereco();
            fe.getCliente().setId(r.getInt(1));
            fe.getCliente().setNome(r.getString(2));
            fe.getCliente().setTel(r.getString(3));
            fe.getCliente().setCpf(r.getString(5));
            fe.getCliente().setDt_nasc(r.getDate(6));
            fe.getEndereco().setCep(r.getString(8));
            fe.getEndereco().setLogradouro(r.getString(9));
            fe.getEndereco().setNumero(r.getString(10));
            fe.getEndereco().setCompl(r.getString(11));
            fe.getEndereco().getCidade().setId(r.getInt(13));
            fe.getEndereco().getCidade().setNome(r.getString(14));
            fe.getEndereco().getCidade().setUf(r.getString(15));
            
            lista.add(fe);
        }
        
        return lista.isEmpty()? null:lista;
    }
}
