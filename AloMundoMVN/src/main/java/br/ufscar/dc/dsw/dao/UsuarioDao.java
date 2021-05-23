/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caiocesardemorais
 */
public class UsuarioDao {
    
    public void CadastrarUsuario(UsuarioBean usu) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO usuario (nome, email, senha, isAdmin) VALUES (?, ?, ?, ?)"; 
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, usu.getNome());
        comandoSql.setString(2, usu.getEmail());
        comandoSql.setString(3, usu.getSenha());
        comandoSql.setBoolean(4, usu.isAdmin());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    }
    
    public UsuarioBean consultarUsuario(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM usuario WHERE usuario.iduser = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        ResultSet rs = comandoSql.executeQuery();
        
        rs.first();
        
        UsuarioBean usu = new UsuarioBean();
        usu.setId(rs.getInt("iduser"));
        usu.setNome(rs.getString("nome"));
        usu.setEmail(rs.getString("email"));
        usu.setSenha(rs.getString("senha"));
        usu.setIsAdmin(rs.getBoolean("isAdmin"));
        
        return usu;
    }
    
    public UsuarioBean consultarUsuarioPorLogin(String nome, String senha) throws SQLException, ClassNotFoundException {
        UsuarioBean usu = null;
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";
        PreparedStatement comandoSql =  Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, nome);
        comandoSql.setString(2, senha);
        ResultSet rs = comandoSql.executeQuery();

        if(rs.next()) {
            usu = new UsuarioBean();
            usu.setId(rs.getInt("iduser"));
            usu.setNome(rs.getString("nome"));
            usu.setEmail(rs.getString("email"));
            usu.setSenha(rs.getString("senha"));
            usu.setIsAdmin(rs.getBoolean("isAdmin"));;

        }
        return usu;
    }
    
    public void alterarUsuario(UsuarioBean usu) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, isAdmin = ? WHERE iduser = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, usu.getNome());
        comandoSql.setString(2, usu.getEmail());
        comandoSql.setString(3, usu.getSenha());
        comandoSql.setBoolean(4, usu.isAdmin());
        comandoSql.setInt(5, usu.getId());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    }
    
    public void excluirUsuario(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM usuario WHERE iduser = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        comandoSql.execute();
        Conexao.getInstance().commit();
    }
    
}
