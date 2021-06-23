/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IImovelService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ellen
 */
@Controller
@RequestMapping("/cliente")
public class ClientePropostaController {
    @Autowired
	private IPropostaService service;
	
	@Autowired
	private IClienteService serviceCliente;
	
	@Autowired
	private IImovelService serviceImovel;
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") Long id, ModelMap model, Proposta proposta, RedirectAttributes attr) {
            
            if(service.contarPorClienteImovel(serviceCliente.buscarPorUsuario(this.getUsuario()).getId(), id) > 0){
                attr.addFlashAttribute("fail", "Você já possui uma proposta ABERTA para esse imovel");
		return "redirect:/listaImoveis/listar";
            }

            model.addAttribute("idimovel",id);
            return "cliente/cadastroProposta";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		Cliente cliente = serviceCliente.buscarPorUsuario(this.getUsuario());
		
		model.addAttribute("propostas",service.buscarTodos(cliente));
		return "cliente/listaProposta";
	}
	
	@PostMapping("/salvarProposta/{id}")
	public String salvar(@PathVariable("id") Long id, Proposta proposta, BindingResult result, RedirectAttributes attr) {
		proposta.setCliente(serviceCliente.buscarPorUsuario(this.getUsuario()));
                proposta.setImovel(serviceImovel.buscarPorId(id));
                Date date = new Date();
                java.sql.Date dataSql = new java.sql.Date(date.getTime());
                proposta.setDataemissao(dataSql);		
                proposta.setStatus("ABERTA");
		service.salvar(proposta);
		attr.addFlashAttribute("sucess", "Proposta inserida com sucesso.");
		return "redirect:/cliente/listar";
	}
}
