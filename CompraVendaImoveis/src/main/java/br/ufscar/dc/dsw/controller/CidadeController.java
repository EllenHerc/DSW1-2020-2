/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CidadeDao;
import br.ufscar.dc.dsw.bean.CidadeBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;

/**
 *
 * @author ellen
 */
@WebServlet(urlPatterns = {"/buscaPorNome/*", "/cliente/redirect/imoveis/buscaPorNome/*"})
public class CidadeController extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    protected List<String> buscaCidade(String term) throws ClassNotFoundException, SQLException{
        
        List<CidadeBean> cidades = new CidadeDao().getAll();
                List<String> listaCidades = new ArrayList<>();
        if (term.length()>0){
            for (CidadeBean cidade : cidades) {
                if (cidade.getNome().toLowerCase().contains(term.toLowerCase())) {
                    listaCidades.add(cidade.getNome()+" - "+cidade.getUf());          
                }
             }
        }
        return listaCidades;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gsonBuilder = new GsonBuilder().create();
        
        PrintWriter out = response.getWriter();
	out.print(gsonBuilder.toJson(buscaCidade(request.getParameter("term"))));
	out.flush();
	out.close();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
