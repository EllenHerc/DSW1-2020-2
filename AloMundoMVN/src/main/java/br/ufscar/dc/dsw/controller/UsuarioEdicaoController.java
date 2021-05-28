package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.UsuarioBean;
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
        try {
            // TODO : verificar se esta logado e se é o proprietario dos dados (id parametro == id logado)
            int id = Integer.parseInt(req.getParameter("id"));
            UsuarioDao usuarioDao = new UsuarioDao();
            
            String URL = "usuario/edicao.jsp";
            UsuarioBean usuario = usuarioDao.consultarUsuario(id);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher(URL + "?id= "+ usuario.getId());
            req.setAttribute("usuario", usuario);
            dispatcher.forward(req, resp);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioEdicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            UsuarioDao usuarioDao = new UsuarioDao();
            UsuarioBean usuario = usuarioDao.consultarUsuario(id);

            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setSenha(senha);

            usuarioDao.alterarUsuario(usuario);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioEdicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("index.jsp");
    }
    
}
