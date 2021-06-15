/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.Imovel;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface IFotoService {
    Foto buscarPorId(Long id);

    List<Foto> buscarTodos();
    
    List<Foto> buscarTodos(Imovel i);
    
    void salvar(Foto f);

    void excluir(Long id);
    
}
