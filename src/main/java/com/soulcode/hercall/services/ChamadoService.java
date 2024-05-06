package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.ChamadoDto;
import com.soulcode.hercall.models.Chamado;
import com.soulcode.hercall.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;
    public ChamadoDto save(ChamadoDto dto) {
        Chamado chamado = ChamadoDto.convert(dto);
        chamado = this.chamadoRepository.save(chamado);
        return  new ChamadoDto(chamado);
    }

    public List<ChamadoDto> findAll() {

        List<Chamado>chamado = this.chamadoRepository.findAll();
        return chamado.stream().map(ChamadoDto::new).collect(Collectors.toList());
    }

    public ChamadoDto findById(Long id) {

        Optional<Chamado> chamado = this.chamadoRepository.findById(id);
        if(chamado.isEmpty()){
            throw new RuntimeException("Cliente não encontrado");
        }else{
            return new ChamadoDto(chamado.get());
        }
    }

    public ChamadoDto updateById(Long id, ChamadoDto dto) {

        this.findById(id);
        Chamado chamado = ChamadoDto.convert(dto);
        chamado.setId(id);
        this.chamadoRepository.save(chamado);
        return new ChamadoDto(chamado);
    }

    public String deleteById(Long id) {

        ChamadoDto dto = findById(id);
        this.chamadoRepository.deleteById(id);
        return ( " O chamado " + dto.getId() + " foi excluído");
    }
}
