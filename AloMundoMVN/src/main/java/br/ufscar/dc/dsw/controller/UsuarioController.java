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
@WebServlet(name = "Cadastro", urlPatterns = { "/cadastro" })
public class UsuarioController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO : verificar se não esta logado; se já estiver logado - redirecionar pra listagem de imoveis
        String URL = "usuario/cadastro.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(URL);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        Boolean isAdmin = Boolean.parseBoolean(req.getParameter("isAdmin"));

        UsuarioBean usuario = new UsuarioBean();
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setIsAdmin(isAdmin);
        usuario.setSenha(senha);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            usuarioDao.CadastrarUsuario(usuario);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("index.jsp");
    }
    
}
