/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ellen
 */
public class ClienteDao {
    
     public void CadastrarCliente(ClienteBean cli) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO cliente (cpf, nome, nascimento, sexo, telefone, user_email) VALUES (?, ?, ?, ?, ?)"; 
         try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
             comandoSql.setLong(1, cli.getCpf());
             comandoSql.setString(2, cli.getNome());
             comandoSql.setDate(3, cli.getNascimento());
             comandoSql.setString(4, cli.getSexo());
             comandoSql.setString(5, cli.getTelefone());
             comandoSql.setString(6, cli.getUser().getEmail());
             comandoSql.execute();
             Conexao.getInstance().commit();
         }
    }
    
    public ClienteBean consultarCliente(Long cpf) throws SQLException, ClassNotFoundException {
        String sql = "SELECT c.cpf, c.nome, c.nascimento, c.sexo, c.telefone, c.user_email, u.id, u.senha, u.papel FROM cliente c INNER JOIN usuario u ON u.email = c.user_email  WHERE c.cpf = ?";
        ClienteBean cli;
         try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
             comandoSql.setLong(1, cpf);
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                cli = new ClienteBean();
                cli.setCpf(rs.getLong("cpf"));
                cli.setNome(rs.getString("nome"));
                cli.setSexo(rs.getString("sexo"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setNascimento(rs.getDate("nascimento"));
                UsuarioBean usu = new UsuarioBean();
                usu.setId(rs.getLong("id"));
                usu.setEmail(rs.getString("user_email"));
                usu.setSenha(rs.getString("senha"));
                usu.setPapel(rs.getString("papel"));
                cli.setUser(usu);
            }
         }
        return cli;
    }
    
    public ClienteBean consultarClienteEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT c.cpf, c.nome, c.nascimento, c.sexo, c.telefone, u.id, u.senha, u.papel FROM cliente c INNER JOIN usuario u ON u.email = c.user_email  WHERE c.email = ?";
        ClienteBean cli;
         try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
             comandoSql.setString(1, email);
            try (ResultSet rs = comandoSql.executeQuery()) {
                rs.first();
                cli = new ClienteBean();
                cli.setCpf(rs.getLong("cpf"));
                cli.setNome(rs.getString("nome"));
                cli.setSexo(rs.getString("sexo"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setNascimento(rs.getDate("nascimento"));
                UsuarioBean usu = new UsuarioBean();
                usu.setId(rs.getLong("id"));
                usu.setEmail(rs.getString("user_email"));
                usu.setSenha(rs.getString("senha"));
                usu.setPapel(rs.getString("papel"));
                cli.setUser(usu);
            }
         }
        return cli;
    }
    
      
    public void alterarCliente(ClienteBean cli) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE cliente SET nome = ?, nascimento = ?, telefone = ?, sexo = ?, user_email = ? WHERE cpf = ?";
         try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
             comandoSql.setString(1, cli.getNome());
             comandoSql.setDate(2, cli.getNascimento());
             comandoSql.setString(3, cli.getTelefone());
             comandoSql.setString(4, cli.getSexo());
             comandoSql.setString(5, cli.getUser().getEmail());
             comandoSql.setLong(6, cli.getCpf());
             comandoSql.execute();
             Conexao.getInstance().commit();
         }
    }
    
    public void excluirCliente(Long cpf) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
         try (PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql)) {
             comandoSql.setLong(1, cpf);
             comandoSql.execute();
             Conexao.getInstance().commit();
         }
    }
    
    
}
