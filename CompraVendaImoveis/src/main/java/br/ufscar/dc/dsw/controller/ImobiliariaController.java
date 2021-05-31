/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.bean.CidadeBean;
import br.ufscar.dc.dsw.bean.FotoBean;
import br.ufscar.dc.dsw.bean.ImobiliariaBean;
import br.ufscar.dc.dsw.bean.ImovelBean;
import br.ufscar.dc.dsw.dao.CidadeDao;
import br.ufscar.dc.dsw.dao.FotoDao;
import br.ufscar.dc.dsw.dao.ImovelDao;
import br.ufscar.dc.dsw.dao.PropostaDao;
import br.ufscar.dc.dsw.util.Erro;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(urlPatterns = {"/imobiliaria/", "/imobiliaria/imovel/*", "/imobiliaria/imovel/"})
public class ImobiliariaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private FotoDao daoFoto;
    private ImovelDao daoImovel;
    private CidadeDao daoCidade;

    @Override
    public void init() {
        daoFoto = new FotoDao();
        daoImovel = new ImovelDao();
        daoCidade = new CidadeDao();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }
        System.out.println(action);
        ImobiliariaBean imobiliaria = (ImobiliariaBean) request.getSession().getAttribute("imobiliariaLogado");

        if(imobiliaria != null){ 
            if (action.equals("/cadastrarImovel/")) {
                try {
                    apresentaFormCadastroImovel(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ImobiliariaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else if (action.equals("/insercaoImovel/")) {
                try {
                    insereImovel(request, response, imobiliaria);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ImobiliariaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else if (action.equals("") || (action.equals("/lista"))) {
                    try {
                            listaImoveisImobiliaria(request, response, imobiliaria);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(ImobiliariaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } else if (action.equals("/insercaoFoto")) {
                try {
                    insereFoto(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ImobiliariaController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        dispatcher.forward(request, response);
    }
    
    private Map<Long, String> getCidades() throws ClassNotFoundException, SQLException {
        Map<Long, String> cidades = new HashMap<>();
        for (CidadeBean cidade : daoCidade.getAll()) {
            cidades.put(cidade.getId(), cidade.getNome());
        }
        return cidades;
    }

    private void apresentaFormCadastroImovel(HttpServletRequest request, HttpServletResponse response) 
            throws ClassNotFoundException, SQLException, ServletException, IOException {
               
        request.setAttribute("cidades", getCidades());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/imobiliaria/formularioImovel.jsp");
        dispatcher.forward(request, response);    
    }

    private void insereImovel(HttpServletRequest request, HttpServletResponse response, ImobiliariaBean imobiliaria) 
            throws UnsupportedEncodingException, ClassNotFoundException, SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String descricao = request.getParameter("descricao");
        float valor = Float.parseFloat(request.getParameter("valor"));
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String bairro = request.getParameter("bairro");
        Long idcidade = Long.parseLong(request.getParameter("cidade"));
        
        CidadeBean cidade = daoCidade.getById(idcidade);
        
        ImovelBean imovel = new ImovelBean(cep, logradouro, numero, bairro, cidade, descricao, valor, imobiliaria);
        long idimovel = daoImovel.CadastrarImovel(imovel);        
        
        request.setAttribute("idimovel", idimovel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/imobiliaria/formularioFoto.jsp");
        dispatcher.forward(request, response);   
    }

    private void insereFoto(HttpServletRequest request, HttpServletResponse response)
       throws UnsupportedEncodingException, ClassNotFoundException, SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        
        Integer i = 0;
        Long idimovel = Long.parseLong(request.getParameter("idimovel"));
        ImovelBean imovel = daoImovel.getById(idimovel);
        FotoBean img;
        
        String url;
        while (request.getParameter("foto"+i.toString()) != null){
            url = request.getParameter("foto"+i.toString());          
            img = new FotoBean(url, imovel);
            daoFoto.CadastrarFoto(img);
            i++;
        }
        response.sendRedirect("lista");
    }


}

