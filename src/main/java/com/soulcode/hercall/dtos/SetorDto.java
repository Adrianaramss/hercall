package com.soulcode.hercall.dtos;

import com.soulcode.hercall.models.Setor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetorDto {

    private Long id_setor;

    @NotBlank(message = "Descrição do setor é obrigatória")
    private String tipoSetor;

    public SetorDto() {
    }

    public SetorDto(Long id_setor) {
        this.id_setor = id_setor;
    }

    public SetorDto(Setor setor) {
        this.id_setor = setor.getId_setor();
        this.tipoSetor = setor.getTipoSetor();
    }

    public static Setor convert(SetorDto setorDto) {
        Setor setor = new Setor();
        setor.setId_setor(setorDto.getId_setor());
        setor.setTipoSetor(setorDto.getTipoSetor());
        return setor;
    }
}
