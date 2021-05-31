/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.ClienteBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.bean.PropostaBean;
import br.ufscar.dc.dsw.bean.UsuarioBean;
import br.ufscar.dc.dsw.dao.ClienteDao;
import br.ufscar.dc.dsw.dao.ImovelDao;
import br.ufscar.dc.dsw.dao.PropostaDao;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.util.EmailService;
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
import javax.mail.internet.InternetAddress;


/**
 *
 * @author ellen
 */
@WebServlet(urlPatterns = {"/cliente/", "/cliente/proposta/*", "/cliente/redirect/*", "/imobiliaria/proposta/", "/imobiliaria/proposta/*"})
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
        ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("clienteLogado");
        ImobiliariaBean imobiliaria = (ImobiliariaBean) request.getSession().getAttribute("imobiliariaLogado");
        Erro erros = new Erro();

        if (cliente != null){
            try {
                if (action.equals("/cadastroProposta")) {
                    apresentaFormCadastro(request, response);
                } else if (action.equals("/insercao")) {
                    insere(request, response, cliente);
                }else if (action.equals("/voltar")) {
                    index(request, response);
                } else if (action.equals("") || (action.equals("/lista"))) {
                    listaProposta(request, response, cliente);
                } else if (action.equals("/imoveis")) {
                    listaImoveisCliente(request, response, cliente);
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PropostaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(imobiliaria != null){
            try {
                if (action.equals("/aceitar")) {
                    apresentaFormAceitaProposta(request, response);
                } else if (action.equals("/enviarContraProposta")) {
                    apresentaFormContraProposta(request, response);
                }else if (action.equals("/naoAceitar")) {
                    naoAceitar(request, response);
                } else if (action.equals("") || (action.equals("/lista"))) {
                    listaPropostaImobiliaria(request, response, imobiliaria);
                } else if (action.equals("/enviaContraProposta")) {
                    enviaContraProposta(request, response, imobiliaria);
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PropostaController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void insere(HttpServletRequest request, HttpServletResponse response, ClienteBean cliente) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Erro erros = new Erro();
        request.setCharacterEncoding("UTF-8");

        String pagamento = request.getParameter("pagamento");
        float valor = Float.parseFloat(request.getParameter("valor"));
        Long idImovel = Long.parseLong(request.getParameter("idimovel"));
        
        ImovelBean imovel = daoImovel.getById(idImovel);
        
	Date date = new Date();
        java.sql.Date dataSql = new java.sql.Date(date.getTime());
        
        PropostaBean proposta = new PropostaBean(cliente, imovel, pagamento, valor, "ABERTO", dataSql);
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

    private void listaProposta(HttpServletRequest request, HttpServletResponse response, ClienteBean cliente)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        List<PropostaBean> listaPropostas = daoProposta.getByClient(cliente);

        request.setAttribute("listaPropostas", listaPropostas);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/clienteHome.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listaImoveisCliente(HttpServletRequest request, HttpServletResponse response, ClienteBean cliente)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        request.setAttribute("listaImoveis", daoImovel.getAll());
        request.setAttribute("cliente", cliente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaImoveis.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormAceitaProposta(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        Long id = Long.parseLong(request.getParameter("idproposta"));
        request.setAttribute("proposta", daoProposta.getById(id));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/proposta/agendaReuniao/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormContraProposta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        Long id = Long.parseLong(request.getParameter("idproposta"));
        daoProposta.atualizaStatus("NÃO ACEITO", id);

        request.setAttribute("proposta", daoProposta.getById(id));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/imobiliaria/contraProposta/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void naoAceitar(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        Long id = Long.parseLong(request.getParameter("idproposta"));
        daoProposta.atualizaStatus("NÃO ACEITO", id);
               
        response.sendRedirect("lista");
    }

    private void listaPropostaImobiliaria(HttpServletRequest request, HttpServletResponse response, ImobiliariaBean imobiliaria)
        throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        List<PropostaBean> listaPropostas = daoProposta.getByImobiliaria(imobiliaria);

        request.setAttribute("listaPropostas", listaPropostas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/imobiliaria/propostas.jsp");
        dispatcher.forward(request, response);
    }

    private void enviaContraProposta(HttpServletRequest request, HttpServletResponse response, ImobiliariaBean imobiliaria) 
            throws ServletException, IOException, SQLException, ClassNotFoundException { 
        
        String pagamento = request.getParameter("pagamento");
        String valor = request.getParameter("valor");
        Long idProposta = Long.parseLong(request.getParameter("idproposta"));
        
        PropostaBean proposta = daoProposta.getById(idProposta);
        
        EmailService service = new EmailService();
		
        InternetAddress from = new InternetAddress(imobiliaria.getUser().getEmail(), imobiliaria.getNome());
        InternetAddress to = new InternetAddress(proposta.getCliente().getUser().getEmail(), proposta.getCliente().getNome());

        String subject1 =  "Contra-Proposta "+proposta.getImovel().getDescricao();

        String line1 = "Você recebeu uma contra-proposta da imobiliaria "+imobiliaria.getNome();
        String line2 = "Valor da contra-proposta: R$ "+valor;
        String line3 = "Condição de pagamento da contra-proposta: "+pagamento;
        String body = line1 + "\n" + line2 + "\n" + line3;

        service.send(from, to, subject1, body);
        
        response.sendRedirect("lista");
    }

}
