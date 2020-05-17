package br.com.sysfarma.bean;

import br.com.sysfarma.dao.FuncionarioDAO;
import br.com.sysfarma.factory.Conexao;
import br.com.sysfarma.util.Mensagem;
import br.com.sysfarma.util.Session;
import br.com.sysfarma.util.UrlPagina;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBLogin")
@ViewScoped
public class LoginBean {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @PostConstruct
    public void init() {

        try {
            Connection testeConexao = Conexao.getConexao();
        } catch (SQLException ex) {
            UrlPagina.redirecionar("SysFarma/faces/pages/erro/db.xhtml");
        }

        if (Session.getParam("logado") != null) {
            UrlPagina.redirecionar("venda.xhtml");
        }

    }

    public String entrar() {
        FuncionarioDAO fdao = new FuncionarioDAO();

        try {
            if (!fdao.autenticarLogin(email, senha)) {
                Session.setParam("logado", true);
                return "venda.xhtml?faces-redirect=true";
            } else {
                Mensagem.erro("Não encontramos em nosso sistema tenta novamente.");
            }
        } catch (SQLException ex) {
            Mensagem.erroCode(ex.getLocalizedMessage(), ex.getErrorCode());
        }

        return "";
    }

    public void sair() {
        if (Session.getParam("logado") != null) {
            Session.remove("logado");
            UrlPagina.redirecionar("login.xhtml");
        }

    }

}
