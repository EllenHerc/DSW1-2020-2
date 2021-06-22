/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dao.ICidadeDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.IImobiliariaDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Usuario;
import java.sql.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ellen
 */
@SpringBootApplication
public class T2dswApplication {
    
    public static void main(String[] args) {
		SpringApplication.run(T2dswApplication.class, args);
	}
    @Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IClienteDAO clienteDAO, IImobiliariaDAO imobiliariaDAO, ICidadeDAO cidadeDAO) {
		return (args) -> {
			
			Usuario adm = new Usuario();
			adm.setEmail("admin@admin");
			adm.setSenha(encoder.encode("admin"));
			adm.setPapel("ROLE_ADMIN");
			adm.setEnabled(true);
			usuarioDAO.save(adm);	
			
			Cidade c1 = new Cidade();
			c1.setNome("São Paulo");
			c1.setUf("SP");
			cidadeDAO.save(c1);
			
			Cidade c2 = new Cidade();
			c2.setNome("São Carlos");
			c2.setUf("SP");
			cidadeDAO.save(c2);
			
			Cidade c3 = new Cidade();
			c3.setNome("Rio de Janeiro");
			c3.setUf("RJ");
			cidadeDAO.save(c3);
			
			Cidade c4 = new Cidade();
			c4.setNome("Belo Horizonte");
			c4.setUf("MG");
			cidadeDAO.save(c4);
			
			Cidade c5 = new Cidade();
			c5.setNome("Florianópolis");
			c5.setUf("SC");
			cidadeDAO.save(c5);                        
                        
                        Usuario u1 = new Usuario();
			u1.setEmail("maria@email");
			u1.setSenha(encoder.encode("123"));
			u1.setPapel("ROLE_CLIENTE");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
                                                
                        Cliente cl1 = new Cliente();
                        cl1.setCpf("475.822.877-95");
                        cl1.setNome("Maria Helena");
                        cl1.setNascimento(Date.valueOf("2000-01-12"));
                        cl1.setSexo("FEMININO");
                        cl1.setTelefone("16988721547");
                        cl1.setUsuario(u1);
                        clienteDAO.save(cl1);
                        
                        Usuario u2 = new Usuario();
			u2.setEmail("lopes@email");
			u2.setSenha(encoder.encode("123"));
			u2.setPapel("ROLE_IMOBILIARIA");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
                        
                        Imobiliaria i1 = new Imobiliaria();
                        i1.setCnpj("15.673.605/0001-10");
                        i1.setNome("LOPES CONSULTORIA DE IMOVEIS");
                        i1.setDescricao("LOPES CONSULTORIA DE IMOVEIS', 'A maior imobiliária do Brasil, e encontre imóveis de todos os tamanhos e para todos os bolsos");
			i1.setUsuario(u2);
                        imobiliariaDAO.save(i1);
                        
                        Usuario u3 = new Usuario();
			u3.setEmail("predial@email");
			u3.setSenha(encoder.encode("123"));
			u3.setPapel("ROLE_IMOBILIARIA");
			u3.setEnabled(true);
			Usuario u4 = usuarioDAO.save(u3);
                        System.out.println(u4.getId());
                        
                        Imobiliaria i2 = new Imobiliaria();
                        i2.setCnpj("05.887.451/0001-78");
                        i2.setNome("PREDIAL CENTER CONSULTORIA");
                        i2.setDescricao("Tradição e confiança na venda, locação e administração de imóveis em São Carlos");
			i2.setUsuario(u3);
                        imobiliariaDAO.save(i2);
			
			
			
		};
        }
}
