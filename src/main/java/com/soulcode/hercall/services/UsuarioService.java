package com.soulcode.hercall.services;

import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.models.Usuario;
import com.soulcode.hercall.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioDto save(UsuarioDto dto){
        Usuario usuario = UsuarioDto.convert(dto);
        usuario = this.usuarioRepository.save(usuario);
        return  new UsuarioDto(usuario);
    }

    public List<UsuarioDto> findAll() {
        List<Usuario>usuario = this.usuarioRepository.findAll();
        return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    public UsuarioDto findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new RuntimeException("Cliente não encontrado");
        }else{
            return new UsuarioDto(usuario.get());
        }
    }

    public UsuarioDto updateById(Long id, UsuarioDto dto) {
        this.findById(id);
        Usuario usuario = UsuarioDto.convert(dto);
        usuario.setId(id);
        this.usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public String deleteById(Long id) {
        UsuarioDto dto = findById(id);
        this.usuarioRepository.deleteById(id);
        return ( " O cliente de número " + dto.getId() + " foi excluído");

    }
}
