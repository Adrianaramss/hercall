package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetorController {
    @Autowired
    private SetorService setorService;
    @PostMapping("/setores")
    public SetorDto save(@RequestBody SetorDto dto){
        return this.setorService.save(dto);
    }

    @GetMapping("/setores")
    public List<SetorDto>findAll(){
        return this.setorService.findAll();
    }

    @GetMapping("setores/{id}")
    public SetorDto findById(@PathVariable Long id){
        return this.setorService.findById(id);
    }

    @PutMapping("/setores/{id}")
    public SetorDto updateById(@PathVariable Long id, @RequestBody SetorDto dto){
        return this.setorService.updateById(id, dto);
    }

    @DeleteMapping("/setores/{id}")
    public SetorDto deleteById(@PathVariable Long id){
        return this.setorService.deleteById(id);
    }

}
