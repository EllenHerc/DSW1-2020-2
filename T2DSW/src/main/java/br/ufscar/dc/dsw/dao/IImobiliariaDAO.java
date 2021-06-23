/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Imobiliaria;
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

public interface IImobiliariaDAO extends CrudRepository<Imobiliaria, Long>{
    
    Imobiliaria findById(long id);
	
    List<Imobiliaria> findAll();

    Imobiliaria save(Imobiliaria imobiliaria);
    
    public Imobiliaria getImobiliariaByUsuario(Usuario u);

    void deleteById(Long id);
    
}
