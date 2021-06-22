/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IPropostaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ellen
 */
@Service
@Transactional(readOnly = false)
public class PropostaService implements IPropostaService{
    
    @Autowired
    IPropostaDAO dao;

    @Transactional(readOnly = true)
    @Override
    public Proposta buscarPorId(Long id) {
        return dao.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> buscarTodos(Imovel i) {
        return dao.findAllByImovel(i);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> buscarTodos(Cliente c) {
        return dao.findAllByCliente(c);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> buscarTodos(Imobiliaria i) {
        return dao.findAllByImobiliaria(i);
    }

    @Override
    public void salvar(Proposta p) {
        dao.save(p);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public int contarPorClienteImovel(Long idcliente, Long idimovel) {
        return dao.countByClienteAndImovel(idcliente, idimovel);
    }
    
}
