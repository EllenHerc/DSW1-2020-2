/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.FotoBean;
import br.ufscar.dc.dsw.bean.CidadeBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caiocesardemorais
 */
public class FotoDao {
    List<FotoBean> listaFotos = null;
    
    public void CadastrarFoto(FotoBean img) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO foto (url, imovel_id) VALUES (?, ?)"; 
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, img.getUrl());
            comandoSql.setLong(2, img.getImovel().getId());
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    } 
    
    public FotoBean consultarFoto(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM foto WHERE id = ?";
        FotoBean img;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, id);
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                img = new FotoBean();
                img.setId(rs.getLong("id"));
                img.setUrl(rs.getString("url"));
                // TO DO: setar dados do imovel se necessario, fazer query com inner join
            }
        }
        return img;
    }
    
    public List<FotoBean> consultarFotosPorImovel(Long imovelId) throws SQLException, ClassNotFoundException {
            String sql = "SELECT f.id, f.url, f.imovel_id, i.cep, i.logradouro, i.numero, i.bairro, i.descricao, i.valor, i.imobiliaria_cnpj, i.cidade_id, c.nome AS cidade, c.uf"
                         + " FROM foto f INNER JOIN imovel i ON f.imovel_id = i.id INNER JOIN cidade c ON c.id = i.cidade_id WHERE f.imovel_id LIKE ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, imovelId);
                try (ResultSet rs = comandoSql.executeQuery()) {
                    FotoBean img = null;
                    listaFotos = new ArrayList<>();
                    while (rs.next()) {
                        img = new FotoBean();
                        img.setId(rs.getLong("id"));
                        img.setUrl(rs.getString("url"));
                        
                        ImovelBean imo = new ImovelBean();
                        imo.setId(rs.getLong("imovel_id"));
                        imo.setCep(rs.getString("cep"));
                        imo.setLogradouro(rs.getString("logradouro"));
                        imo.setNumero(rs.getString("numero"));
                        imo.setBairro(rs.getString("bairro"));
                        
                        CidadeBean cid = new CidadeBean();
                        
                        cid.setId(rs.getLong("cidade_id"));
                        cid.setNome(rs.getString("cidade"));
                        cid.setUf(rs.getString("uf"));
                        
                        ImobiliariaBean imobiliaria = new ImobiliariaBean();
                        imobiliaria.setCnpj(rs.getLong("imobiliaria_cnpj"));
                        
                        imo.setImobiliaria(imobiliaria);
                        imo.setCidade(cid);
                        img.setImovel(imo);
                        listaFotos.add(img);
                    }   }
        }
            return listaFotos;
    }
    
    public void alterarImobiliaria(FotoBean img) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE foto SET url = ?, imovel_id = ? WHERE id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, img.getUrl());
            comandoSql.setLong(2, img.getImovel().getId());
            comandoSql.setLong(5, img.getId());
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
    
    public void excluirFoto(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM foto WHERE id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, id);
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
}
