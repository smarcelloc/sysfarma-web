package br.com.sysfarma.bean;

import br.com.sysfarma.domain.Medic;
import br.com.sysfarma.dao.MedicDAO;
import br.com.sysfarma.dao.TipoMedicDAO;
import br.com.sysfarma.dao.custom.Fornec_Endereco;
import br.com.sysfarma.dao.custom.Fornec_EnderecoDAO;
import br.com.sysfarma.domain.TipoMedic;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBMedic")
@ViewScoped
public class MedicBean {

    private Medic m;
    private ArrayList<Medic> itens;
    private ArrayList<Medic> itensFiltro;
    private ArrayList<TipoMedic> comboTM;
    private ArrayList<Fornec_Endereco> comboFOE;

    public ArrayList<Fornec_Endereco> getComboFOE() {
        return comboFOE;
    }

    public void setComboFOE(ArrayList<Fornec_Endereco> comboFOE) {
        this.comboFOE = comboFOE;
    }

    public Medic getM() {
        return m;
    }

    public void setM(Medic m) {
        this.m = m;
    }

    public ArrayList<Medic> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Medic> itens) {
        this.itens = itens;
    }

    public ArrayList<Medic> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<Medic> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public ArrayList<TipoMedic> getComboTM() {
        return comboTM;
    }

    public void setComboTM(ArrayList<TipoMedic> comboTM) {
        this.comboTM = comboTM;
    }

    //Preparar 
    
    public void carregarListagem() {
        try {
            TipoMedicDAO tmdao = new TipoMedicDAO();
            Fornec_EnderecoDAO foedao = new Fornec_EnderecoDAO();
            comboFOE = foedao.listar();
            comboTM = tmdao.listar();
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

    }
    
    public void prepararNovo() {
        m = new Medic();
    }
    
    public void prepararEditar(){
        m = new Medic();
        carregarListagem();
    }


    @PostConstruct
    public void init() {
        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }
        
        try {
            MedicDAO mdao = new MedicDAO();
            itens = mdao.listar();
            m = new Medic();
            carregarListagem();

        } catch (SQLException ex) {
            UrlPagina.redirecionar("erro/db.xhtml");
        }
    }

    //Novo
    public void novoEditar(boolean editar) {
        MedicDAO mdao = new MedicDAO();

        try {

            if (editar == false) {
                mdao.inserir(m);
                Mensagem.sucesso("Cadastramos com sucesso.");
            } else {
                mdao.alterar(m);
                Mensagem.sucesso("Atualizamos com sucesso.");
            }

        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
    }

    public void excluir() {
        MedicDAO mdao = new MedicDAO();

        try {
            mdao.deletar(m.getId());
            Mensagem.sucesso("Deletamos com sucesso!");
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
    }

}
