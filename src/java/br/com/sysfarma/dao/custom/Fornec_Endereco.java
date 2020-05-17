package br.com.sysfarma.dao.custom;

import br.com.sysfarma.domain.Fornec;
import br.com.sysfarma.domain.Endereco;

public class Fornec_Endereco {

    private Fornec fornec = new Fornec();
    private Endereco endereco = new Endereco();

    public Fornec getFornec() {
        return fornec;
    }

    public void setFornec(Fornec fornec) {
        this.fornec = fornec;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
