/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.service.spec.ICidadeService;
import br.ufscar.dc.dsw.service.spec.IFotoService;
import br.ufscar.dc.dsw.service.spec.IImovelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ellen
 */
@Controller
@RequestMapping("/listaImoveis")
public class ListaImoveisController {
    
    @Autowired
	private IImovelService service;
        private IFotoService serviceFoto;
	
	@Autowired
	private ICidadeService cidadeService;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		model.addAttribute("cidades",cidadeService.buscarTodos());
                
                List<Imovel> listaImovel = service.buscarTodos();
                
                
		model.addAttribute("imoveis",listaImovel);
		
		return "listaImoveis";
	}
	
	@GetMapping("/filtro")
	public String aplicarFiltro(ModelMap model, Cidade cidade, BindingResult result) {
				
		model.addAttribute("cidades",cidadeService.buscarTodos());
                
                List<Imovel> listaImovel = service.buscarTodos(cidade);
		
                
		model.addAttribute("imoveis",listaImovel);
		
		return "listaImoveis";
	}
    
}
