/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.bean.FotoBean;
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
        String sql = "INSERT INTO fotos (url, imoveis_idimoveis) VALUES (?, ?)"; 
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, img.getUrl());
        comandoSql.setInt(2, img.getImovel().getId());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    } 
    
    public FotoBean consultarFoto(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM fotos WHERE fotos.idfotos = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        ResultSet rs = comandoSql.executeQuery();
        
        rs.first();
        
        FotoBean img = new FotoBean();
        img.setId(rs.getInt("idfotos"));
        img.setUrl(rs.getString("url"));
        
        // TO DO: setar dados do imovel se necessario, fazer query com inner join
        return img;
    }
    
    public List<FotoBean> consultarFotosPorImovel(int imovelId) throws SQLException, ClassNotFoundException {
            String sql = "SELECT * FROM fotos INNER JOIN imoveis ON fotos.imoveis_idimoveis = imoveis.id WHERE fotos.imoveis_idimoveis LIKE ?";
            PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
            comandoSql.setInt(1, imovelId);
            ResultSet rs = comandoSql.executeQuery();
            
            FotoBean img = null; 
            listaFotos = new ArrayList<FotoBean>();
            while (rs.next()) {
                    img = new FotoBean();
                    img.setId(rs.getInt("idfotos"));
                    img.setUrl(rs.getString("url"));

                    ImovelBean imo = new ImovelBean();
                    imo.setId(rs.getInt("idimoveis"));
                    imo.setNome(rs.getString("nome"));
                    imo.setEndereco((rs.getString("endereco")));
                    imo.setCidade(rs.getString("cidade"));
                    imo.setDescricao(rs.getString("descricao"));
                    imo.setValor(rs.getFloat("valor"));
                    
                    ImobiliariaBean imobiliaria = new ImobiliariaBean();
                    imobiliaria.setId(rs.getInt("imobiliaria_idimobiliaria"));
                    
                    imo.setImobiliaria(imobiliaria);
                    img.setImovel(imo);
                    listaFotos.add(img);
            }
            return listaFotos;
    }
    
    public void alterarImobiliaria(FotoBean img) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE fotos SET url = ?, imoveis_idimoveis = ? WHERE idfotos = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setString(1, img.getUrl());
        comandoSql.setInt(2, img.getImovel().getId());
        comandoSql.setInt(5, img.getId());
        comandoSql.execute(); 
        Conexao.getInstance().commit(); 
    }
    
    public void excluirFoto(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM fotos WHERE idfotos = ?";
        PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
        comandoSql.setInt(1, id);
        comandoSql.execute();
        Conexao.getInstance().commit();
    }
}
