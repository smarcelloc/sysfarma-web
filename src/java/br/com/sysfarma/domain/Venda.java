package br.com.sysfarma.domain;

import java.sql.Date;

public class Venda {

    private Integer id;
    private Double total;
    private Date dt_vendida;
    private String pgto;
    private Cliente cliente = new Cliente();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDt_vendida() {
        return dt_vendida;
    }

    public void setDt_vendida(Date dt_vendida) {
        this.dt_vendida = dt_vendida;
    }

    public String getPgto() {
        return pgto;
    }

    public void setPgto(String pgto) {
        this.pgto = pgto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
