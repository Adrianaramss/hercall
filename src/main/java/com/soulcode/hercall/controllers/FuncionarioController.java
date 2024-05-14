package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.models.Setor;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.SetorService;
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
public class FuncionarioController {

    @Autowired
    ChamadoService chamadoService;

    @Autowired
    SetorService setorService;

    @GetMapping("/tela-usuario")
    public String telaUsuario(Model model) {
        List<ChamadoDto> listarChamado = chamadoService.findAll().getData();

        List<Map<String, Object>> chamadosAbertos = listarChamado.stream()
                .map(chamado -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", chamado.getId());
                    map.put("descricao", chamado.getDescricao());
                    map.put("status", chamado.getStatus().getTipoStatus());
                    map.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
                    map.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
                    map.put("setor", chamado.getSetor());
                    map.put("solicitante", chamado.getSolicitante());
                    map.put("responsavel", chamado.getResponsavel());
                    map.put("prioridade", chamado.getPrioridade());
                    return map;
                })
                .toList();

        model.addAttribute("chamadosAbertos", chamadosAbertos);
        List<SetorDto> listarSetor = setorService.findAll().getData();
        model.addAttribute("setores", listarSetor);
        return "tela-usuario";
    }

    @PostMapping("/cadastra-chamado")
    @ResponseBody
    public ApiResponse<ChamadoDto> save(@RequestParam("setor") Long setor,
                                        @RequestParam("descricao") String descricao) {
        Setor existeSetor = SetorDto.convert(setorService.findById(setor).getData());
        return this.chamadoService.save(new ChamadoDto(existeSetor, descricao));
    }
}
