/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caiocesardemorais
 */
public class ImobiliariaDao {
   
    public ImobiliariaBean consultarImobiliaria(Long cnpj) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM imobiliaria WHERE id = ?";
        ImobiliariaBean imo;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, cnpj);
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                imo = new ImobiliariaBean();
                imo.setCnpj(rs.getLong("cnpj"));
                imo.setNome(rs.getString("nome"));
                imo.setDescricao(rs.getString("descricao"));
                /*não recupera os dados de acesso*/
            }
        }
        return imo;
    }
    
    public ImobiliariaBean consultarImobiliariaEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM imobiliaria WHERE user_email = ?";
        ImobiliariaBean imo;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE)) {
            comandoSql.setString(1, email);
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                imo = new ImobiliariaBean();
                imo.setCnpj(rs.getLong("cnpj"));
                imo.setNome(rs.getString("nome"));
                imo.setDescricao(rs.getString("descricao"));
                
                UsuarioBean usu = new UsuarioBean();
                usu.setEmail(email);
                
                imo.setUser(usu);
                
                /*não recupera os dados de acesso*/
            }
        }
        return imo;
    }
   
    /*public ImobiliariaBean consultarImobiliariaPorLogin(String nome, String senha) throws SQLException, ClassNotFoundException {
        ImobiliariaBean imo = null;
        String sql = "SELECT * FROM imobiliaria WHERE nome = ? AND senha = ?";
        PreparedStatement comandoSql =  Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, nome);
        comandoSql.setString(2, senha);
        ResultSet rs = comandoSql.executeQuery();

        if(rs.next()) {
            imo = new ImobiliariaBean();
            imo.setId(rs.getInt("idimobiliaria"));
            imo.setNome(rs.getString("nome"));
            imo.setEmail(rs.getString("email"));
            imo.setSenha(rs.getString("senha"));
            imo.setCnpj(rs.getString("cnpj"));

        }
        return imo;
    }*/
}
