/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface IImovelService {
    Imovel buscarPorId(Long id);

    List<Imovel> buscarTodos();
    
    List<Imovel> buscarTodos(Imobiliaria i);
    
    List<Imovel> buscarTodos(Cidade c);

    void salvar(Imovel i);

    void excluir(Long id);
}
