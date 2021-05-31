/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.CidadeBean;
import br.ufscar.dc.dsw.bean.FotoBean;
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
public class ImovelDao {
    
    public List<ImovelBean> getAll() throws SQLException, ClassNotFoundException {
            String sql = "SELECT i.id, i.descricao, i.valor, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj, im.nome AS imobiliaria"
                + " FROM imovel i INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj";
            List<ImovelBean> listaImoveis;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql); ResultSet rs = comandoSql.executeQuery()) {
            ImovelBean imo;
            listaImoveis = new ArrayList<>();
            while (rs.next()) {
                imo = new ImovelBean();
                imo.setId(rs.getLong("id"));
                imo.setDescricao(rs.getString("descricao"));
                imo.setValor(rs.getFloat("valor"));
                imo.setCep(rs.getString("cep"));
                imo.setLogradouro(rs.getString("logradouro"));
                imo.setNumero(rs.getInt("numero"));
                imo.setBairro(rs.getString("bairro"));
                
                CidadeBean cid = new CidadeBean();
                
                cid.setId(rs.getLong("cidade_id"));
                cid.setNome(rs.getString("cidade"));
                cid.setUf(rs.getString("uf"));
                
                ImobiliariaBean imobiliaria = new ImobiliariaBean();
                imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                imobiliaria.setNome(rs.getString("imobiliaria"));
                
                FotoDao fotoDao = new FotoDao();
                List<FotoBean> ListaFoto = fotoDao.consultarFotosPorImovel(imo.getId());
                
                imo.setImobiliaria(imobiliaria);
                imo.setCidade(cid);
                imo.setFotos(ListaFoto);
                
                listaImoveis.add(imo);
            }
        }
            return listaImoveis;
    }
    public List<ImovelBean> getByCity(CidadeBean cidade) throws SQLException, ClassNotFoundException {
            String sql = "SELECT i.id, i.descricao, i.valor, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj, im.nome AS imobiliaria"
                + " FROM imovel i INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE c.nome = ? AND c.uf = ?";
            List<ImovelBean> listaImoveis;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, cidade.getNome());
            comandoSql.setString(2, cidade.getUf());
                try (ResultSet rs = comandoSql.executeQuery()) {
                    ImovelBean imo;
                    listaImoveis = new ArrayList<>();
                    while (rs.next()) {
                        imo = new ImovelBean();
                        imo.setId(rs.getLong("id"));
                        imo.setDescricao(rs.getString("descricao"));
                        imo.setValor(rs.getFloat("valor"));
                        imo.setCep(rs.getString("cep"));
                        imo.setLogradouro(rs.getString("logradouro"));
                        imo.setNumero(rs.getInt("numero"));
                        imo.setBairro(rs.getString("bairro"));
                        
                        ImobiliariaBean imobiliaria = new ImobiliariaBean();
                        imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                        imobiliaria.setNome(rs.getString("imobiliaria"));
                        
                        FotoDao fotoDao = new FotoDao();
                        List<FotoBean> ListaFoto = fotoDao.consultarFotosPorImovel(imo.getId());
                        
                        imo.setImobiliaria(imobiliaria);
                        cidade.setId(rs.getLong("cidade_id"));
                        imo.setCidade(cidade);
                        imo.setFotos(ListaFoto);
                        
                        listaImoveis.add(imo);
                    }   }
        }
            return listaImoveis;
    }
    
    public List<ImovelBean> getByImobiliaria(ImobiliariaBean imobiliaria) throws SQLException, ClassNotFoundException {
            String sql = "SELECT i.id, i.descricao, i.valor, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj"
                + " FROM imovel i INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE i.imobiliaria_cnpj = ?";
            List<ImovelBean> listaImoveis;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, imobiliaria.getCnpj());
                try (ResultSet rs = comandoSql.executeQuery()) {
                    ImovelBean imo;
                    listaImoveis = new ArrayList<>();
                    while (rs.next()) {
                        imo = new ImovelBean();
                        imo.setId(rs.getLong("id"));
                        imo.setDescricao(rs.getString("descricao"));
                        imo.setValor(rs.getFloat("valor"));
                        imo.setCep(rs.getString("cep"));
                        imo.setLogradouro(rs.getString("logradouro"));
                        imo.setNumero(rs.getInt("numero"));
                        imo.setBairro(rs.getString("bairro"));
                        
                        CidadeBean cidade = new CidadeBean();
                        cidade.setNome(rs.getString("cidade"));
                        cidade.setId(rs.getLong("cidade_id"));
                        cidade.setUf(rs.getString("uf"));
                                                
                        FotoDao fotoDao = new FotoDao();
                        List<FotoBean> ListaFoto = fotoDao.consultarFotosPorImovel(imo.getId());
                        
                        imo.setImobiliaria(imobiliaria);
                        imo.setCidade(cidade);
                        imo.setFotos(ListaFoto);
                        
                        listaImoveis.add(imo);
                    }   }
        }
            return listaImoveis;
    }
    
    public ImovelBean getById(Long id) throws SQLException, ClassNotFoundException {
            String sql = "SELECT i.descricao, i.valor, i.cep, i.logradouro, i.numero, i.bairro, i.cidade_id, c.nome AS cidade, c.uf, i.imobiliaria_cnpj, im.nome AS imobiliaria"
                + " FROM imovel i INNER JOIN cidade c ON c.id = i.cidade_id INNER JOIN imobiliaria im ON im.cnpj = i.imobiliaria_cnpj WHERE i.id = ?";
        ImovelBean imo = new ImovelBean();
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE)) {
            comandoSql.setLong(1, id);
                try (ResultSet rs = comandoSql.executeQuery()) {
                    rs.first();
                    
                    imo.setId(id);
                    imo.setDescricao(rs.getString("descricao"));
                    imo.setValor(rs.getFloat("valor"));
                    imo.setCep(rs.getString("cep"));
                    imo.setLogradouro(rs.getString("logradouro"));
                    imo.setNumero(rs.getInt("numero"));
                    imo.setBairro(rs.getString("bairro"));

                    ImobiliariaBean imobiliaria = new ImobiliariaBean();
                    imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                    imobiliaria.setNome(rs.getString("imobiliaria"));

                    FotoDao fotoDao = new FotoDao();
                    List<FotoBean> ListaFoto = fotoDao.consultarFotosPorImovel(imo.getId());

                    imo.setImobiliaria(imobiliaria);
                    CidadeBean cidade = new CidadeBean();
                    cidade.setId(rs.getLong("cidade_id"));
                    cidade.setNome(rs.getString("cidade"));
                    cidade.setUf(rs.getString("uf"));
                    
                    imo.setCidade(cidade);
                    imo.setFotos(ListaFoto);
                    } 
        }
            return imo;
    }
    
}
