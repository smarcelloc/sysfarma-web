package br.com.sysfarma.dao;

import br.com.sysfarma.domain.Medic;
import br.com.sysfarma.factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicDAO {    
    public void inserir(Medic m) throws SQLException{
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO Medic");
        sql.append("(nome, preco, estoque, dt_validade, controlado, TipoMedic_id, Fornec_Pessoa_id) ");
        sql.append("VALUE(?,?,?,?,?,?,?)");
                
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setString(1, m.getNome());
        exec.setDouble(2, m.getPreco());
        exec.setShort(3, m.getEstoque());
        exec.setDate(4, m.getDt_validade());
        exec.setBoolean(5, m.getControlado());

        if(m.getTipoMedic() == null)
             exec.setString(6, null);
        else
            exec.setLong(6, m.getTipoMedic().getId());

        if(m.getFornec() == null)
            exec.setString(7, null);
        else
            exec.setLong(7, m.getFornec().getId());

        exec.executeUpdate();     
    }
    
    public void alterar(Medic m) throws SQLException{
        StringBuilder sql = new StringBuilder();
        
        sql.append("UPDATE Medic SET ");
        sql.append("nome = ?, ");
        sql.append("preco = ?, ");
        sql.append("estoque = ?, ");
        sql.append("dt_validade = ?, ");
        sql.append("controlado = ?, ");
        sql.append("TipoMedic_id = ?, ");
        sql.append("Fornec_Pessoa_id = ? ");
        sql.append("WHERE id = ?");
        
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setString(1, m.getNome());
        exec.setDouble(2, m.getPreco());
        exec.setShort(3, m.getEstoque());
        exec.setDate(4, m.getDt_validade());
        exec.setBoolean(5, m.getControlado());
        exec.setInt(6, m.getTipoMedic().getId());
        exec.setInt(7, m.getFornec().getId());
        exec.setInt(8, m.getId());
        exec.executeUpdate();
    }
    
    public void deletar(int id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        
        sql.append("DELETE FROM Medic ");
        sql.append("WHERE id = ?");
        
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setInt(1, id);
        exec.executeUpdate();
    }
    
    public Medic ConsultarPorId(int id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        TipoMedicDAO mtdao = new TipoMedicDAO();
        FornecDAO fdao = new FornecDAO();
        
        sql.append("SELECT * FROM Medic ");
        sql.append("WHERE id = ?");
        
        Connection conectar = Conexao.getConexao();
       PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setInt(1, id);
        ResultSet r = exec.executeQuery();

        if(r.next()){
            Medic m = new Medic();
            m.setId(r.getInt(1));
            m.setNome(r.getString(2));
            m.setPreco(r.getDouble(3));
            m.setEstoque(r.getShort(4));
            m.setDt_validade(r.getDate(5));
            m.setControlado(r.getBoolean(6));
            m.setTipoMedic(mtdao.consultarPorId(r.getInt(7)));
            m.setFornec(fdao.consultarPorId(r.getInt(8)));
            
            return m;
        }
        
        return null;
    }
    
    public ArrayList<Medic> listar() throws SQLException{
        TipoMedicDAO mtdao = new TipoMedicDAO();
        FornecDAO fdao = new FornecDAO();
        
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement("SELECT * FROM Medic;");
            
        ResultSet r = exec.executeQuery();
        ArrayList<Medic> list = new ArrayList<>();

        while(r.next()){
            Medic m = new Medic();
            m.setId(r.getInt(1));
            m.setNome(r.getString(2));
            m.setPreco(r.getDouble(3));
            m.setEstoque(r.getShort(4));
            m.setDt_validade(r.getDate(5));
            m.setControlado(r.getBoolean(6));
            m.setTipoMedic(mtdao.consultarPorId(r.getInt(7)));
            m.setFornec(fdao.consultarPorId(r.getInt(8)));
            list.add(m);
        }

        return list;
    }
    
    public void DeletarPorTipoMedic(int id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Medic SET ");
        sql.append("TipoMedic_id = null ");
        sql.append("WHERE TipoMedic_id = ? ");
        
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setInt(1, id);
        exec.executeUpdate();
    }
    
    public void DeletarPorFornec(int id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Medic SET ");
        sql.append("Fornec_Pessoa_id = null ");
        sql.append("WHERE Fornec_Pessoa_id = ? ");
        
        Connection conectar = Conexao.getConexao();
        PreparedStatement exec = conectar.prepareStatement(sql.toString());
            
        exec.setInt(1, id);
        exec.executeUpdate();
    }
}

