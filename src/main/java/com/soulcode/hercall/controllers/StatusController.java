package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.StatusDto;
import com.soulcode.hercall.services.StatusService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping("/status")
    public ApiResponse<StatusDto> save(@RequestBody StatusDto dto) {
        return this.statusService.save(dto);
    }

    @GetMapping("/status")
    public ApiResponse<List<StatusDto>> findAll() {
        return this.statusService.findAll();
    }

    @GetMapping("status/{id}")
    public ApiResponse<StatusDto> findById(@PathVariable Long id) {
        return this.statusService.findById(id);
    }

    @PutMapping("/status/{id}")
    public ApiResponse<StatusDto> updateById(@PathVariable Long id, @RequestBody StatusDto dto) {
        return this.statusService.updateById(id, dto);
    }

    @DeleteMapping("/status/{id}")
    public ApiResponse<StatusDto> deleteById(@PathVariable Long id) {
        return this.statusService.deleteById(id);
    }

}
