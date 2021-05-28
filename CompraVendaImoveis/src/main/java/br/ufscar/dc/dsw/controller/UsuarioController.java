package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.dao.ClienteDao;
import br.ufscar.dc.dsw.dao.UsuarioDao;
import java.io.IOException;
import java.sql.Date;
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
        
        String cpf = req.getParameter("cpf");
        String nome = req.getParameter("nome");
        String nascimento = req.getParameter("nascimento");
        String telefone = req.getParameter("telefone");
        String sexo = req.getParameter("sexo");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        UsuarioBean usuario = new UsuarioBean();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setPapel("CLIENTE");
        
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            usuarioDao.CadastrarUsuario(usuario);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*insere cliente com referencia ao usuario*/
        ClienteBean cliente = new ClienteBean();
        cliente.setNome(nome);
        cliente.setCpf(Long.parseLong(cpf));
        cliente.setTelefone(telefone);
        cliente.setSexo(sexo);
        cliente.setNascimento(Date.valueOf(nascimento));
        cliente.setUser(usuario);
        
        ClienteDao clienteDao = new ClienteDao();
        try {
            clienteDao.CadastrarCliente(cliente);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resp.sendRedirect("index.jsp");
    }
    
}
