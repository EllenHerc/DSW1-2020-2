/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;
import java.sql.Date;

/**
 *
 * @author ellen
 */
public class ClienteBean {
    private Long cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private UsuarioBean user;

    public ClienteBean(Long cpf, String nome, String telefone, String sexo, UsuarioBean user) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.user = user;
    }

    public ClienteBean() {
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setUser(UsuarioBean user) {
        this.user = user;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public UsuarioBean getUser() {
        return user;
    }
    
    
    
}
