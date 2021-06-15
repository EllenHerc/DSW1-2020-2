/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ellen
 */
@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
    
    Cliente findById(long id);
	
    List<Cliente> findAll();

    Cliente save(Cliente c);
    
    public Cliente getClienteByUsuario(Usuario usuario);
    
    void deleteById(Long id);
}
