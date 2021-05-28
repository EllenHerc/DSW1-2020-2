/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import java.util.List;

/**
 *
 * @author caiocesardemorais
 */
public class ImovelBean {
    private Long id;
    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private CidadeBean cidade;
    private String descricao;
    private float valor;
    private ImobiliariaBean imobiliaria;
    private List<FotoBean> fotos;

    public ImovelBean(Long id, String cep, String logradouro, int numero, String bairro, CidadeBean cidade, String descricao, float valor, ImobiliariaBean imobiliaria) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.descricao = descricao;
        this.valor = valor;
        this.imobiliaria = imobiliaria;
    }

    public void setFotos(List<FotoBean> fotos) {
        this.fotos = fotos;
    }

    public List<FotoBean> getFotos() {
        return fotos;
    }        

    public ImovelBean() {
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setImobiliaria(ImobiliariaBean imobiliaria) {
        this.imobiliaria = imobiliaria;
    }
    
    

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public CidadeBean getCidade() {
        return cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public ImobiliariaBean getImobiliaria() {
        return imobiliaria;
    }
    
    

}