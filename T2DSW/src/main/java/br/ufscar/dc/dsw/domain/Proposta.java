/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.domain;

import java.sql.Date;
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
@Table(name = "Proposta")
public class Proposta extends AbstractEntity<Long>{
    @NotBlank(message = "STATUS Não pode ficar vazio")
    @Size(max = 11)
    @Column(nullable = false, length = 11)
    private String status;
    
    @NotBlank(message = "PAGAMENTO Não pode ficar vazio")
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String pagamento;
    
    @NotNull(message = "VALOR Não pode ficar vazio")
    @Column(nullable = false, length = 12)
    private float valor;
    
    @NotNull(message = "DATAEMISSÃO Não pode ficar vazio")
    @Column(nullable = false)
    private Date dataemissao;
    
    @NotNull(message = "CLIENTE_ID Não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @NotNull(message = "IMOVEL_ID Não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    public String getStatus() {
        return status;
    }

    public String getPagamento() {
        return pagamento;
    }

    public float getValor() {
        return valor;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }    
}
