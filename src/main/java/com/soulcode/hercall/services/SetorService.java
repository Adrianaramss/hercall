package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.SetorDto;
import com.soulcode.hercall.models.Setor;
import com.soulcode.hercall.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    SetorRepository setorRepository;

    public SetorDto save(SetorDto setorDto) {
        Setor setor = SetorDto.convert(setorDto);
        setor = this.setorRepository.save(setor);

        return new SetorDto(setor);
    }

    public List<SetorDto> findAll() {
        List<Setor> setores = this.setorRepository.findAll();
        return setores.stream().map(SetorDto::new).collect(Collectors.toList());
    }

    public SetorDto findById(Long id) {
        Optional<Setor> resultado = this.setorRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Setor não encontrado");
        } else {
            return new SetorDto(resultado.get());
        }
    }

    public SetorDto updateById(Long id, SetorDto setorDto) {
        this.findById(id);
        Setor setor = SetorDto.convert(setorDto);
        setor.setId_setor(id);
        setor = this.setorRepository.save(setor);

        return new SetorDto(setor);
    }

    public SetorDto deleteById(Long id) {
        SetorDto setorDto = findById(id);
        this.setorRepository.deleteById(id);

        return setorDto;
    }
}
