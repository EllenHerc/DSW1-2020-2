package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author caiocesardemorais
 */
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findById(long id);
}
