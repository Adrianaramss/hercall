package com.soulcode.hercall.dtos;

import com.soulcode.hercall.models.Prioridade;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrioridadeDto {

    private Long id_prioridade;

    @NotBlank(message = "Descrição da prioridade é obrigatória")
    private String tipoPrioridade;

    public PrioridadeDto() {
    }

    public PrioridadeDto(Long id_prioridade) {
        this.id_prioridade = id_prioridade;
    }

    public PrioridadeDto(String tipoPrioridade) {
        this.tipoPrioridade = tipoPrioridade;
    }

    public PrioridadeDto(Prioridade prioridade) {
        this.id_prioridade = prioridade.getId_prioridade();
        this.tipoPrioridade = prioridade.getTipoPrioridade();
    }

    public static Prioridade convert(PrioridadeDto prioridadeDto) {
        Prioridade prioridade = new Prioridade();
        prioridade.setId_prioridade(prioridadeDto.getId_prioridade());
        prioridade.setTipoPrioridade(prioridadeDto.getTipoPrioridade());

        return prioridade;
    }
}
