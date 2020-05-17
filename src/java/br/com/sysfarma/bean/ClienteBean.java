package br.com.sysfarma.bean;

import br.com.sysfarma.dao.ClienteDAO;
import br.com.sysfarma.dao.custom.Cliente_Endereco;
import br.com.sysfarma.dao.custom.Cliente_EnderecoDAO;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean {

    private ArrayList<Cliente_Endereco> itens;
    private ArrayList<Cliente_Endereco> itensFiltro;
    private Cliente_Endereco ce;

    public Cliente_Endereco getCe() {
        return ce;
    }

    public void setCe(Cliente_Endereco ce) {
        this.ce = ce;
    }

    public ArrayList<Cliente_Endereco> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Cliente_Endereco> itens) {
        this.itens = itens;
    }

    public ArrayList<Cliente_Endereco> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<Cliente_Endereco> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public Cliente_Endereco getce() {
        return ce;
    }

    public void setce(Cliente_Endereco ce) {
        this.ce = ce;
    }

    @PostConstruct
    public void init() {
        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }
        
        
        try {
            Cliente_EnderecoDAO cedao = new Cliente_EnderecoDAO();
            itens = cedao.listar();
        } catch (SQLException ex) {
            UrlPagina.redirecionar("erro/db.xhtml");

        }
    }

    //Preparar 
    public void prepararNovo() {
        ce = new Cliente_Endereco();
    }

    //Novo
    public void novoEditar(boolean editar) {
        boolean erro = true;
        ClienteDAO cdao = new ClienteDAO();

        try {

            if (cdao.verificarCPF(ce.getCliente().getId(), ce.getCliente().getCpf())) {

                if (editar == false) {
                    cdao.inserir(ce.getCliente(), ce.getEndereco());

                    Mensagem.sucesso("Cadastramos com sucesso.");
                } else {
                    cdao.alterar(ce.getCliente(), ce.getEndereco());
                    Mensagem.sucesso("Atualizamos com sucesso.");
                }

                erro = false;
            } else {
                Mensagem.erro("Este CPF já existe em nosso sistema.");
            }

        } catch (SQLException ex) {
            Mensagem.erro(ex.toString());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);
    }

    public void excluir() {
        boolean erro = true;
        ClienteDAO fdao = new ClienteDAO();

        try {
            fdao.deletar(ce.getCliente().getId());
            Mensagem.sucesso("Deletamos com sucesso!");
            erro = false;
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        RequestContext.getCurrentInstance().addCallbackParam("erro", erro);

    }

}
