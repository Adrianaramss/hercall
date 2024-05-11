package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.StatusDto;
import com.soulcode.hercall.models.Status;
import com.soulcode.hercall.repositories.StatusRepository;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public ApiResponse<StatusDto> save(StatusDto dto) {
        try {
            if (statusRepository.existByNome(dto.getNome())) {
                return new ApiResponse<>(409, "Já existe outro nome com essa descrição!", null);
            }

            Status status = StatusDto.convert(dto);
            status = this.statusRepository.save(status);

            return new ApiResponse<>(201, "Status cadastrado com sucesso!", new StatusDto(status));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<StatusDto>> findAll() {
        try {
            List<Status> statusList = this.statusRepository.findAll();
            return new ApiResponse<>(200, "Listagem de setores realizada com sucesso!",
                    statusList.stream().map(StatusDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<StatusDto> findById(Long id) {
        try {
            Optional<Status> resultado = this.statusRepository.findById(id);

            return resultado.map(status -> new ApiResponse<>(200, "Detalhamento de status realizado com sucesso!",
                    new StatusDto(status))).orElseGet(() -> new ApiResponse<>(204, "Status não encontrado!", null));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<StatusDto> updateById(Long id, StatusDto dto) {
        try {
            ApiResponse<StatusDto> existeStatus = this.findById(id);

            if (existeStatus.getStatus() != 200) {
                return new ApiResponse<>(404, "Não é possível editar, pois status não foi encontrado por ID!", null);
            }

            Status status = StatusDto.convert(dto);
            status.setId_status(id);

            if (statusRepository.existByNomeAndNotId(dto.getNome(), id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois já existe outro status com essa descrição!", null);
            }

            if (statusRepository.existChamadoByIdStatus(id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois esse status está relacionado a um chamado!", null);
            }

            status = this.statusRepository.save(status);

            return new ApiResponse<>(200, "Status editado com sucesso!", new StatusDto(status));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<StatusDto> deleteById(Long id) {
        try {
            ApiResponse<StatusDto> existeStatus = this.findById(id);

            if (existeStatus.getStatus() != 200) {
                return new ApiResponse<>(404, "Não foi possível excluir, pois status não foi encontrado por ID!", null);
            }

            if (statusRepository.existChamadoByIdStatus(id)) {
                return new ApiResponse<>(409, "Não é possível excluir, pois esse status está relacionado a um chamado!", null);
            }

            this.statusRepository.deleteById(id);

            return new ApiResponse<>(200, "Status excluído com sucesso!", existeStatus.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
