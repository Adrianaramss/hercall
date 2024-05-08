package com.soulcode.hercall.controllers;
import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;
    @PostMapping("/chamados")
    public ChamadoDto save(@RequestBody ChamadoDto dto){
        return this.chamadoService.save(dto);
    }

    @GetMapping("/chamados")
    public List<ChamadoDto>findAll(){
        return this.chamadoService.findAll();
    }

    @GetMapping("/chamados/{id}")
    public ChamadoDto findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @PutMapping("/chamados/{id}")
    public ChamadoDto updateById(@PathVariable Long id, @RequestBody ChamadoDto dto){
        return this.chamadoService.updateById(id, dto);
    }

    @DeleteMapping("/chamados/{id}")
    public String deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }



}
