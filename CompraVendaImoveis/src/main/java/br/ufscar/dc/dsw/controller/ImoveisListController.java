/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.CidadeBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.dao.ImovelDao;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ellen
 */
@WebServlet(urlPatterns = {"/imoveis/*"})
public class ImoveisListController extends HttpServlet{
    private ImovelDao dao;

    @Override
    public void init() {
        dao = new ImovelDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ImoveisListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<ImovelBean> listaImoveis = null;
        String strCidade = request.getParameter("cidade");
        if(strCidade==null){
            try {
                listaImoveis = dao.getAll();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ImoveisListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String nomeCidade = strCidade.substring(0,strCidade.length()-5);
            
            CidadeBean cidade = new CidadeBean();
            cidade.setNome(nomeCidade);
            cidade.setUf(strCidade.substring(nomeCidade.length()+3,strCidade.length()));
            
            listaImoveis = dao.getByCity(cidade);
        }
        request.setAttribute("listaImoveis", listaImoveis);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaImoveis.jsp");
        dispatcher.forward(request, response);
    }
    
}
