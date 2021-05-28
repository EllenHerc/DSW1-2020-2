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
    private Date nascimento;
    private UsuarioBean user;

    public ClienteBean(Long cpf, String nome, String telefone, String sexo, Date nascimento, UsuarioBean user) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.user = user;
    }

    public ClienteBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
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

    public Date getNascimento() {
        return nascimento;
    }

    public UsuarioBean getUser() {
        return user;
    }
    
    
    
}
