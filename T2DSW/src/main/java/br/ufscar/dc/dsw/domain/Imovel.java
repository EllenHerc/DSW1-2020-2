/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ellen
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Imovel")
public class Imovel extends AbstractEntity<Long>{
    
    @OneToMany
    private List<Foto> fotos;
    
    @NotBlank(message = "DESCRIÇÃO Não pode ficar vazio")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String descricao;
    
    @NotNull(message = "VALOR Não pode ficar vazio")
    @Column(nullable = false, length = 12)
    private float valor;
    
    @NotBlank(message = "CEP Não pode ficar vazio")
    @Size(max = 11)
    @Column(nullable = false, length = 11)
    private String cep;

    @NotBlank(message = "LOGRADOURO Não pode ficar vazio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String logradouro;

    @NotNull(message = "NUMERO Não pode ficar vazio")
    @Column(nullable = false)
    private Integer numero;

    @NotBlank(message = "BAIRRO Não pode ficar vazio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String bairro;

    @NotNull(message = "CIDADE_ID Não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    
    @NotNull(message = "IMOBILIARIA_ID Não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "imobiliaria_id")
    private Imobiliaria imobiliaria;

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Imobiliaria getImobiliaria() {
        return imobiliaria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setImobiliaria(Imobiliaria imobiliaria) {
        this.imobiliaria = imobiliaria;
    }
}
