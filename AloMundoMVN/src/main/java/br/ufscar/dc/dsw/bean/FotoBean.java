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
public class FotoBean {
    private int id;
    private String url;
    private ImovelBean imovel;

    public FotoBean() {
    }

    public FotoBean(int id, String url, ImovelBean imovel) {
        this.id = id;
        this.url = url;
        this.imovel = imovel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImovelBean getImovel() {
        return imovel;
    }

    public void setImovel(ImovelBean imovel) {
        this.imovel = imovel;
    }
    
    
}
