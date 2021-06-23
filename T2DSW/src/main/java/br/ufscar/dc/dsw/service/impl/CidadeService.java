/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.ICidadeDAO;
import br.ufscar.dc.dsw.domain.Cidade;
import br.ufscar.dc.dsw.service.spec.ICidadeService;
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
public class CidadeService implements ICidadeService{
    
    @Autowired
    ICidadeDAO dao;

    @Transactional(readOnly = true)
    @Override
    public Cidade buscarPorId(Long id) {
        return dao.findById(id.longValue());    
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cidade> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Cidade cidade) {
        dao.save(cidade);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }    
}
