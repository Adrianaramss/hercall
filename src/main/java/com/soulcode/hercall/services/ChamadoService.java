package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.models.Chamado;
import com.soulcode.hercall.repositories.ChamadoRepository;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    public ApiResponse<ChamadoDto> save(ChamadoDto dto) {
        try {
            dto.setData_inicio(LocalDate.now());
            dto.setStatus(TipoStatus.AGUARDANDO_TECNICO);
            Chamado chamado = ChamadoDto.convert(dto);
            chamado = this.chamadoRepository.save(chamado);

            return new ApiResponse<>(201, "Chamado cadastrado com sucesso!", new ChamadoDto(chamado));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<ChamadoDto>> findAll() {
        try {
            List<Chamado> chamados = this.chamadoRepository.findAll();
            return new ApiResponse<>(200, "Listagem de chamados realizada com sucesso!",
                    chamados.stream().map(ChamadoDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<ChamadoDto> findById(Long id) {
        try {
            Optional<Chamado> resultado = this.chamadoRepository.findById(id);

            return resultado.map(chamado -> new ApiResponse<>(200, "Detalhamento de chamados realizado com sucesso!",
                    new ChamadoDto(chamado))).orElseGet(() -> new ApiResponse<>(204, "Chamado não encontrado!", null));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<ChamadoDto> updateById(Long id, ChamadoDto dto) {
        try {
            ApiResponse<ChamadoDto> existeChamado = this.findById(id);

            if (existeChamado.getStatus() != 200) {
                return new ApiResponse<>(404, "Não é possível editar, pois chamado não foi encontrado por ID!", null);
            }

            if (existeChamado.getData().getStatus().equals(TipoStatus.FINALIZADO)) {
                return new ApiResponse<>(400, "Não é possível editar, pois chamado já foi finalizado!", null);
            }

            if(dto.getStatus().equals(TipoStatus.FINALIZADO) || dto.getStatus().equals(TipoStatus.CANCELADO)) {
                dto.setData_termino(LocalDate.now());
            }

            Chamado chamado = ChamadoDto.convert(dto);
            chamado.setId(id);

            chamado = this.chamadoRepository.save(chamado);

            return new ApiResponse<>(200, "Chamado editado com sucesso!", new ChamadoDto(chamado));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<ChamadoDto> deleteById(Long id) {
        try {
            ApiResponse<ChamadoDto> existeChamado = this.findById(id);

            if (existeChamado.getStatus() != 200) {
                return new ApiResponse<>(404, "Não foi possível excluir, pois chamado não foi encontrado por ID!", null);
            }

            if (existeChamado.getData().getStatus().equals(TipoStatus.EM_ATENDIMENTO)) {
                return new ApiResponse<>(400, "Não é possível excluir, pois um técnico está atendendo esse chamado!", null);
            }

            this.chamadoRepository.deleteById(id);

            return new ApiResponse<>(200, "Chamado excluído com sucesso!", existeChamado.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<ChamadoDto>> findChamadosByPrioridade(String tipoPrioridade) {
        try {
            List<Chamado> chamados = this.chamadoRepository.findByTipoPrioridade(tipoPrioridade);
            return new ApiResponse<>(200, "Listagem de chamados por prioridade realizada com sucesso!",
                    chamados.stream().map(ChamadoDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<ChamadoDto>> findChamadosByStatus(TipoStatus status) {
        try {
            List<Chamado> chamados = this.chamadoRepository.findByStatus(status);
            return new ApiResponse<>(200, "Listagem de chamados por status realizada com sucesso!",
                    chamados.stream().map(ChamadoDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<ChamadoDto> updateChamado(Long id, ChamadoDto dto) {
        try {
            ApiResponse<ChamadoDto> chamadoExistente = findById(id);
            if (chamadoExistente.getStatus() != 200) {
                return new ApiResponse<>(404, "Chamado não encontrado!", null);
            }
            Chamado chamado = ChamadoDto.convert(dto);
            chamado.setId(id);
            chamado = this.chamadoRepository.save(chamado);
            return new ApiResponse<>(200, "Chamado atualizado com sucesso!", new ChamadoDto(chamado));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<Object[]>> countChamadosByMes() {
        try {
            List<Object[]> resultado = this.chamadoRepository.countChamadosByMes();

            return new ApiResponse<>(200, "Detalhamento de chamados por mês realizado com sucesso!", resultado);
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<Object[]>> countChamadosByStatus() {
        try {
            List<Object[]> resultado = this.chamadoRepository.countChamadosByStatus();

            return new ApiResponse<>(200, "Detalhamento de chamados por status realizado com sucesso!", resultado);
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

}
