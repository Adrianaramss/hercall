package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.models.Prioridade;
import com.soulcode.hercall.repositories.PrioridadeRepository;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrioridadeService {

    @Autowired
    PrioridadeRepository prioridadeRepository;

    public ApiResponse<PrioridadeDto> save(PrioridadeDto dto) {
        try {
            if (prioridadeRepository.existByTipoPrioridade(dto.getTipoPrioridade())) {
                return new ApiResponse<>(409, "Já existe outra prioridade com essa descrição!", null);
            }

            Prioridade prioridade = PrioridadeDto.convert(dto);
            prioridade = this.prioridadeRepository.save(prioridade);

            return new ApiResponse<>(201, "Prioridade cadastrada com sucesso!", new PrioridadeDto(prioridade));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<PrioridadeDto>> findAll() {
        try {
            List<Prioridade> prioridades = this.prioridadeRepository.findAll();
            return new ApiResponse<>(200, "Listagem de prioridades realizada com sucesso!",
                    prioridades.stream().map(PrioridadeDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PrioridadeDto> findById(Long id) {
        try {
            Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);

            return resultado.map(prioridade -> new ApiResponse<>(200, "Detalhamento de prioridade realizado com sucesso!",
                    new PrioridadeDto(prioridade))).orElseGet(() -> new ApiResponse<>(204, "Prioridade não encontrada!", null));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PrioridadeDto> updateById(Long id, PrioridadeDto dto) {
        try {
            ApiResponse<PrioridadeDto> existePrioridade = this.findById(id);

            if (existePrioridade.getStatus() != 200) {
                return new ApiResponse<>(404, "Não é possível editar, pois prioridade não foi encontrada por ID!", null);
            }

            Prioridade prioridade = PrioridadeDto.convert(dto);
            prioridade.setId_prioridade(id);

            if (prioridadeRepository.existByTipoPrioridadeAndNotId(dto.getTipoPrioridade(), id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois já existe outra prioridade com essa descrição!", null);
            }

            if (prioridadeRepository.existChamadoByIdPrioridade(id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois essa prioridade está relacionada a um chamado!", null);
            }

            prioridade = this.prioridadeRepository.save(prioridade);

            return new ApiResponse<>(200, "Prioridade editada com sucesso!", new PrioridadeDto(prioridade));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PrioridadeDto> deleteById(Long id) {
        try {
            ApiResponse<PrioridadeDto> existePrioridade = this.findById(id);

            if (existePrioridade.getStatus() != 200) {
                return new ApiResponse<>(404, "Não foi possível excluir, pois prioridade não foi encontrada por ID!", null);
            }

            if (prioridadeRepository.existChamadoByIdPrioridade(id)) {
                return new ApiResponse<>(409, "Não é possível excluir, pois essa prioridade está relacionada a um chamado!", null);
            }

            this.prioridadeRepository.deleteById(id);

            return new ApiResponse<>(200, "Prioridade excluída com sucesso!", existePrioridade.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}