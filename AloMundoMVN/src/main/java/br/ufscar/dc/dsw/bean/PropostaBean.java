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
public class PropostaBean {
    private UsuarioBean usuario;
    private ImovelBean imovel;
    private String pagamento;
    private float valor;
    private String status;
    private String data;

    public PropostaBean() {
    }

    public PropostaBean(UsuarioBean usuario, ImovelBean imovel, String pagamento, float valor, String status, String data) {
        this.usuario = usuario;
        this.imovel = imovel;
        this.pagamento = pagamento;
        this.valor = valor;
        this.status = status;
        this.data = data;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public ImovelBean getImovel() {
        return imovel;
    }

    public void setImovel(ImovelBean imovel) {
        this.imovel = imovel;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
   
}
