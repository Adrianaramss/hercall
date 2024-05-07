package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.StatusDto;
import com.soulcode.hercall.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;
    @PostMapping("/status")
    public StatusDto save(@RequestBody StatusDto dto){
        return this.statusService.save(dto);
    }

    @GetMapping("/status")
    public List<StatusDto>findAll(){
        return this.statusService.findAll();
    }

    @GetMapping("status/{id}")
    public StatusDto findById(@PathVariable Long id){
        return this.statusService.findById(id);
    }

    @PutMapping("/status/{id}")
    public StatusDto updateById(@PathVariable Long id, @RequestBody StatusDto dto){
        return this.statusService.updateById(id, dto);
    }

    @DeleteMapping("/status/{id}")
    public StatusDto deleteById(@PathVariable Long id){
        return this.statusService.deleteById(id);
    }

}
