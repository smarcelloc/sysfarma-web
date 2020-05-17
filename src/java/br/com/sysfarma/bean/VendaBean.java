package br.com.sysfarma.bean;

import br.com.sysfarma.domain.Venda;
import br.com.sysfarma.dao.VendaDAO;
import br.com.sysfarma.dao.custom.Cliente_Endereco;
import br.com.sysfarma.dao.custom.Cliente_EnderecoDAO;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBVenda")
@ViewScoped
public class VendaBean {

    private Venda v;
    private ArrayList<Venda> itens;
    private ArrayList<Venda> itensFiltro;
    private ArrayList<Cliente_Endereco> comboCE;

    public Venda getV() {
        return v;
    }

    public void setV(Venda v) {
        this.v = v;
    }

    public ArrayList<Venda> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Venda> itens) {
        this.itens = itens;
    }

    public ArrayList<Venda> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<Venda> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public ArrayList<Cliente_Endereco> getComboCE() {
        return comboCE;
    }

    public void setComboCE(ArrayList<Cliente_Endereco> comboCE) {
        this.comboCE = comboCE;
    }

    //Preparar 
    public void carregarListagem() {
        try {
            Cliente_EnderecoDAO cedao = new Cliente_EnderecoDAO();
            comboCE = cedao.listar();
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

    }

    public void prepararNovo() {
        v = new Venda();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        v.setDt_vendida(Date.valueOf(dateFormat.format(cal.getTime())));
    }

    public void prepararEditar() {
        carregarListagem();
    }

    @PostConstruct
    public void init() {
        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }
        
        try {
            VendaDAO vdao = new VendaDAO();
            itens = vdao.listar();
            v = new Venda();
            carregarListagem();

        } catch (SQLException ex) {
            UrlPagina.redirecionar("erro/db.xhtml");
        }
    }

    //Novo
    public void novoEditar(boolean editar) {
        VendaDAO mdao = new VendaDAO();

        try {

            if (editar == false) {
                mdao.inserir(v);
                Mensagem.sucesso("Cadastramos com sucesso.");
            } else {
                mdao.alterar(v);
                Mensagem.sucesso("Atualizamos com sucesso.");
            }

        } catch (SQLException ex) {
            Mensagem.erro(ex.toString());
        }

        init();
    }

    public void excluir() {
        VendaDAO mdao = new VendaDAO();

        try {
            mdao.deletar(v.getId());
            Mensagem.sucesso("Deletamos com sucesso!");
        } catch (SQLException ex) {
            Mensagem.erro(ex.getLocalizedMessage());
        }

        init();
    }

}
