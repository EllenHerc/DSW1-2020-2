/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.spec;

import java.util.List;

import domain.Usuario;

/**
 *
 * @author caiocesardemorais
 */
public interface IUsuarioService {

    Usuario buscarPorId(Long id);
    
    Usuario buscaPorEmail(String email);

    List<Usuario> buscarTodos();

    void salvar(Usuario usu);

    void excluir(Long id);
}
