package br.ufscar.dc.dsw.bean;

import java.sql.Date;


public class PropostaBean {
    private Long id;
    private ClienteBean cliente;
    private ImovelBean imovel;
    private String pagamento;
    private float valor;
    private String status;
    private Date dataemissao;

    public PropostaBean() {
    }

    public PropostaBean(Long id, ClienteBean cliente, ImovelBean imovel, String pagamento, float valor, String status, Date dataemissao) {
        this.id = id;
        this.cliente = cliente;
        this.imovel = imovel;
        this.pagamento = pagamento;
        this.valor = valor;
        this.status = status;
        this.dataemissao = dataemissao;
    }

    public PropostaBean(ClienteBean cliente, ImovelBean imovel, String pagamento, float valor, String status, Date dataemissao) {
        this.cliente = cliente;
        this.imovel = imovel;
        this.pagamento = pagamento;
        this.valor = valor;
        this.status = status;
        this.dataemissao = dataemissao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public void setImovel(ImovelBean imovel) {
        this.imovel = imovel;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }
    
    public Long getId() {
        return id;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public ImovelBean getImovel() {
        return imovel;
    }

    public String getPagamento() {
        return pagamento;
    }

    public float getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

   
}
