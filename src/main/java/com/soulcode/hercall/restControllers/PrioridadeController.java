package com.soulcode.hercall.restControllers;

import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.services.PrioridadeService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrioridadeController {
    @Autowired
    private PrioridadeService prioridadeService;

    @PostMapping("/prioridades")
    public ApiResponse<PrioridadeDto> save(@RequestBody PrioridadeDto dto) {
        return this.prioridadeService.save(dto);
    }

    @GetMapping("/prioridades")
    public ApiResponse<List<PrioridadeDto>> findAll() {
        return this.prioridadeService.findAll();
    }

    @GetMapping("prioridades/{id}")
    public ApiResponse<PrioridadeDto> findById(@PathVariable Long id) {
        return this.prioridadeService.findById(id);
    }

    @PutMapping("/prioridades/{id}")
    public ApiResponse<PrioridadeDto> updateById(@PathVariable Long id, @RequestBody PrioridadeDto dto) {
        return this.prioridadeService.updateById(id, dto);
    }

    @DeleteMapping("/prioridades/{id}")
    public ApiResponse<PrioridadeDto> deleteById(@PathVariable Long id) {
        return this.prioridadeService.deleteById(id);
    }

}