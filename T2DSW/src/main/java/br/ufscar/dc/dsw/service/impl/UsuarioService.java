/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
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
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    IUsuarioDAO dao;

    @Override
    public void salvar(Usuario usuario) {
            dao.save(usuario);
    }

    @Override
    public void excluir(Long id) {
            dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorId(Long id) {
            return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> buscarTodos() {
            return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscaPorEmail(String email) {
            return dao.getUserByEmail(email);
    }
    
}
