/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.rest.resources;

import com.api.rest.models.Usuario;
import com.api.rest.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caiocesardemorais
 */
@RestController
@RequestMapping(value="/api/v1")
public class UsuarioResource {
 
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping("/usuario")
    public List<Usuario> listaUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @GetMapping("/usuario/{id}")
    public Usuario listaUsuarioPorId(@PathVariable(value="id") long id){
        return usuarioRepository.findById(id);
    }
    
    @PostMapping("/usuario")
    public Usuario salvaUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    @DeleteMapping("/usuario/{id}")
    public void removeUsuario(@PathVariable(value="id") long id){
        usuarioRepository.deleteById(id);
    }
    
    @PatchMapping("/usuario/{id}")
    public Usuario atualizaUsuario(@RequestBody Usuario usuario, @PathVariable(value="id") long id){  
        usuario.setId(id);    
        Usuario usuarioAntigo = null;
        usuarioAntigo = usuarioRepository.findById(id);
        if (usuarioAntigo != null) {
            if (usuario.getEmail() != null) usuarioAntigo.setEmail(usuario.getEmail());
            if (usuario.getSenha() != null) usuarioAntigo.setSenha(usuario.getSenha());
            if (usuario.getPapel() != null) usuarioAntigo.setPapel(usuario.getPapel());
            return usuarioRepository.save(usuarioAntigo);
        }
        return usuarioAntigo;
    }
    
    // TODO: remover senha na hora de devolver dados
}
