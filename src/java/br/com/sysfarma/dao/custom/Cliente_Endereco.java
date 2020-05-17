/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarma.dao.custom;

import br.com.sysfarma.domain.Cliente;
import br.com.sysfarma.domain.Endereco;

/**
 *
 * @author marce
 */
public class Cliente_Endereco {

    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
