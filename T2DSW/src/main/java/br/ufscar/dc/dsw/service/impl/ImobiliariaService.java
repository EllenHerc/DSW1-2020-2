/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IImobiliariaDAO;
import br.ufscar.dc.dsw.domain.Imobiliaria;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IImobiliariaService;
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
public class ImobiliariaService implements IImobiliariaService {

    @Autowired
    IImobiliariaDAO dao;
    
    @Transactional(readOnly = true)
    @Override
    public Imobiliaria buscarPorId(Long id) {
        return dao.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Imobiliaria> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Imobiliaria i) {
        dao.save(i);
    }

    @Transactional(readOnly = true)
    @Override
    public Imobiliaria buscarPorUsuario(Usuario u) {
        return dao.getImobiliariaByUsuario(u);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
    
}
