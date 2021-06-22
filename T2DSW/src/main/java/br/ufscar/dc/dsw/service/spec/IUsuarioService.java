/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Usuario;
import java.util.List;

/**
 *
 * @author ellen
 */
public interface IUsuarioService {
    Usuario buscarPorId(Long id);
	
    Usuario buscaPorEmail(String email);

    List<Usuario> buscarTodos();

    Usuario salvar(Usuario usuario);

    void excluir(Long id);	
    
}
