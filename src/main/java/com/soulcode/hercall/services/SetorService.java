package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.models.Setor;
import com.soulcode.hercall.repositories.SetorRepository;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    SetorRepository setorRepository;

    public ApiResponse<SetorDto> save(SetorDto dto) {
        try {
            if (setorRepository.existByTipoSetor(dto.getTipoSetor())) {
                return new ApiResponse<>(409, "Já existe outro setor com essa descrição!", null);
            }

            Setor setor = SetorDto.convert(dto);
            setor = this.setorRepository.save(setor);

            return new ApiResponse<>(201, "Setor cadastrado com sucesso!", new SetorDto(setor));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<SetorDto>> findAll() {
        try {
            List<Setor> setores = this.setorRepository.findAll();
            return new ApiResponse<>(200, "Listagem de setores realizada com sucesso!",
                    setores.stream().map(SetorDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SetorDto> findById(Long id) {
        try {
            Optional<Setor> resultado = this.setorRepository.findById(id);

            return resultado.map(setor -> new ApiResponse<>(200, "Detalhamento de setor realizado com sucesso!",
                    new SetorDto(setor))).orElseGet(() -> new ApiResponse<>(204, "Setor não encontrado!", null));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SetorDto> updateById(Long id, SetorDto dto) {
        try {
            ApiResponse<SetorDto> existeSetor = this.findById(id);

            if (existeSetor.getStatus() != 200) {
                return new ApiResponse<>(404, "Não é possível editar, pois setor não foi encontrado por ID!", null);
            }

            Setor setor = SetorDto.convert(dto);
            setor.setId_setor(id);

            if (setorRepository.existByTipoSetorAndNotId(dto.getTipoSetor(), id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois já existe outro setor com essa descrição!", null);
            }

            if (setorRepository.existChamadoByIdSetor(id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois esse setor está relacionado a um chamado!", null);
            }

            setor = this.setorRepository.save(setor);

            return new ApiResponse<>(200, "Setor editado com sucesso!", new SetorDto(setor));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SetorDto> deleteById(Long id) {
        try {
            ApiResponse<SetorDto> existeSetor = this.findById(id);

            if (existeSetor.getStatus() != 200) {
                return new ApiResponse<>(404, "Não foi possível excluir, pois setor não foi encontrado por ID!", null);
            }

            if (setorRepository.existChamadoByIdSetor(id)) {
                return new ApiResponse<>(409, "Não é possível excluir, pois esse setor está relacionado a um chamado!", null);
            }

            this.setorRepository.deleteById(id);

            return new ApiResponse<>(200, "Setor excluído com sucesso!", existeSetor.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

}
