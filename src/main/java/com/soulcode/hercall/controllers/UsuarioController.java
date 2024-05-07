package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/usuarios")
    public UsuarioDto save(@RequestBody UsuarioDto dto){
        return this.usuarioService.save(dto);
    }

    @GetMapping("/usuarios")
    public List<UsuarioDto>findAll(){
        return this.usuarioService.findAll();
    }

    @GetMapping("usuarios/{id}")
    public UsuarioDto findById(@PathVariable Long id){
        return this.usuarioService.findById(id);
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioDto updateById(@PathVariable Long id, @RequestBody UsuarioDto dto){
        return this.usuarioService.updateById(id, dto);
    }

    @DeleteMapping("/usuarios/{id}")
    public UsuarioDto deleteById(@PathVariable Long id){
        return this.usuarioService.deleteById(id);
    }

}
