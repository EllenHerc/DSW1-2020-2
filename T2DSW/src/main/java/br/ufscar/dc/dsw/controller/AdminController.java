package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Cliente;
import javax.validation.Valid;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IClienteService clienteService;
        
        private BCryptPasswordEncoder encoder;

	@GetMapping("/cadastrarCliente")
	public String cadastrar(ModelMap model, Cliente cliente, Usuario usuario) {
		return "admin/cadastroClientes";
	}
	
	@PostMapping("/salvarCliente")
	public String salvar(@Valid Cliente cliente, @Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		usuario.setEnabled(true);
		usuario.setPapel("ROLE_CLIENTE");
                usuario.setSenha(encoder.encode(usuario.getSenha()));
		
                cliente.setUsuario(usuarioService.salvar(usuario));
                
		clienteService.salvar(cliente);
		
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");
		
		return "redirect:/admin/listarClientes";
	}
	
	@GetMapping("/editarCliente/{id}")
	public String preEditarCliente(@PathVariable("id") Long id, ModelMap model) {
		
		Cliente cliente = clienteService.buscarPorId(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("usuario", cliente.getUsuario());
		return "admin/cadastroClientes";
	}

	@PostMapping("/editarCliente")
	public String editarCliente(@Valid Cliente cliente, Usuario usuario, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/cadastroClientes";
		}
		
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admin/listarHoteis";
	}

	@GetMapping("/excluirCliente/{id}")
	public String excluirCliente(@PathVariable("id") Long id, RedirectAttributes attr) {
                    Usuario user = usuarioService.buscarPorId(clienteService.buscarPorId(id).getUsuario().getId());
		usuarioService.excluir(user.getId());
		clienteService.excluir(id);
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		return "redirect:/admin/listarClientes";
	}
	
	@GetMapping("/listarClientes")
	public String listarCliente(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "admin/listaClientes";
	}
}
