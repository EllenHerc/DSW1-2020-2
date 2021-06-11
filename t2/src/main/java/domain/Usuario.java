/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author caiocesardemorais
 */
@Entity
@Table(name = "Usuario")
public class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 45)
    @Column(unique = true, nullable = false, length = 45)
    private String email;

    @NotBlank
    @Size(min = 6, max = 16)
    @Column(nullable = false, length = 16)
    private String senha;

    @NotBlank
    @Size(min = 3, max = 14)
    @Column(nullable = false, length = 14)
    private String papel;

    public Usuario() {
    }

   public Usuario(String email, String senha, String papel) {
       this.email = email;
       this.senha = senha;
       this.papel = papel;
   }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
