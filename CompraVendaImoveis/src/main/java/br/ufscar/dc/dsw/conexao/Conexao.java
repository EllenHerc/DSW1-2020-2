/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author caiocesardemorais
 */
public class Conexao {
    private static Connection conexao = null;
    private final String bancoDados = "compravendaimoveis";
    
    public Conexao() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");			
        //servidor, porta, banco de dados, usuario, senha
	conexao = DriverManager.getConnection("jdbc:mysql://localhost:3000/"+ this.bancoDados + "?useTimezone=true&serverTimezone=UTC","web1","web1caio");
	conexao.setAutoCommit(false);	
    }
    
    public static Connection getInstance() throws ClassNotFoundException, SQLException{
        if (conexao == null){
            new Conexao();
        }
        return conexao;
    }
}
