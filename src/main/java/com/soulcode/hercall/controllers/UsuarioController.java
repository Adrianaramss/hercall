package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.services.UsuarioService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ApiResponse<UsuarioDto> save(@RequestBody UsuarioDto dto) {
        return this.usuarioService.save(dto);
    }

    @GetMapping("/usuarios")
    public ApiResponse<List<UsuarioDto>> findAll() {
        return this.usuarioService.findAll();
    }

    @GetMapping("usuarios/{id}")
    public ApiResponse<UsuarioDto> findById(@PathVariable Long id) {
        return this.usuarioService.findById(id);
    }

    @PutMapping("/usuarios/{id}")
    public ApiResponse<UsuarioDto> updateById(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        return this.usuarioService.updateById(id, dto);
    }

    @DeleteMapping("/usuarios/{id}")
    public ApiResponse<UsuarioDto> deleteById(@PathVariable Long id) {
        return this.usuarioService.deleteById(id);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/tela-usuario")
    public String telaUsuario() {
        return "tela-usuario";
    }

    @GetMapping("/tela-tecnico")
    public String telaTecnico() {
        return "tela-tecnico";
    }

    @GetMapping("/tela-admin")
    public String telaAdmin() {
        return "tela-admin";
    }

    @GetMapping("/tela-usuarios")
    public String telaUsuarios() {
        return "tela-usuarios";
    }

    @GetMapping("/tela-prioridades")
    public String telaPriodades() {
        return "tela-prioridades";
    }

    @GetMapping("/tela-setores")
    public String telaSetores() {
        return "tela-setores";
    }

}
