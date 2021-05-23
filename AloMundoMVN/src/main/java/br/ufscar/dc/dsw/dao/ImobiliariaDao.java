/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caiocesardemorais
 */
public class ImobiliariaDao {
    
    public void CadastrarImobiliaria(ImobiliariaBean imo) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO imobiliaria (nome, email, senha, cnpj) VALUES (?, ?, ?, ?)"; 
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, imo.getNome());
        comandoSql.setString(2, imo.getEmail());
        comandoSql.setString(3, imo.getSenha());
        comandoSql.setString(4, imo.getCnpj());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    } 
   
    public ImobiliariaBean consultarImobiliaria(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM imobiliaria WHERE imobiliaria.idimobiliaria = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        ResultSet rs = comandoSql.executeQuery();
        
        rs.first();
        
        ImobiliariaBean imo = new ImobiliariaBean();
        imo.setId(rs.getInt("idimobiliaria"));
        imo.setNome(rs.getString("nome"));
        imo.setEmail(rs.getString("email"));
        imo.setSenha(rs.getString("senha"));
        imo.setCnpj(rs.getString("cnpj"));
        
        return imo;
    }
   
    public ImobiliariaBean consultarImobiliariaPorLogin(String nome, String senha) throws SQLException, ClassNotFoundException {
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
    }
    
    public void alterarImobiliaria(ImobiliariaBean imo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE imobiliaria SET nome = ?, email = ?, senha = ?, cnpj = ? WHERE idimobiliaria = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, imo.getNome());
        comandoSql.setString(2, imo.getEmail());
        comandoSql.setString(3, imo.getSenha());
        comandoSql.setString(4, imo.getCnpj());
        comandoSql.setInt(5, imo.getId());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    }
    
    public void excluirImobiliaria(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM imobiliaria WHERE idimobiliaria = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        comandoSql.execute();
        Conexao.getInstance().commit();
    }
}
