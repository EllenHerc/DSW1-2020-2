/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Cidade;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface ICidadeService {
    
    Cidade buscarPorId(Long id);

    List<Cidade> buscarTodos();

    void salvar(Cidade cidade);

    void excluir(Long id);
    
}
