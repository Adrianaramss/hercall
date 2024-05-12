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

}
