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

    @GetMapping("/status")
    public ApiResponse<List<StatusDto>> findAll() {
        return this.statusService.findAll();
    }

    @GetMapping("status/{id}")
    public ApiResponse<StatusDto> findById(@PathVariable Long id) {
        return this.statusService.findById(id);
    }

}
