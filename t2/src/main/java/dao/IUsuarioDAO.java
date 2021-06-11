package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caiocesardemorais
 */
@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    
    @Override
    List<Usuario> findAll();

    Usuario findById(long id);
    
    @Override
    Usuario save(Usuario usu);

    @Override
    void deleteById(Long id);
}
