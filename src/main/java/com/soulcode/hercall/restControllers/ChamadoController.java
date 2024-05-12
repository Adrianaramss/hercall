package com.soulcode.hercall.restControllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/chamados")
    public ApiResponse<ChamadoDto> save(@RequestBody ChamadoDto dto){
        return this.chamadoService.save(dto);
    }

    @GetMapping("/chamados")
    public ApiResponse<List<ChamadoDto>> findAll(){
        return this.chamadoService.findAll();
    }

    @GetMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @PutMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> updateById(@PathVariable Long id, @RequestBody ChamadoDto dto){
        return this.chamadoService.updateById(id, dto);
    }

    @DeleteMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

}
