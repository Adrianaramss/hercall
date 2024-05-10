package com.soulcode.hercall.controllers;

import ch.qos.logback.core.model.Model;
import com.soulcode.hercall.dtos.StatusDto;
import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public UsuarioDto save(@RequestBody UsuarioDto dto) {
        return this.usuarioService.save(dto);
    }

    @GetMapping("/usuarios")
    public List<UsuarioDto> findAll() {
        return this.usuarioService.findAll();
    }

    @GetMapping("usuarios/{id}")
    public UsuarioDto findById(@PathVariable Long id) {
        return this.usuarioService.findById(id);
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioDto updateById(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        return this.usuarioService.updateById(id, dto);
    }

    @DeleteMapping("/usuarios/{id}")
    public UsuarioDto deleteById(@PathVariable Long id) {
        return this.usuarioService.deleteById(id);
    }


    @GetMapping("/tela-tecnico")
    public String telaTecnico() {
        return "tela-tecnico";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tela-usuario")
    public String telaUsuario() {
        return "tela-usuario";
    }

    @GetMapping("/tela-admin")
    public String telaAdmin() {
        return "tela-admin";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

}
