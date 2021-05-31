/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.dao.ImovelDao;
import br.ufscar.dc.dsw.dao.PropostaDao;
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
/**
 *
 * @author ellen
 */
@WebServlet(urlPatterns = {"/imobiliaria/"})
public class ImobiliariaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PropostaDao daoProposta;
    private ImovelDao daoImovel;

    @Override
    public void init() {
        daoProposta = new PropostaDao();
        daoImovel = new ImovelDao();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        ImobiliariaBean imobiliaria = (ImobiliariaBean) request.getSession().getAttribute("imobiliariaLogado");

        if(imobiliaria != null){           
            try {
                listaImoveisImobiliaria(request, response, imobiliaria);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ImobiliariaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } else {
            response.sendRedirect(request.getContextPath());
        } /*else {
            erros.add("Acesso não autorizado!");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
        }*/
    }

    private void listaImoveisImobiliaria(HttpServletRequest request, HttpServletResponse response, ImobiliariaBean imobiliaria)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        request.setAttribute("listaImoveis", daoImovel.getByImobiliaria(imobiliaria));
        request.setAttribute("imobiliaria", imobiliaria);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/imobiliaria/imobiliariaHome.jsp");
        dispatcher.forward(request, response);}


}

