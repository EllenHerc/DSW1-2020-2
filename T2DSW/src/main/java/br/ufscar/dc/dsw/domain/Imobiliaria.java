/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author ellen
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Imobiliaria")
public class Imobiliaria extends AbstractEntity<Long>{
    
    @NotBlank(message = "CNPJ Não pode ficar vazio")
    @Column(nullable = false, length = 18, unique = true)
    private String cnpj;    
    
    @NotBlank(message = "NOME Não pode ficar vazio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "DESCRIÇÃO Não pode ficar vazio")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String descricao;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }       
}
