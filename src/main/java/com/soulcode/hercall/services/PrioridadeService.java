package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.PrioridadeDto;
import com.soulcode.hercall.models.Prioridade;
import com.soulcode.hercall.repositories.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrioridadeService {

    @Autowired
    PrioridadeRepository prioridadeRepository;

    public PrioridadeDto save(PrioridadeDto prioridadeDto) {
        Prioridade prioridade = PrioridadeDto.convert(prioridadeDto);
        prioridade = this.prioridadeRepository.save(prioridade);

        return new PrioridadeDto(prioridade);
    }

    public List<PrioridadeDto> findAll(){
        List<Prioridade> prioridades = this.prioridadeRepository.findAll();

        return prioridades.stream().map(PrioridadeDto::new).collect(Collectors.toList());
    }

    public PrioridadeDto findById(Long id) {
        Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);

        if (resultado.isEmpty()) {
            throw new RuntimeException("Prioridade não encontrado");
        } else {
            return new PrioridadeDto(resultado.get());
        }
    }

    public PrioridadeDto updateById(Long id, PrioridadeDto prioridadeDto) {
        this.findById(id);
        Prioridade prioridade = PrioridadeDto.convert(prioridadeDto);
        prioridade.setId_prioridade(id);
        prioridade = this.prioridadeRepository.save(prioridade);

        return new PrioridadeDto(prioridade);
    }

    public PrioridadeDto deleteById(Long id) {
        PrioridadeDto prioridadeDto = findById(id);
        this.prioridadeRepository.deleteById(id);

        return prioridadeDto;
    }
}
