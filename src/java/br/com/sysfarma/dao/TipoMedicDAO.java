package br.com.sysfarma.dao;

import br.com.sysfarma.domain.TipoMedic;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoMedicDAO {

    public void inserir(TipoMedic tm) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO TipoMedic");
        sql.append("(nome) ");
        sql.append("VALUE(?)");

        Connection connec = Conexao.getConexao();
        PreparedStatement exec = connec.prepareStatement(sql.toString());

        exec.setString(1, tm.getNome());
        exec.executeUpdate();
    }

   public void alterar(TipoMedic tm) throws SQLException {
       StringBuilder sql = new StringBuilder();
       
       sql.append("UPDATE TipoMedic SET ");
       sql.append("nome = ? ");
       sql.append("WHERE id = ?");
       
       Connection connec = Conexao.getConexao();
       PreparedStatement exec = connec.prepareStatement(sql.toString());
       
       exec.setString(1, tm.getNome());
       exec.setInt(2, tm.getId());
       exec.executeUpdate();
   }
   
   public void deletar(int id) throws SQLException {
       StringBuilder sql = new StringBuilder();
       MedicDAO mdao = new MedicDAO();

       sql.append("DELETE FROM TipoMedic ");
       sql.append("WHERE id = ?");

       mdao.DeletarPorTipoMedic(id);
       Connection connec = Conexao.getConexao();
       PreparedStatement exec = connec.prepareStatement(sql.toString());
       
       exec.setInt(1, id);
       exec.executeUpdate();
   }
   
   public TipoMedic consultarPorId(int id) throws SQLException {
       StringBuilder sql = new StringBuilder();
       
       sql.append("SELECT * FROM TipoMedic ");
       sql.append("WHERE id = ?");
       
       Connection connec = Conexao.getConexao();
       PreparedStatement exec = connec.prepareStatement(sql.toString());
       
       exec.setInt(1, id);
       ResultSet r = exec.executeQuery();
       
       if (r.next()) {
           TipoMedic tm = new TipoMedic();
           tm.setId(id);
           tm.setNome(r.getString(2));
           return tm;
       }
       
       return null;
   }
   
   public boolean verificarNome(Integer id, String nome) throws SQLException {
       
       if (id == null) {
           StringBuilder sql = new StringBuilder();
           sql.append("SELECT * FROM TipoMedic ");
           sql.append("WHERE nome = ?");
           
           Connection connec = Conexao.getConexao();
           PreparedStatement exec = connec.prepareStatement(sql.toString());
           
           exec.setString(1, nome);
           ResultSet r = exec.executeQuery();
           
           if (r.next()) {
               return true;
           }
       } else {
           StringBuilder sql = new StringBuilder();
           sql.append("SELECT * FROM TipoMedic ");
           sql.append("WHERE nome = ? AND id != ?");
           
           Connection connec = Conexao.getConexao();
           PreparedStatement exec = connec.prepareStatement(sql.toString());
           
           exec.setString(1, nome);
           exec.setInt(2, id);
           ResultSet r = exec.executeQuery();
           
           if (r.next()) {
               return true;
           }
       }
       
       return false;
   }
   
   public ArrayList<TipoMedic> listar() throws SQLException {
       
       Connection connec = Conexao.getConexao();
       PreparedStatement exec = connec.prepareStatement("SELECT * FROM TipoMedic");
       
       ResultSet r = exec.executeQuery();
       ArrayList<TipoMedic> list = new ArrayList<>();
       
       while (r.next()) {
           TipoMedic tm = new TipoMedic();
           tm.setId(r.getInt(1));
           tm.setNome(r.getString(2));
           
           list.add(tm);
       }
       
       return list;
   }

}
