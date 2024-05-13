package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.models.Prioridade;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.PrioridadeService;
import com.soulcode.hercall.services.UsuarioService;
import com.soulcode.hercall.shared.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/edita-chamado/{id}")
    @ResponseBody
    public ApiResponse<ChamadoDto> edit(@PathVariable("id") Long id,
                     @RequestParam("status") TipoStatus status,
                     @RequestParam("prioridade") Long prioridade) {

        ChamadoDto dto = this.chamadoService.findById(id).getData();
        dto.setStatus(status);
        dto.setPrioridade(PrioridadeDto.convert(prioridadeService.findById(prioridade).getData()));

        return this.chamadoService.updateById(id, dto);
    }
}
