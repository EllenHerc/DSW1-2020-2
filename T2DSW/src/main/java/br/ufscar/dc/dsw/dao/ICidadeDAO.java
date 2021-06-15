/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cidade;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ellen
 */
@SuppressWarnings("unchecked")
public interface ICidadeDAO extends CrudRepository<Cidade, Long>{
    
        Cidade findById(long id);
	
	List<Cidade> findAll();
	
	Cidade save(Cidade cidade);

	void deleteById(Long id);
}
