package br.com.sysfarma.dao.custom;

import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fornec_EnderecoDAO {

    public ArrayList<Fornec_Endereco> listar() throws SQLException {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Pessoa as p ");
        sql.append("INNER JOIN Fornec as fo ");
        sql.append("INNER JOIN Endereco as e ");
        sql.append("INNER JOIN Cidade as c ");
        sql.append("ON p.id = fo.Pessoa_id AND p.id = e.Pessoa_id AND e.Cidade_id = c.id ");

        Connection conectar = Conexao.getConexao();

        PreparedStatement exec = conectar.prepareStatement(sql.toString());

        ResultSet r = exec.executeQuery();

        ArrayList<Fornec_Endereco> lista = new ArrayList<>();
        Fornec_Endereco foe;

        while (r.next()) {
            foe = new Fornec_Endereco();
            foe.getFornec().setId(r.getInt(1));
            foe.getFornec().setNome(r.getString(2));
            foe.getFornec().setTel(r.getString(3));
            foe.getFornec().setCnpj(r.getString(5));
            foe.getFornec().setRamal(r.getString(6));
            foe.getEndereco().setCep(r.getString(8));
            foe.getEndereco().setLogradouro(r.getString(9));
            foe.getEndereco().setNumero(r.getString(10));
            foe.getEndereco().setCompl(r.getString(11));
            foe.getEndereco().getCidade().setId(r.getInt(13));
            foe.getEndereco().getCidade().setNome(r.getString(14));
            foe.getEndereco().getCidade().setUf(r.getString(15));

            lista.add(foe);
        }

        return lista.isEmpty() ? null : lista;
    }
}
