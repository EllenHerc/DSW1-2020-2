/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.dao.ClienteDao;
import br.ufscar.dc.dsw.dao.UsuarioDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
 * @author caiocesardemorais
 */
@WebServlet(name = "Admin", urlPatterns = { "/admin" })
public class AdmController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        UsuarioBean usu = (UsuarioBean) req.getSession().getAttribute("usuario");
        if(usu != null){
            ClienteDao cli = new ClienteDao();
            try {
                List<ClienteBean> clientes = cli.getAll();
                req.setAttribute("clientes", clientes);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/adm/lista.jsp");
                dispatcher.forward(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
        
        String URL = "usuario/cadastro.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(URL);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Long cpf = Long.parseLong(req.getParameter("cpf"));
        
        ClienteDao clienteDao = new ClienteDao();
        try {
            clienteDao.excluirCliente(cpf);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resp.sendRedirect("admin");
    }
}
