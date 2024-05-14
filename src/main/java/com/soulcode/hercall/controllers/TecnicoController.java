package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.PrioridadeService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TecnicoController {

    @Autowired
    ChamadoService chamadoService;

    @Autowired
    PrioridadeService prioridadeService;

    @GetMapping("/tela-tecnico")
    public String telaTecnico(Model model) {
        List<ChamadoDto> listarChamado = chamadoService.findAll().getData();
        List<Map<String, Object>> chamadosAbertos = listarChamado.stream()
                .map(chamado -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", chamado.getId());
                    map.put("descricao", chamado.getDescricao());
                    map.put("tipoStatus", chamado.getStatus().getTipoStatus());
                    map.put("status", chamado.getStatus());
                    map.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
                    map.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
                    map.put("setor", chamado.getSetor());
                    map.put("solicitante", chamado.getSolicitante());
                    map.put("responsavel", chamado.getResponsavel());
                    map.put("prioridade", chamado.getPrioridade());
                    return map;
                })
                .toList();
        model.addAttribute("chamados", chamadosAbertos);

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
