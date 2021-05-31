/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.CidadeBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.ufscar.dc.dsw.conexao.Conexao;


/**
 *
 * @author ellen
 */
public class CidadeDao{
        
    

    public List<CidadeBean> getAll() throws ClassNotFoundException, SQLException {
        List<CidadeBean> listaCidades = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, c.uf FROM cidade c ORDER BY c.nome";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql); ResultSet rs = comandoSql.executeQuery()) {
            
            CidadeBean cidade;
            while (rs.next()) {
                cidade = new CidadeBean();
                cidade.setId(Long.parseLong(rs.getString("id")));
                cidade.setNome(rs.getString("nome"));
                cidade.setUf(rs.getString("uf"));
                listaCidades.add(cidade);
            }
        }
        return listaCidades;
    }
    
    public List<CidadeBean> getByName(String n) throws ClassNotFoundException, SQLException {
        List<CidadeBean> listaCidades = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, c.uf FROM cidade c WHERE UPPER(c.nome) LIKE ? ORDER BY c.nome";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, n);
            try (ResultSet rs = comandoSql.executeQuery()) {
                CidadeBean cidade = null;
                while (rs.next()) {
                    cidade = new CidadeBean();
                    cidade.setId(Long.parseLong(rs.getString("id")));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setUf(rs.getString("uf"));
                    listaCidades.add(cidade);
                }
            }
        }
        return listaCidades;
    }

    public CidadeBean getById(Long id) throws ClassNotFoundException, SQLException {
        CidadeBean cidade = new CidadeBean();
        String sql = "SELECT c.nome, c.uf FROM cidade c WHERE c.id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE)) {
            comandoSql.setLong(1, id);
            
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                cidade.setId(id);
                cidade.setNome(rs.getString("nome"));
                cidade.setUf(rs.getString("uf"));
                
            }
        }
        return cidade;
    }
    
    
}
