/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IFotoDAO;
import br.ufscar.dc.dsw.domain.Foto;
import br.ufscar.dc.dsw.service.spec.IFotoService;
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
public class FotoService implements IFotoService{
    
    @Autowired
    IFotoDAO dao;
    
    @Transactional(readOnly = true)
    @Override
    public Foto buscarPorId(Long id) {
        return dao.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Foto> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public Foto salvar(Foto f) {
        return dao.save(f);
    }
    
    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
    
}
