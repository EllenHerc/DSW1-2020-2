package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.dao.ImovelDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caiocesardemorais
 */
@WebServlet(name = "NovoImovel", urlPatterns = { "/novo-imovel" })
public class ImovelCadastroController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO : verificar se esta logado como imobiliaria
        String URL = "imoveis/novo-imovel.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(URL);
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            // TODO : verificar se esta logado como imobiliaria;
            // pegar o id da imobiliaria logada para cadastrar o imovel relacionado a ela
            String nome = req.getParameter("nome");
            String endereco = req.getParameter("endereco");
            String cidade = req.getParameter("cidade");
            String descricao = req.getParameter("descricao");
            Float valor = Float.parseFloat(req.getParameter("valor"));

            ImovelDao imovelDao = new ImovelDao();
            ImovelBean imovel = new ImovelBean();

            imovel.setNome(nome);
            imovel.setEndereco(endereco);
            imovel.setCidade(cidade);
            imovel.setDescricao(descricao);
            imovel.setValor(valor);
            
            // TODO: setar id da imobiliaria de acodo com a imobiliaria logada
            // HARDCODE: imobiliaria com id 1 (necessario ter uma cadastrada com esse id no bd)
            ImobiliariaBean imobiliaria = new ImobiliariaBean();
            imobiliaria.setId(1);
            imovel.setImobiliaria(imobiliaria);
                
            imovelDao.CadastrarImovel(imovel);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImovelCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("index.jsp");
    }
    
}
