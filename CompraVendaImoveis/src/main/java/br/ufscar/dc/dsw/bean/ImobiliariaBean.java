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
public class ImobiliariaBean {
    private Long cnpj;
    private String nome;
    private String descricao;
    private UsuarioBean user;

    public ImobiliariaBean() {
    }

    public ImobiliariaBean(Long cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public ImobiliariaBean(Long cnpj, String nome, String descricao, UsuarioBean user) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.user = user;
    }

    public ImobiliariaBean(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public UsuarioBean getUser() {
        return user;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUser(UsuarioBean user) {
        this.user = user;
    }
    
    
    

   
}
