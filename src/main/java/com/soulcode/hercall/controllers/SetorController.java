package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.models.Setor;
import com.soulcode.hercall.services.SetorService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetorController {
    @Autowired
    private SetorService setorService;

    @PostMapping("/setores")
    public String save(@ModelAttribute SetorDto dto) {
        ApiResponse<SetorDto> setorCadastrado = this.setorService.save(dto);

        return "redirect:/tela-setores";
    }

    @GetMapping("/setores")
    public ApiResponse<List<SetorDto>> findAll() {
        return this.setorService.findAll();
    }

    @GetMapping("/setores/{id}")
    public ApiResponse<SetorDto> findById(@PathVariable Long id) {
        return this.setorService.findById(id);
    }

    @PutMapping("/setores/{id}")
    public ApiResponse<SetorDto> updateById(@PathVariable Long id, @RequestBody SetorDto dto) {
        return this.setorService.updateById(id, dto);
    }

    @DeleteMapping("/setores/{id}")
    public ApiResponse<SetorDto> deleteById(@PathVariable Long id) {
        return this.setorService.deleteById(id);
    }

    @PostMapping("/delete-setor-por-nome")
    public String deleteByNome(@ModelAttribute SetorDto setorDto, Model model) {
        ApiResponse<SetorDto> setorExcluido = this.setorService.deleteByNome(setorDto.getTipoSetor());
        model.addAttribute("statusChamado", setorExcluido.getStatus());
        model.addAttribute("mensagem", setorExcluido.getMessage());
        return "redirect:/tela-setores";
    }

}
