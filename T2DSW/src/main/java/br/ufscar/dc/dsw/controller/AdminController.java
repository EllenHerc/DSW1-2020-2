package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IUsuarioService usuarioService;
        
	@Autowired
	private IClienteService clienteService;
        
        @Autowired
	private IPropostaService propostaService;
        
        @Autowired
        private BCryptPasswordEncoder encoder;
        
	@GetMapping("/cadastrarCliente")
	public String cadastrar(ModelMap model, Cliente cliente, Usuario usuario) {
		return "admin/cadastroClientes";
	}
	
	@PostMapping("/salvarCliente")
	public String salvar(Cliente cliente, Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
                usuario.setEnabled(true);
		usuario.setPapel("ROLE_CLIENTE");
                usuario.setSenha(encoder.encode(usuario.getSenha()));
		                
                usuarioService.salvar(usuario);
                cliente.setUsuario(usuario);
                
		clienteService.salvar(cliente);
		
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");
		
		return "redirect:/admin/listarClientes";
	}
	
	@GetMapping("/editarCliente/{id}")
	public String preEditarCliente(@PathVariable("id") Long id, ModelMap model) {
		
		Cliente cliente = clienteService.buscarPorId(id);
                Usuario usuario = cliente.getUsuario();
                usuario.setSenha("");
                model.addAttribute("cliente", cliente);
		model.addAttribute("usuario", usuario);
		return "admin/cadastroClientes";
	}

	@PostMapping("/editaCliente/{id}")
	public String editarCliente(@PathVariable("id") Long id, Cliente cliente, Usuario usuario, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/cadastroClientes";
		}
                usuario.setEnabled(true);
		usuario.setPapel("ROLE_CLIENTE");
                usuario.setSenha(encoder.encode(usuario.getSenha()));
                cliente.setUsuario(usuarioService.salvar(usuario));
		cliente.setId(id);
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admin/listarClientes";
	}

	@GetMapping("/excluirCliente/{id}")
	public String excluirCliente(@PathVariable("id") Long id, RedirectAttributes attr) {
            
                Cliente cli = clienteService.buscarPorId(id);
                Usuario user = usuarioService.buscarPorId(cli.getUsuario().getId());
                
                List<Proposta> listaPropostas = propostaService.buscarTodos(cli);
                
                for (Proposta proposta : listaPropostas){
                    propostaService.excluir(proposta.getId());
                 }
             
		clienteService.excluir(id);
                usuarioService.excluir(user.getId());
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		return "redirect:/admin/listarClientes";
	}
	
	@GetMapping("/listarClientes")
	public String listarCliente(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "admin/listaClientes";
	}
}
