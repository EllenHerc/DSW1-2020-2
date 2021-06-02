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
import br.ufscar.dc.dsw.dao.ImobiliariaDao;
import br.ufscar.dc.dsw.dao.UsuarioDao;
import br.ufscar.dc.dsw.util.Erro;
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
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet{
    private static final long serialVersionUID = 1L;
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            if (email == null || email.isEmpty()) {
                erros.add("Email de login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.isExisteErros()) {
                UsuarioDao dao = new UsuarioDao();
                UsuarioBean usuario = null;
                try {
                    usuario = dao.consultarUsuarioPorEmail(email);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    if (usuario != null) {
                        if (usuario.getSenha().equals(senha)) {
                            request.getSession().setAttribute("usuarioLogado", usuario);
                            switch (usuario.getPapel()) {
                                case "ADMIN":
                                    response.sendRedirect("admin/");
                                    break;
                                case "CLIENTE":
                                    ClienteDao daoCliente = new ClienteDao();
                                    ClienteBean cliente = null;
                                    try {
                                        cliente = daoCliente.consultarClienteEmail(usuario.getEmail());
                                        request.getSession().setAttribute("cliente", cliente);
                                        response.sendRedirect("cliente/clienteHome.jsp");
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                        response.sendRedirect("login.jsp");
                                    }
                                    
                                    break;
                                case "IMOBILIARIA":
                                    ImobiliariaDao daoImobiliaria = new ImobiliariaDao();
                                    ImobiliariaBean imobiliaria = null;
                                    try {
                                        imobiliaria = daoImobiliaria.consultarImobiliariaEmail(usuario.getEmail());
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.getSession().setAttribute("imobiliariaLogado", imobiliaria);
                                    response.sendRedirect("imobiliaria/");
                                    break;
                                default:
                                    break;
                            }
                                return;
                        } else {
                                erros.add("Senha inválida!");
                        }
                    } else {
                            erros.add("Usuário não encontrado!");
                    }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }
    
}
