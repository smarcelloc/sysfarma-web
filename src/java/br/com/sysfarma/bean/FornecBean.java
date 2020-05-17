package br.com.sysfarma.bean;

import br.com.sysfarma.dao.FornecDAO;
import br.com.sysfarma.dao.custom.Fornec_Endereco;
import br.com.sysfarma.dao.custom.Fornec_EnderecoDAO;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "MBFornec")
@ViewScoped
public class FornecBean {

    private ArrayList<Fornec_Endereco> itens;
    private ArrayList<Fornec_Endereco> itensFiltro;
    private Fornec_Endereco foe;

    public ArrayList<Fornec_Endereco> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Fornec_Endereco> itens) {
        this.itens = itens;
    }

    public ArrayList<Fornec_Endereco> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<Fornec_Endereco> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public Fornec_Endereco getFoe() {
        return foe;
    }

    public void setFoe(Fornec_Endereco foe) {
        this.foe = foe;
    }

    @PostConstruct
    public void init() {
        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }
        
        
        try {
            Fornec_EnderecoDAO foedao = new Fornec_EnderecoDAO();
            itens = foedao.listar();
        } catch (SQLException ex) {

            UrlPagina.redirecionar("erro/db.xhtml");

        }
    }

    //Preparar 
    public void prepararNovo() {
        foe = new Fornec_Endereco();
    }

    //Novo
    public void novoEditar(boolean editar) {
        boolean erro = true;
        FornecDAO foedao = new FornecDAO();

        try {

            if (foedao.verificarNome(foe.getFornec().getId(), foe.getFornec().getNome())) {

                if (foedao.verificarCNPJ(foe.getFornec().getId(), foe.getFornec().getCnpj())) {

                    if (editar == false) {
                        foedao.inserir(foe.getFornec(), foe.getEndereco());

                        Mensagem.sucesso("Cadastramos com sucesso.");
                    } else {
                        foedao.alterar(foe.getFornec(), foe.getEndereco());
                        Mensagem.sucesso("Atualizamos com sucesso.");
                    }

                    erro = false;
                } else {
                    Mensagem.erro("Este CNPJ já existe em nosso sistema.");
                }
            } else {
                Mensagem.erro("Este Nome da Drogaria já existe em nosso sistema.");
            }

        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);
    }

    public void excluir() {
        boolean erro = true;
        FornecDAO fdao = new FornecDAO();

        try {
            fdao.deletar(foe.getFornec().getId());
            Mensagem.sucesso("Deletamos com sucesso!");
            erro = false;
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);

    }

}
