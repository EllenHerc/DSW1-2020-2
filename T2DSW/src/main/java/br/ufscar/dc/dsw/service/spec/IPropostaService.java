/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.domain.Proposta;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface IPropostaService {
    
    Proposta buscarPorId(Long id);
	
    List<Proposta> buscarTodos();
    
    List<Proposta> buscarTodos(Imovel i);

    List<Proposta> buscarTodos(Cliente c);

    List<Proposta> buscarTodos(Imobiliaria i);

    void salvar(Proposta p);

    void excluir(Long id);	
    
}
