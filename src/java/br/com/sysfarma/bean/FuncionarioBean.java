package br.com.sysfarma.bean;

import br.com.sysfarma.dao.FuncionarioDAO;
import br.com.sysfarma.dao.custom.Funcionario_Endereco;
import br.com.sysfarma.dao.custom.Funcionario_EnderecoDAO;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {

    private ArrayList<Funcionario_Endereco> itens;
    private ArrayList<Funcionario_Endereco> itensFiltro;
    private Funcionario_Endereco fe;

    public ArrayList<Funcionario_Endereco> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Funcionario_Endereco> itens) {
        this.itens = itens;
    }

    public ArrayList<Funcionario_Endereco> getItensFiltro() {
        return itensFiltro;
    }

    public void setItensFiltro(ArrayList<Funcionario_Endereco> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    public Funcionario_Endereco getFe() {
        return fe;
    }

    public void setFe(Funcionario_Endereco fe) {
        this.fe = fe;
    }

    @PostConstruct
    public void init() {

        if (Session.getParam("logado") == null) {
            UrlPagina.redirecionar("login.xhtml");
        }

        try {
            Funcionario_EnderecoDAO fedao = new Funcionario_EnderecoDAO();
            itens = fedao.listar();
        } catch (SQLException ex) {
            UrlPagina.redirecionar("erro/db.xhtml");
        }
    }
    //Preparar 

    public void prepararNovo() {
        fe = new Funcionario_Endereco();
    }

    //Novo
    public void novoEditar(boolean editar) {
        FuncionarioDAO fdao = new FuncionarioDAO();
        boolean erro = true;
        try {

            if (fdao.verificarEmail(fe.getFuncionario().getId(), fe.getFuncionario().getEmail())) {

                if (editar == false) {
                    fdao.inserir(fe.getFuncionario(), fe.getEndereco());

                    Mensagem.sucesso("Cadastramos com sucesso.");
                } else {
                    fdao.alterar(fe.getFuncionario(), fe.getEndereco());
                    Mensagem.sucesso("Atualizamos com sucesso.");
                }
                erro = false;
            } else {

                Mensagem.erro("Este e-mail já existe no sistema, digite outro diferente.");
            }

        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
        PrimeFaces.current().ajax().addCallbackParam("erro", erro);
    }

    public void excluir() {
        FuncionarioDAO fdao = new FuncionarioDAO();

        try {
            if (itens.size() == 1) {
                Mensagem.erro("Não podemos excluir um único funcionário cadastrado");
            } else {

                fdao.deletar(fe.getFuncionario().getId());
                Mensagem.sucesso("Deletamos com sucesso!");
            }
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();

    }

    public void alterarSenha() {
        FuncionarioDAO fdao = new FuncionarioDAO();

        try {

            fdao.alterarSenha(fe.getFuncionario().getId(), fe.getFuncionario().getSenha());
            Mensagem.sucesso("Alteramos a senha com sucesso.");

        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        init();
    }

}
