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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ellen
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long>{
    
    @NotBlank(message = "NOME Não pode ficar vazio")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String nome;   
    
    @NotBlank(message = "CPF Não pode ficar vazio")
    @Size(max = 14)
    @Column(nullable = false, length = 14)
    private String cpf;   
    
    @NotBlank(message = "SEXO Não pode ficar vazio")
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String sexo;
    
    @NotBlank(message = "TELEFONE Não pode ficar vazio")
    @Size(max = 12)
    @Column(nullable = false, length = 12)
    private String telefone;
    
    @NotNull(message = "USUARIO_ID Não pode ficar vazio")
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
