/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Usuario;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface IImobiliariaService {
    
    Imobiliaria buscarPorId(Long id);

    List<Imobiliaria> buscarTodos();

    void salvar(Imobiliaria i);
    
    Imobiliaria buscarPorUsuario(Usuario u);

    void excluir(Long id);
    
}
