/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Foto")
public class Foto extends AbstractEntity<Long>{
    
    @NotBlank(message = "URL Não pode ficar vazio")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String url;    
    
    @NotNull(message = "IMOVEL_ID Não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    public String getUrl() {
        return url;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }        
}
