package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.dao.ClienteDao;
import br.ufscar.dc.dsw.dao.UsuarioDao;
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
@WebServlet(name = "Edicao", urlPatterns = { "/edicao" })
public class UsuarioEdicaoController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = "usuario/edicao.jsp";
        ClienteBean cliente = (ClienteBean) req.getSession().getAttribute("clienteLogado");
        req.setAttribute("usuario", cliente);
        RequestDispatcher dispatcher = req.getRequestDispatcher(URL);
        dispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            String nome = req.getParameter("nome");
            String telefone = req.getParameter("telefone");
            String sexo = req.getParameter("sexo");
            
            ClienteBean cliente = (ClienteBean) req.getSession().getAttribute("clienteLogado");
            
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setSexo(sexo);
            
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.alterarCliente(cliente);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioEdicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("cliente/clienteHome.jsp");
    }
    
}
