package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.enumerator.TipoPrioridade;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.shared.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/chamados")
    public String save(@ModelAttribute ChamadoDto dto, Model model){
        ApiResponse<ChamadoDto> chamadoCadastrado = this.chamadoService.save(dto);
        model.addAttribute("statusChamado", chamadoCadastrado.getStatus());
        model.addAttribute("mensagem", chamadoCadastrado.getMessage());
        return "redirect:/tela-usuario";
    }

        @GetMapping("/chamados")
    public ApiResponse<List<ChamadoDto>> findAll(){
        return this.chamadoService.findAll();
    }

    @GetMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @PatchMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> updateById(@PathVariable Long id, @ModelAttribute ChamadoDto dto){
        return this.chamadoService.updateById(id, dto);
    }

    @DeleteMapping("/chamados/{id}")
    public ApiResponse<ChamadoDto> deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

    @GetMapping("/chamados/por-prioridade")
    public ApiResponse<List<ChamadoDto>> findChamadosByPrioridade(@RequestParam TipoPrioridade prioridade) {
        return this.chamadoService.findChamadosByPrioridade(prioridade);
    }

    @GetMapping("/chamados/por-status")
    public ApiResponse<List<ChamadoDto>> findChamadosByStatus(@RequestParam String status) {
        return this.chamadoService.findChamadosByStatus(status);
    }

    //
//    @PutMapping("/chamados/{id}")
//    public ApiResponse<ChamadoDto> updateChamado(@PathVariable Long id, @RequestBody ChamadoDto dto) {
//        return this.chamadoService.updateChamado(id, dto);
//    }
}
