package com.soulcode.hercall.dtos;

import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.models.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDto {

    private Long id_status;

    @NotBlank(message = "Descrição do status é obrigatória")
    private String nome;

    public StatusDto() {
    }

    public StatusDto(Long id_status) {
        this.id_status = id_status;
    }

    public StatusDto(Status status) {
        this.id_status = status.getId_status();
        this.nome = status.getNome();
    }

    public static Status convert(StatusDto statusDto) {
        Status status = new Status();
        status.setId_status(statusDto.getId_status());
        status.setNome(statusDto.getNome());

        return status;
    }
}
