package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.Imovel;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICidadeService;
import br.ufscar.dc.dsw.service.spec.IFotoService;
import br.ufscar.dc.dsw.service.spec.IImobiliariaService;
import br.ufscar.dc.dsw.service.spec.IImovelService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/imobiliaria")
public class ImobiliariaController {
	@Autowired
	private ICidadeService cidadeService;
	@Autowired
	private IImobiliariaService imobiliariaService;
        @Autowired
	private IImovelService imovelService;
        @Autowired
	private IFotoService fotoService;
        
        private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

	@GetMapping("/cadastrarImovel")
	public String cadastrar(ModelMap model, Imovel imovel) {
                model.addAttribute("cidades", cidadeService.buscarTodos());
		return "imobiliaria/cadastroImoveis";
	}
	
	@PostMapping("/salvarImovel")
	public String salvar(@Valid Imovel imovel, BindingResult result, RedirectAttributes attr, ModelMap model) {
            imovel.setImobiliaria(imobiliariaService.buscarPorUsuario(this.getUsuario()));
            int i = 0;
            for (Foto foto : imovel.getFotos()){
                if(foto.getUrl()!=null){
                    imovel.getFotos().set(i, fotoService.salvar(foto));         
                }
                i++;
            }	
            imovelService.salvar(imovel);
            attr.addFlashAttribute("sucess", "Imovel salvo com sucesso.");
            return "redirect:/imobiliaria/listarImoveis";
	}
	
	@GetMapping("/listarImoveis")
	public String listarImoveis(ModelMap model) {
		model.addAttribute("imoveis", imovelService.buscarTodos(imobiliariaService.buscarPorUsuario(this.getUsuario())));
		return "imobiliaria/listaImoveis";
	}
}
