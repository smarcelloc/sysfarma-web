/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarma.dao.custom;

import br.com.sysfarma.domain.Endereco;
import br.com.sysfarma.domain.Funcionario;

/**
 *
 * @author marce
 */
public class Funcionario_Endereco {

    private Funcionario funcionario = new Funcionario();
    private Endereco endereco = new Endereco();

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
