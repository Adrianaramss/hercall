package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.StatusDto;
import com.soulcode.hercall.models.Status;
import com.soulcode.hercall.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public StatusDto save(StatusDto statusDto) {
        Status status = StatusDto.convert(statusDto);
        status = this.statusRepository.save(status);

        return new StatusDto(status);
    }

    public List<StatusDto> findAll() {
        List<Status> statusList = this.statusRepository.findAll();
        return statusList.stream().map(StatusDto::new).collect(Collectors.toList());
    }

    public StatusDto findById(Long id) {
        Optional<Status> resultado = this.statusRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Status não encontrado");
        } else {
            return new StatusDto(resultado.get());
        }
    }

    public StatusDto updateById(Long id, StatusDto statusDto) {
        this.findById(id);
        Status status = StatusDto.convert(statusDto);
        status.setId_status(id);
        status = this.statusRepository.save(status);

        return new StatusDto(status);
    }

    public StatusDto deleteById(Long id) {
        StatusDto statusDto = findById(id);
        this.statusRepository.deleteById(id);

        return statusDto;
    }
}
