package com.soulcode.hercall.controllers;

import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.services.PrioridadeService;
import com.soulcode.hercall.services.SetorService;
import com.soulcode.hercall.services.UsuarioService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ChamadoService chamadoService;

    @Autowired
    SetorService setorService;

    @Autowired
    PrioridadeService prioridadeService;

    @GetMapping("/tela-admin")
    public String telaAdmin(Model model) {
        List<Object[]> numeroChamadoPorMes = chamadoService.countChamadosByMes().getData();

        List<Integer> data = new ArrayList<>();
        for (Object[] row : numeroChamadoPorMes) {
            for (Object value : row) {
                data.add(((BigDecimal) value).intValue());
            }
        }
        List<String> labels = Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho");
        model.addAttribute("data", data);
        model.addAttribute("labels", labels);

        List<Object[]> numeroChamadoPorStatus = chamadoService.countChamadosByStatus().getData();

        List<Integer> dataStatus = new ArrayList<>();
        for (Object[] row : numeroChamadoPorStatus) {
            for (Object value : row) {
                dataStatus.add(((BigDecimal) value).intValue());
            }
        }

        model.addAttribute("chamadosAbertos", dataStatus.get(0));
        model.addAttribute("chamadosEmAtendimento", dataStatus.get(1));
        model.addAttribute("chamadosFinalizados", dataStatus.get(2));

        return "tela-admin";
    }

    @GetMapping("/tela-usuarios")
    public String telaUsuarios(Model model) {
        List<UsuarioDto> listarUsuario = usuarioService.findAll().getData();
        model.addAttribute("usuarios", listarUsuario);
        return "tela-usuarios";
    }

    @GetMapping("/tela-prioridades")
    public String telaPriodades(Model model) {
        List<PrioridadeDto> listarPrioridade = prioridadeService.findAll().getData();
        model.addAttribute("prioridades", listarPrioridade);
        return "tela-prioridades";
    }

    @GetMapping("/tela-setores")
    public String telaSetores(Model model) {
        List<SetorDto> listarSetor = setorService.findAll().getData();
        model.addAttribute("setores", listarSetor);
        return "tela-setores";
    }

    @PostMapping("/cadastra-setor")
    @ResponseBody
    public ApiResponse<SetorDto> saveSetor(@RequestParam String setor) {
        return this.setorService.save(new SetorDto(setor));
    }

    @PostMapping("/exclui-setor")
    @ResponseBody
    public ApiResponse<SetorDto> deleteSetorByNome(@RequestParam("setor") String setor) {
        return this.setorService.deleteByNome(setor);
    }

    @PostMapping("/cadastra-prioridade")
    @ResponseBody
    public ApiResponse<PrioridadeDto> savePrioridade(@RequestParam("prioridade") String prioridade) {
        return this.prioridadeService.save(new PrioridadeDto(prioridade));
    }

    @PostMapping("/exclui-prioridade")
    @ResponseBody
    public ApiResponse<PrioridadeDto> deletePrioridadePorNome(@RequestParam("prioridade") String prioridade) {
        return this.prioridadeService.deleteByNome(prioridade);
    }

}
