package br.com.sysfarma.domain;

import java.sql.Date;

public class Cliente extends Pessoa{
    private String cpf;
    private Date dt_nasc;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }
}
