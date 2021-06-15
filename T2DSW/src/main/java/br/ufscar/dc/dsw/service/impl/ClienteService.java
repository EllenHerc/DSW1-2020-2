/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IClienteService;
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
public class ClienteService implements IClienteService{
    
    @Autowired
    IClienteDAO dao;

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarPorId(Long id) {
        return dao.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Cliente c) {
        dao.save(c);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarPorUsuario(Usuario u) {
        return dao.getClienteByUsuario(u);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
    
}
