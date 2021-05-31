/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.CidadeBean;
import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.bean.PropostaBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ellen
 */
public class PropostaDao {
    
    public void CadastrarProposta(PropostaBean prop) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO proposta (pagamento, valor, status, dataemissao, cliente_cpf, imovel_id) VALUES (?, ?, ?, ?, ?, ?)"; 
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, prop.getPagamento());
            comandoSql.setFloat(2, prop.getValor());
            comandoSql.setString(3, prop.getStatus());
            comandoSql.setDate(4, prop.getDataemissao());
            comandoSql.setLong(5, prop.getCliente().getCpf());
            comandoSql.setLong(6, prop.getImovel().getId());

            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    } 
    
    public List<PropostaBean> getByClient(ClienteBean cliente) throws SQLException, ClassNotFoundException {
            String sql = "SELECT p.id, p.pagamento, p.valor, p.status, p.dataemissao, p.imovel_id, i.descricao, i.valor as valorimovel, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj, im.nome AS imobiliaria"
                + " FROM proposta p INNER JOIN imovel i ON p.imovel_id = i.id INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE p.cliente_cpf = ?";
            List<PropostaBean> listaPropostas;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, cliente.getCpf());
                try (ResultSet rs = comandoSql.executeQuery()) {
                    PropostaBean prop;
                    listaPropostas = new ArrayList<>();
                    while (rs.next()) {
                        prop = new PropostaBean();
                        prop.setId(rs.getLong("id"));
                        prop.setPagamento(rs.getString("pagamento"));
                        prop.setStatus(rs.getString("status"));
                        prop.setValor(rs.getFloat("valor"));
                        prop.setDataemissao(rs.getDate("dataemissao"));
                        
                        ImovelBean imo = new ImovelBean();
                        imo.setId(rs.getLong("imovel_id"));
                        imo.setDescricao(rs.getString("descricao"));
                        imo.setValor(rs.getFloat("valor"));
                        imo.setCep(rs.getString("cep"));
                        imo.setLogradouro(rs.getString("logradouro"));
                        imo.setNumero(rs.getString("numero"));
                        imo.setBairro(rs.getString("bairro"));
                        
                        ImobiliariaBean imobiliaria = new ImobiliariaBean();
                        imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                        imobiliaria.setNome(rs.getString("imobiliaria"));
                        
                        CidadeBean cidade = new CidadeBean();
                        cidade.setNome(rs.getString("cidade"));
                        cidade.setUf(rs.getString("uf"));
                        cidade.setId(rs.getLong("cidade_id"));
                        
                        imo.setImobiliaria(imobiliaria);
                        imo.setCidade(cidade);
                        
                        prop.setImovel(imo);
                        prop.setCliente(cliente);
                        listaPropostas.add(prop);
                    }   }
        }
            return listaPropostas;
    }    
    
    public List<PropostaBean> getByImobiliaria(ImobiliariaBean imobiliaria) throws SQLException, ClassNotFoundException {
            String sql = "SELECT p.id, p.pagamento, p.valor, p.status, p.dataemissao, p.imovel_id, p.cliente_cpf, cli.nome as cliente, cli.telefone, cli.user_email, i.descricao, i.valor as valorimovel, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf FROM proposta p INNER JOIN cliente cli ON p.cliente_cpf = cli.cpf INNER JOIN imovel i ON p.imovel_id = i.id INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE i.imobiliaria_cnpj = ? ORDER BY p.status";
            List<PropostaBean> listaPropostas;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, imobiliaria.getCnpj());
                try (ResultSet rs = comandoSql.executeQuery()) {
                    PropostaBean prop;
                    listaPropostas = new ArrayList<>();
                    while (rs.next()) {
                        prop = new PropostaBean();
                        prop.setId(rs.getLong("id"));
                        prop.setPagamento(rs.getString("pagamento"));
                        prop.setStatus(rs.getString("status"));
                        prop.setValor(rs.getFloat("valor"));
                        prop.setDataemissao(rs.getDate("dataemissao"));
                        
                        ImovelBean imo = new ImovelBean();
                        imo.setId(rs.getLong("imovel_id"));
                        imo.setDescricao(rs.getString("descricao"));
                        imo.setValor(rs.getFloat("valor"));
                        imo.setCep(rs.getString("cep"));
                        imo.setLogradouro(rs.getString("logradouro"));
                        imo.setNumero(rs.getString("numero"));
                        imo.setBairro(rs.getString("bairro"));                                               
                        
                        CidadeBean cidade = new CidadeBean();
                        cidade.setNome(rs.getString("cidade"));
                        cidade.setUf(rs.getString("uf"));
                        cidade.setId(rs.getLong("cidade_id"));
                        
                        imo.setImobiliaria(imobiliaria);
                        imo.setCidade(cidade);
                        
                        ClienteBean cli = new ClienteBean();
                        cli.setCpf(rs.getLong("cliente_cpf"));
                        cli.setNome(rs.getString("cliente"));
                        cli.setTelefone(rs.getString("telefone"));
                        
                        UsuarioBean usu = new UsuarioBean();
                        usu.setEmail(rs.getString("user_email"));
                        
                        cli.setUser(usu);                        
                        prop.setImovel(imo);
                        prop.setCliente(cli);
                        listaPropostas.add(prop);
                    }   }
        }
            return listaPropostas;
    }    
    
    public PropostaBean getById(Long id) throws SQLException, ClassNotFoundException {
            String sql = "SELECT p.id, p.pagamento, p.valor, p.status, p.dataemissao, p.imovel_id, p.cliente_cpf, cli.nome as cliente, cli.telefone, cli.user_email, i.descricao, i.valor as valorimovel, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj, im.nome AS imobiliaria"
                + " FROM proposta p INNER JOIN cliente cli ON p.cliente_cpf = cli.cpf INNER JOIN imovel i ON p.imovel_id = i.id INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE p.id = ?";
            PropostaBean prop = new PropostaBean();
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE)) {
            comandoSql.setLong(1, id);
                try (ResultSet rs = comandoSql.executeQuery()) {
                    rs.first();
                    
                    prop.setId(rs.getLong("id"));
                    prop.setPagamento(rs.getString("pagamento"));
                    prop.setStatus(rs.getString("status"));
                    prop.setValor(rs.getFloat("valor"));
                    prop.setDataemissao(rs.getDate("dataemissao"));

                    ClienteBean cli = new ClienteBean();
                    cli.setCpf(rs.getLong("cliente_cpf"));
                    cli.setNome(rs.getString("cliente"));
                    cli.setTelefone(rs.getString("telefone"));
                    
                    UsuarioBean usu = new UsuarioBean();
                    usu.setEmail(rs.getString("user_email"));
                    cli.setUser(usu);

                    ImovelBean imo = new ImovelBean();
                    imo.setId(rs.getLong("imovel_id"));
                    imo.setDescricao(rs.getString("descricao"));
                    imo.setValor(rs.getFloat("valor"));
                    imo.setCep(rs.getString("cep"));
                    imo.setLogradouro(rs.getString("logradouro"));
                    imo.setNumero(rs.getString("numero"));
                    imo.setBairro(rs.getString("bairro"));

                    ImobiliariaBean imobiliaria = new ImobiliariaBean();
                    imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                    imobiliaria.setNome(rs.getString("imobiliaria"));

                    CidadeBean cidade = new CidadeBean();
                    cidade.setNome(rs.getString("cidade"));
                    cidade.setUf(rs.getString("uf"));
                    cidade.setId(rs.getLong("cidade_id"));

                    imo.setImobiliaria(imobiliaria);
                    imo.setCidade(cidade);

                    prop.setImovel(imo);
                    prop.setCliente(cli);
                    }
        }
            return prop;
    }

    public boolean verificaClienteImovel(PropostaBean proposta) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM proposta WHERE cliente_cpf = ? AND imovel_id = ? AND status = 'ABERTO'";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
        comandoSql.setLong(1, proposta.getCliente().getCpf());
        comandoSql.setLong(2, proposta.getImovel().getId());
            try (ResultSet rs = comandoSql.executeQuery()) {
                PropostaBean prop;
                while (rs.next()) {
                    return true;
                }   
            }
        }
        return false;
    }
    
    public void atualizaStatus(String status, Long id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE proposta SET status = ? WHERE id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, status);
            comandoSql.setLong(2, id);
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
    
}
