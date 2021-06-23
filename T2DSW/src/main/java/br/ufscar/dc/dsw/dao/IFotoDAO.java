/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.domain.Imovel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ellen
 */
@SuppressWarnings("unchecked")
public interface IFotoDAO extends CrudRepository<Foto, Long>{
    
    Foto findById(long id);
	
    List<Foto> findAll();

    Foto save(Foto foto);

    void deleteById(Long id);
    
}
