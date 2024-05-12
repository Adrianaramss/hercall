package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.PrioridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TecnicoController {

    @Autowired
    ChamadoService chamadoService;

    @Autowired
    PrioridadeService prioridadeService;

    @GetMapping("/tela-tecnico")
    public String telaTecnico(Model model) {
        List<ChamadoDto> listarChamado = chamadoService.findAll().getData();
        model.addAttribute("chamados", listarChamado);

        List<PrioridadeDto> listarPrioridade = prioridadeService.findAll().getData();
        model.addAttribute("prioridades", listarPrioridade);

        List<TipoStatus> listarStatus = List.of(TipoStatus.values());
        model.addAttribute("statusList", listarStatus);

        return "tela-tecnico";
    }
}
