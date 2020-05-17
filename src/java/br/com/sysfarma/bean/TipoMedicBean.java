package br.com.sysfarma.bean;

import br.com.sysfarma.domain.TipoMedic;
import br.com.sysfarma.dao.TipoMedicDAO;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "MBTipoMedic")
@ViewScoped
public class TipoMedicBean {

    private ArrayList<TipoMedic> itens;
    private ArrayList<TipoMedic> itensFiltro;
    private TipoMedic tm;

    public ArrayList<TipoMedic> getItens() {
        return itens;
    }

    public void setItens(ArrayList<TipoMedic> itens) {
        this.itens = itens;
    }

    public ArrayList<TipoMedic> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<TipoMedic> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public TipoMedic getTm() {
        return tm;
    }

    public void setTm(TipoMedic tm) {
        this.tm = tm;
    }

    //Preparar 
    public void prepararNovo() {
        tm = new TipoMedic();
    }

    @PostConstruct
    public void init() {
        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }

        try {
            TipoMedicDAO tmdao = new TipoMedicDAO();
            itens = tmdao.listar();
        } catch (SQLException ex) {
            UrlPagina.redirecionar("erro/db.xhtml");

        }
    }

    //Novo
    public void novoEditar(boolean editar) {
        boolean erro = true;
        TipoMedicDAO tmdao = new TipoMedicDAO();

        try {

            if (!tmdao.verificarNome(tm.getId(), tm.getNome())) {

                if (editar == false) {
                    tmdao.inserir(tm);

                    Mensagem.sucesso("Cadastramos com sucesso.");
                } else {
                    tmdao.alterar(tm);
                    Mensagem.sucesso("Atualizamos com sucesso.");
                }

                erro = false;
            } else {
                Mensagem.erro("Este Tipo de Medicamento já existe em nosso sistema.");
            }

        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);
    }

    public void excluir() {
        boolean erro = true;
        TipoMedicDAO tmdao = new TipoMedicDAO();

        try {
            tmdao.deletar(tm.getId());
            Mensagem.sucesso("Deletamos com sucesso!");
            erro = false;
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);

    }

}
