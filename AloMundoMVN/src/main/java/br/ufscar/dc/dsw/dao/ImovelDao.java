/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author caiocesardemorais
 */
public class ImovelDao {
    
    public void CadastrarImovel(ImovelBean imovel) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO imoveis (nome, endereco, cidade, descricao, valor, imobiliaria_idimobiliaria) VALUES (?, ?, ?, ?, ?, ?)"; 
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, imovel.getNome());
        comandoSql.setString(2, imovel.getEndereco());
        comandoSql.setString(3, imovel.getCidade());
        comandoSql.setString(4, imovel.getDescricao());
        comandoSql.setFloat(5, imovel.getValor());
        comandoSql.setInt(6, imovel.getImobiliaria().getId());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    }
    
}
