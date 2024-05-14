package com.soulcode.hercall.restControllers;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.services.ChamadoService;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @PostMapping("/chamados")
    public ApiResponse<Map<String, Object>> save(@RequestBody ChamadoDto dto) {
        ApiResponse<ChamadoDto> chamadoResponse = this.chamadoService.save(dto);

        Map<String, Object> mappedChamado = new LinkedHashMap<>();

        ChamadoDto chamado = chamadoResponse.getData();

        mappedChamado.put("id", chamado.getId());
        mappedChamado.put("descricao", chamado.getDescricao());
        mappedChamado.put("status", chamado.getStatus() != null ? chamado.getStatus().getTipoStatus() : "N/A");
        mappedChamado.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("id_setor", chamado.getSetor() != null ? chamado.getSetor().getId_setor() : "N/A");
        mappedChamado.put("tipoSetor", chamado.getSetor() != null ? chamado.getSetor().getTipoSetor() : "N/A");
        mappedChamado.put("id_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getId() : "N/A");
        mappedChamado.put("nome_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getNome() : "N/A");
        mappedChamado.put("id_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getId() : "N/A");
        mappedChamado.put("nome_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getNome() : "N/A");
        mappedChamado.put("id_prioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getId_prioridade() : "N/A");
        mappedChamado.put("tipoPrioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getTipoPrioridade() : "N/A");

        return new ApiResponse<>(chamadoResponse.getStatus(), chamadoResponse.getMessage(), mappedChamado);
    }

    @GetMapping("/chamados")
    public ApiResponse<List<Map<String, Object>>> findAll() {
        ApiResponse<List<ChamadoDto>> resultado = this.chamadoService.findAll();
        List<Map<String, Object>> chamados = resultado.getData().stream().map(chamado -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", chamado.getId());
            map.put("descricao", chamado.getDescricao());
            map.put("status", chamado.getStatus() != null ? chamado.getStatus().getTipoStatus() : "N/A");
            map.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
            map.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
            map.put("id_setor", chamado.getSetor() != null ? chamado.getSetor().getId_setor() : "N/A");
            map.put("tipoSetor", chamado.getSetor() != null ? chamado.getSetor().getTipoSetor() : "N/A");
            map.put("id_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getId() : "N/A");
            map.put("nome_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getNome() : "N/A");
            map.put("id_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getId() : "N/A");
            map.put("nome_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getNome() : "N/A");
            map.put("id_prioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getId_prioridade() : "N/A");
            map.put("tipoPrioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getTipoPrioridade() : "N/A");
            return map;
        }).toList();

        return new ApiResponse<>(resultado.getStatus(), resultado.getMessage(), chamados);
    }

    @GetMapping("/chamados/{id}")
    public ApiResponse<Map<String, Object>> findById(@PathVariable Long id) {
        ApiResponse<ChamadoDto> chamadoResponse = this.chamadoService.findById(id);

        Map<String, Object> mappedChamado = new LinkedHashMap<>();

        ChamadoDto chamado = chamadoResponse.getData();

        mappedChamado.put("id", chamado.getId());
        mappedChamado.put("descricao", chamado.getDescricao());
        mappedChamado.put("status", chamado.getStatus() != null ? chamado.getStatus().getTipoStatus() : "N/A");
        mappedChamado.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("id_setor", chamado.getSetor() != null ? chamado.getSetor().getId_setor() : "N/A");
        mappedChamado.put("tipoSetor", chamado.getSetor() != null ? chamado.getSetor().getTipoSetor() : "N/A");
        mappedChamado.put("id_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getId() : "N/A");
        mappedChamado.put("nome_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getNome() : "N/A");
        mappedChamado.put("id_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getId() : "N/A");
        mappedChamado.put("nome_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getNome() : "N/A");
        mappedChamado.put("id_prioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getId_prioridade() : "N/A");
        mappedChamado.put("tipoPrioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getTipoPrioridade() : "N/A");

        return new ApiResponse<>(chamadoResponse.getStatus(), chamadoResponse.getMessage(), mappedChamado);
    }

    @PutMapping("/chamados/{id}")
    public ApiResponse<Map<String, Object>> updateById(@PathVariable Long id, @RequestBody ChamadoDto dto) {
        ApiResponse<ChamadoDto> chamadoResponse = this.chamadoService.updateById(id, dto);

        Map<String, Object> mappedChamado = new LinkedHashMap<>();

        ChamadoDto chamado = chamadoResponse.getData();

        mappedChamado.put("id", chamado.getId());
        mappedChamado.put("descricao", chamado.getDescricao());
        mappedChamado.put("status", chamado.getStatus() != null ? chamado.getStatus().getTipoStatus() : "N/A");
        mappedChamado.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("id_setor", chamado.getSetor() != null ? chamado.getSetor().getId_setor() : "N/A");
        mappedChamado.put("tipoSetor", chamado.getSetor() != null ? chamado.getSetor().getTipoSetor() : "N/A");
        mappedChamado.put("id_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getId() : "N/A");
        mappedChamado.put("nome_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getNome() : "N/A");
        mappedChamado.put("id_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getId() : "N/A");
        mappedChamado.put("nome_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getNome() : "N/A");
        mappedChamado.put("id_prioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getId_prioridade() : "N/A");
        mappedChamado.put("tipoPrioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getTipoPrioridade() : "N/A");

        return new ApiResponse<>(chamadoResponse.getStatus(), chamadoResponse.getMessage(), mappedChamado);
    }

    @DeleteMapping("/chamados/{id}")
    public ApiResponse<Map<String, Object>> deleteById(@PathVariable Long id) {
        ApiResponse<ChamadoDto> chamadoResponse = this.chamadoService.deleteById(id);

        Map<String, Object> mappedChamado = new LinkedHashMap<>();

        ChamadoDto chamado = chamadoResponse.getData();

        mappedChamado.put("id", chamado.getId());
        mappedChamado.put("descricao", chamado.getDescricao());
        mappedChamado.put("status", chamado.getStatus() != null ? chamado.getStatus().getTipoStatus() : "N/A");
        mappedChamado.put("data_inicio", chamado.getData_inicio() != null ? chamado.getData_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("data_termino", chamado.getData_termino() != null ? chamado.getData_termino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
        mappedChamado.put("id_setor", chamado.getSetor() != null ? chamado.getSetor().getId_setor() : "N/A");
        mappedChamado.put("tipoSetor", chamado.getSetor() != null ? chamado.getSetor().getTipoSetor() : "N/A");
        mappedChamado.put("id_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getId() : "N/A");
        mappedChamado.put("nome_solicitante", chamado.getSolicitante() != null ? chamado.getSolicitante().getNome() : "N/A");
        mappedChamado.put("id_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getId() : "N/A");
        mappedChamado.put("nome_responsavel", chamado.getResponsavel() != null ? chamado.getResponsavel().getNome() : "N/A");
        mappedChamado.put("id_prioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getId_prioridade() : "N/A");
        mappedChamado.put("tipoPrioridade", chamado.getPrioridade() != null ? chamado.getPrioridade().getTipoPrioridade() : "N/A");

        return new ApiResponse<>(chamadoResponse.getStatus(), chamadoResponse.getMessage(), mappedChamado);
    }

}
