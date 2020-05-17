package br.com.sysfarma.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {

    public static void sucesso(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    public static void erro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }
    
    public static void erroCode(String msg, int code) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, code + ": " + msg, code +": "+ msg));
    }
}
