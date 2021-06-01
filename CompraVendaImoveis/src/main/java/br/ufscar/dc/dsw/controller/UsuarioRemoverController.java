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
@WebServlet(name = "Remocao", urlPatterns = { "/remocao" })
public class UsuarioRemoverController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
             ClienteBean cliente = (ClienteBean) req.getSession().getAttribute("clienteLogado");
            
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.excluirUsuario(cliente.getUser().getId());
            
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.excluirCliente(cliente.getCpf());
            
            resp.sendRedirect("index.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioRemoverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
