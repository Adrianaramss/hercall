package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.SetorService;
import com.soulcode.hercall.shared.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FuncionarioController {

    @Autowired
    ChamadoService chamadoService;

    @Autowired
    SetorService setorService;

    @GetMapping("/tela-usuario")
    public String telaUsuario(Model model) {
        List<ChamadoDto> listarChamado = chamadoService.findAll().getData();
        model.addAttribute("chamadosAbertos", listarChamado);
        List<SetorDto> listarSetor = setorService.findAll().getData();
        model.addAttribute("setores", listarSetor);
        return "tela-usuario";
    }

    @PostMapping("/cadastra-chamado")
    public String save(@ModelAttribute ChamadoDto dto, HttpSession session){
        ApiResponse<ChamadoDto> chamadoCadastrado = this.chamadoService.save(dto);
        session.setAttribute("statusChamado", chamadoCadastrado.getStatus());
        session.setAttribute("mensagem", chamadoCadastrado.getMessage());
        return "redirect:/tela-usuario";
    }
}
