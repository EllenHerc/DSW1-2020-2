/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.domain.Proposta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ellen
 */
@SuppressWarnings("unchecked")
public interface IPropostaDAO extends CrudRepository<Proposta, Long>{
    
    Proposta findById(long id);
	
    List<Proposta> findAll();

    List<Proposta> findAllByImovel(Imovel i);

    List<Proposta> findAllByCliente(Cliente c);
    
    @Query("SELECT count(p) FROM Proposta p INNER JOIN Cliente cli ON p.cliente = cli.id INNER JOIN Imovel i ON p.imovel = i.id WHERE i.id = :idimovel AND cli.id = :idcliente AND p.status LIKE 'ABERTA'")
    int countByClienteAndImovel(@Param("idcliente") long idcliente, @Param("idimovel") long idimovel);

    @Query("SELECT p FROM Proposta p INNER JOIN Cliente cli ON p.cliente = cli.id INNER JOIN Imovel i ON p.imovel = i.id INNER JOIN Cidade c ON c.id = i.cidade INNER JOIN Imobiliaria im ON im.id = i.imobiliaria WHERE i.imobiliaria = :imobiliaria ORDER BY p.status")
    public List<Proposta> findAllByImobiliaria(@Param("imobiliaria") Imobiliaria imobiliaria);

    Proposta save(Proposta p);

    void deleteById(Long id);
}
