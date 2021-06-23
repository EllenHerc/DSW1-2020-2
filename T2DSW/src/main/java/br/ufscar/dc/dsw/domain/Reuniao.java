/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ellen
 */
public class Reuniao {
    
    private Date data;
    private Time hora;
    private String link;

    public Reuniao() {
    }
    

    public Date getData() {
        return data;
    }

    public Time getHora() {
        return hora;
    }

    public String getLink() {
        return link;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
    
    
}
