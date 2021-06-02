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
        String sql = "INSERT INTO usuario (email, senha, papel) VALUES (?, ?, ?)"; 
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, usu.getEmail());
            comandoSql.setString(2, usu.getSenha());
            comandoSql.setString(3, usu.getPapel());
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
    
    public UsuarioBean consultarUsuario(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM usuario WHERE usuario.id = ?";
        UsuarioBean usu;
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, id);
            ResultSet rs = comandoSql.executeQuery();
            rs.first();
            usu = new UsuarioBean();
            usu.setId(rs.getLong("id"));
            usu.setEmail(rs.getString("email"));
            usu.setSenha(rs.getString("senha"));
            usu.setPapel(rs.getString("papel"));
            rs.close();
        }
        return usu;
    }
    
    public UsuarioBean consultarUsuarioPorLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        UsuarioBean usu = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, email);
            comandoSql.setString(2, senha);
            try (ResultSet rs = comandoSql.executeQuery()) {
                if(rs.next()) {
                    usu = new UsuarioBean();
                    usu.setId(rs.getLong("id"));
                    usu.setEmail(rs.getString("email"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setPapel(rs.getString("papel"));
                    
                }
            }
        }
        return usu;
    }
    public UsuarioBean consultarUsuarioPorEmail(String email) throws SQLException, ClassNotFoundException {
        UsuarioBean usu = null;
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, email);
            try (ResultSet rs = comandoSql.executeQuery()) {
                if(rs.next()) {
                    usu = new UsuarioBean();
                    usu.setId(rs.getLong("id"));
                    usu.setEmail(rs.getString("email"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setPapel(rs.getString("papel"));
                    
                }
            }
        }
        return usu;
    }
    
    
    public void alterarUsuario(UsuarioBean usu) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE usuario SET email = ?, senha = ?, papel = ? WHERE id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setString(1, usu.getEmail());
            comandoSql.setString(2, usu.getSenha());
            comandoSql.setString(3, usu.getPapel());
            comandoSql.setLong(4, usu.getId());
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
    
    public void excluirUsuario(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
            comandoSql.setLong(1, id);
            comandoSql.execute();
            Conexao.getInstance().commit();
        }
    }
    
}
