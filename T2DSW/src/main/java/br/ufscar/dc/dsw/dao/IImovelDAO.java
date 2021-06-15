/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ellen
 */
@SuppressWarnings("unchecked")
public interface IImovelDAO extends CrudRepository<Imovel, Long>{
    
    Imovel findById(long id);
	
    List<Imovel> findAll();

    Imovel save(Imovel imovel);
    
    List<Imovel> findAllByImobiliaria(Imobiliaria i);
    
    List<Imovel> findAllByCidade(Cidade c);

    void deleteById(Long id);    
}
