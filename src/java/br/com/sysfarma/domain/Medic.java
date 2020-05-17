package br.com.sysfarma.domain;

import java.sql.Date;

public class Medic {

    private Integer id;
    private String nome;
    private Double preco;
    private Short estoque;
    private Date dt_validade;
    private Boolean controlado;
    private TipoMedic tipoMedic = new TipoMedic();
    private Fornec fornec = new Fornec();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Short getEstoque() {
        return estoque;
    }

    public void setEstoque(Short estoque) {
        this.estoque = estoque;
    }

    public Date getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    public Boolean getControlado() {
        return controlado;
    }

    public void setControlado(Boolean controlado) {
        this.controlado = controlado;
    }

    public TipoMedic getTipoMedic() {
        return tipoMedic;
    }

    public void setTipoMedic(TipoMedic tipoMedic) {
        this.tipoMedic = tipoMedic;
    }

    public Fornec getFornec() {
        return fornec;
    }

    public void setFornec(Fornec fornec) {
        this.fornec = fornec;
    }

}
