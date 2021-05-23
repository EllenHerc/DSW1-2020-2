/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

/**
 *
 * @author caiocesardemorais
 */
public class ImovelBean {
    private int id;
    private String nome;
    private String endereco;
    private String cidade;
    private String descricao;
    private float valor;
    private ImobiliariaBean imobiliaria;

    public ImovelBean() {
    }

    public ImovelBean(int id, String nome, String endereco, String cidade, String descricao, float valor, ImobiliariaBean imobiliaria) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.descricao = descricao;
        this.valor = valor;
        this.imobiliaria = imobiliaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public ImobiliariaBean getImobiliaria() {
        return imobiliaria;
    }

    public void setImobiliaria(ImobiliariaBean imobiliaria) {
        this.imobiliaria = imobiliaria;
    }
    
    
}
