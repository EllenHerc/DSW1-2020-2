/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IImovelDAO;
import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Imovel;
import br.ufscar.dc.dsw.service.spec.IImovelService;
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
public class ImovelService implements IImovelService{
    
    @Autowired
    IImovelDAO dao;
    
    @Transactional(readOnly = true)
    @Override
    public Imovel buscarPorId(Long id) {
        return dao.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Imovel> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Imovel> buscarTodos(Imobiliaria i) {
        return dao.findAllByImobiliaria(i);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Imovel> buscarTodos(Cidade c) {
        return dao.findAllByCidade(c);
    }

    @Override
    public void salvar(Imovel i) {
        dao.save(i);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
    
}
