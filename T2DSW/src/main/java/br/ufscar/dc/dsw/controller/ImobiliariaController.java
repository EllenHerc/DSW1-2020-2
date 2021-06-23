package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Reuniao;
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
import br.ufscar.dc.dsw.service.impl.EmailService;
import br.ufscar.dc.dsw.service.spec.ICidadeService;
import br.ufscar.dc.dsw.service.spec.IFotoService;
import br.ufscar.dc.dsw.service.spec.IImobiliariaService;
import br.ufscar.dc.dsw.service.spec.IImovelService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/imobiliaria")
public class ImobiliariaController {
	@Autowired
	private ICidadeService cidadeService;
	@Autowired
	private IImobiliariaService imobiliariaService;
        @Autowired
        private IPropostaService propostaService;
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
        
        @GetMapping("/listar")
	public String listar(ModelMap model) {
		
		Imobiliaria imobiliaria = imobiliariaService.buscarPorUsuario(this.getUsuario());
		
		model.addAttribute("propostas",propostaService.buscarTodos(imobiliaria));
		return "imobiliaria/listaProposta";
	
        }
        @GetMapping("/aceitarProposta/{id}")
	public String aceitarProposta(@PathVariable("id") Long id, ModelMap model, Reuniao reuniao) {
		            
		model.addAttribute("idproposta",id);

                return "imobiliaria/cadastroReuniao";
	
        }
        @GetMapping("/enviarContraProposta/{id}")
	public String enviarContraProposta(@PathVariable("id") String id, ModelMap model, Proposta proposta) {
		            
		model.addAttribute("idproposta",Long.parseLong(id));

                return "imobiliaria/cadastroContraProposta";
	
        }
        @PostMapping("/salvarReuniao/{id}")
	public String salvarReuniao(@PathVariable("id") Long id, @Valid Reuniao reuniao, BindingResult result, RedirectAttributes attr, ModelMap model) throws UnsupportedEncodingException {
            Proposta proposta = propostaService.buscarPorId(id);
            proposta.setStatus("ACEITA");
            
            propostaService.salvar(proposta);
            
            Imobiliaria imobiliaria = imobiliariaService.buscarPorUsuario(this.getUsuario());
            EmailService service = new EmailService();

            InternetAddress from = new InternetAddress(this.getUsuario().getEmail(), imobiliaria.getNome());
            InternetAddress to = new InternetAddress(proposta.getCliente().getUsuario().getEmail(), proposta.getCliente().getNome());

            String subject1 =  "Proposta Aceita para o imóvel "+proposta.getImovel().getDescricao();

            String line1 = "Sua proposta para o imovel: "+proposta.getImovel().getDescricao()+" foi aceita pela imobiliaria: "+imobiliaria.getNome();
            String line2 = "Valor da proposta: R$ "+proposta.getValor();
            String line3 = "Condição de pagamento: "+proposta.getPagamento();
            String line4 = "Uma reunião (via videoconferência) foi agendada pela imobiliaria para acertar os detalhes da compra do imóvel ";
            String line5 = "Data: "+reuniao.getData()+"\nHorario: "+reuniao.getHora()+"\nLink da reunião (videoconferência): "+reuniao.getLink();

            String body = line1 + "\n" + line2 + "\n" + line3 + "\n\n" + line4 + "\n" + line5;

            service.send(from, to, subject1, body);

            model.addAttribute("propostas",propostaService.buscarTodos(imobiliaria));
            attr.addFlashAttribute("sucess", "Proposta aceita com sucesso.");
            return "imobiliaria/listaProposta";
	}
        
        @PostMapping("/salvarContraProposta/{id}")
	public String salvarContraProposta(@PathVariable("id") Long id, @Valid Proposta proposta, BindingResult result, RedirectAttributes attr, ModelMap model) throws UnsupportedEncodingException {
            Proposta proposta2 = propostaService.buscarPorId(id);
            proposta2.setStatus("NÃO ACEITA");

            propostaService.salvar(proposta2);

            Imobiliaria imobiliaria = imobiliariaService.buscarPorUsuario(this.getUsuario());
            EmailService service = new EmailService();

            InternetAddress from = new InternetAddress(imobiliaria.getUsuario().getEmail(), imobiliaria.getNome());
            InternetAddress to = new InternetAddress(proposta2.getCliente().getUsuario().getEmail(), proposta2.getCliente().getNome());

            String subject1 =  "Contra-Proposta "+proposta2.getImovel().getDescricao();

            String line1 = "Você recebeu uma contra-proposta da imobiliaria "+imobiliaria.getNome();
            String line2 = "Valor da contra-proposta: R$ "+proposta.getValor();
            String line3 = "Condição de pagamento da contra-proposta: "+proposta.getPagamento();
            String body = line1 + "\n" + line2 + "\n" + line3;

            service.send(from, to, subject1, body);

            model.addAttribute("propostas",propostaService.buscarTodos(imobiliaria));
            attr.addFlashAttribute("sucess", "Proposta recusada e Contra-proposta enviada com sucesso.");
            return "imobiliaria/listaProposta";
	}
        
        @GetMapping("/naoAceitar/{id}")
	public String naoAceitar(@PathVariable("id") String id, RedirectAttributes attr, ModelMap model) throws UnsupportedEncodingException {
            
            Proposta proposta = propostaService.buscarPorId(Long.parseLong(id));
            proposta.setStatus("NÃO ACEITA");
            
            propostaService.salvar(proposta);

            EmailService service = new EmailService();
            
            Imobiliaria imobiliaria = imobiliariaService.buscarPorUsuario(this.getUsuario());

            InternetAddress from = new InternetAddress(this.getUsuario().getEmail(), imobiliaria.getNome());
            InternetAddress to = new InternetAddress(proposta.getCliente().getUsuario().getEmail(), proposta.getCliente().getNome());

            String subject1 =  "Proposta recusada para o imóvel "+proposta.getImovel().getDescricao();

            String line1 = "Sua proposta para o imovel: "+proposta.getImovel().getDescricao()+" foi recusada pela imobiliaria: "+imobiliaria.getNome();
            String line2 = "Valor da proposta: R$ "+proposta.getValor();
            String line3 = "Condição de pagamento: "+proposta.getPagamento();

            String body = line1 + "\n" + line2 + "\n" + line3;

            service.send(from, to, subject1, body);
				
            model.addAttribute("propostas",propostaService.buscarTodos(imobiliaria));
            attr.addFlashAttribute("sucess", "Proposta recusada com sucesso.");
            return "redirect:imobiliaria/listaProposta";
	}
}
