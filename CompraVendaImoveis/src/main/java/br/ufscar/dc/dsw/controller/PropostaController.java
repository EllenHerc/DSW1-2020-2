/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.bean.PropostaBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.dao.ClienteDao;
import br.ufscar.dc.dsw.dao.ImovelDao;
import br.ufscar.dc.dsw.dao.PropostaDao;
import br.ufscar.dc.dsw.util.Erro;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 *
 * @author ellen
 */
@WebServlet(urlPatterns = {"/cliente/", "/cliente/proposta/*", "/cliente/redirect/*"})
public class PropostaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PropostaDao daoProposta;
    private ClienteDao daoCliente;
    private ImovelDao daoImovel;

    @Override
    public void init() {
        daoProposta = new PropostaDao();
        daoCliente = new ClienteDao();
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
        UsuarioBean usuario = (UsuarioBean) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
        } else if (usuario.getPapel().equals("CLIENTE")) {
            try {
                if (action.equals("/cadastroProposta")) {
                    apresentaFormCadastro(request, response);
                } else if (action.equals("/insercao")) {
                    insere(request, response, usuario.getEmail());
                }else if (action.equals("/voltar")) {
                    index(request, response);
                } else if (action.equals("") || (action.equals("/lista"))) {
                    listaProposta(request, response, usuario.getEmail());
                } else if (action.equals("/imoveis")) {
                    listaImoveisCliente(request, response, usuario.getEmail());
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            } catch (SQLException ex) {
                Logger.getLogger(PropostaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PropostaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            erros.add("Acesso não autorizado!");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
        }
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        Long idImovel = Long.parseLong(request.getParameter("idimovel"));
        request.setAttribute("imovel", daoImovel.getById(idImovel));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Erro erros = new Erro();
        request.setCharacterEncoding("UTF-8");

        String pagamento = request.getParameter("pagamento");
        float valor = Float.parseFloat(request.getParameter("valor"));
        Long idImovel = Long.parseLong(request.getParameter("idimovel"));
        ImovelBean imovel = daoImovel.getById(idImovel);
	Date date = new Date();
        java.sql.Date dataSql = new java.sql.Date(date.getTime());
        
        PropostaBean proposta = new PropostaBean(daoCliente.consultarClienteEmail(email), imovel, pagamento, valor, "NÃO ACEITO", dataSql);
        if(daoProposta.verificaClienteImovel(proposta)){
            erros.add("Proposta não inserida! "
                    + "Você já possui uma proposta para este imovel.");
            request.setAttribute("imovel", imovel);
            request.setAttribute("mensagens", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formulario.jsp");
        dispatcher.forward(request, response);
        }else{
            daoProposta.CadastrarProposta(proposta);
            response.sendRedirect("lista");
        }
    }

    private void listaProposta(HttpServletRequest request, HttpServletResponse response, String email)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        ClienteBean cliente = daoCliente.consultarClienteEmail(email);
        List<PropostaBean> listaPropostas = daoProposta.getByClient(cliente);

        request.setAttribute("listaPropostas", listaPropostas);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/clienteHome.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaImoveisCliente(HttpServletRequest request, HttpServletResponse response, String email)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        request.setAttribute("listaImoveis", daoImovel.getAll());
        request.setAttribute("cliente", daoCliente.consultarClienteEmail(email));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaImoveis.jsp");
        dispatcher.forward(request, response);
    }

}
