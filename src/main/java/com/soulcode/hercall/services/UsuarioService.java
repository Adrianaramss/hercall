package com.soulcode.hercall.services;


import com.soulcode.hercall.dtos.UsuarioDto;
import com.soulcode.hercall.models.Usuario;
import com.soulcode.hercall.repositories.UsuarioRepository;
import com.soulcode.hercall.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ApiResponse<UsuarioDto> save(UsuarioDto dto) {
        try {
            if(!Objects.equals(dto.getSenha(), dto.getConfirmaSenha())) {
                return new ApiResponse<>(400, "As senhas digitadas não coincidem. Por favor, verifique e tente novamente", null);
            }

            if (usuarioRepository.existByEmail(dto.getEmail())) {
                return new ApiResponse<>(409, "Já existe outro usuário com esse email!", null);
            }

            Usuario usuario = UsuarioDto.convert(dto);
            usuario = this.usuarioRepository.save(usuario);

            return new ApiResponse<>(201, "Usuário cadastrado com sucesso!", new UsuarioDto(usuario));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<UsuarioDto>> findAll() {
        try {
            List<Usuario> usuarios = this.usuarioRepository.findAll();
            return new ApiResponse<>(200, "Listagem de usuários realizada com sucesso!",
                    usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<UsuarioDto> findById(Long id) {
        try {
            Optional<Usuario> resultado = this.usuarioRepository.findById(id);

            return resultado.map(usuario -> new ApiResponse<>(200, "Detalhamento de usuário realizado com sucesso!",
                    new UsuarioDto(usuario))).orElseGet(() -> new ApiResponse<>(204, "Usuário não encontrado!", null));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<UsuarioDto> updateById(Long id, UsuarioDto dto) {
        try {
            ApiResponse<UsuarioDto> existeUsuario = this.findById(id);

            if (existeUsuario.getStatus() != 200) {
                return new ApiResponse<>(404, "Não é possível editar, pois usuário não foi encontrado por ID!", null);
            }

            Usuario usuario = UsuarioDto.convert(dto);
            usuario.setId(id);

            if (usuarioRepository.existByEmailAndNotId(dto.getEmail(), id)) {
                return new ApiResponse<>(409, "Não é possível editar, pois já existe outro usuário com esse email!", null);
            }

            usuario = this.usuarioRepository.save(usuario);

            return new ApiResponse<>(200, "Usuário editada com sucesso!", new UsuarioDto(usuario));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<UsuarioDto> deleteById(Long id) {
        try {
            ApiResponse<UsuarioDto> existeUsuario = this.findById(id);

            if (existeUsuario.getStatus() != 200) {
                return new ApiResponse<>(404, "Não foi possível excluir, pois usuário não foi encontrado por ID!", null);
            }

            if (usuarioRepository.existChamadoByIdUsuario(id)) {
                return new ApiResponse<>(409, "Não é possível excluir, pois esse usuário está relacionado a um chamado!", null);
            }

            this.usuarioRepository.deleteById(id);

            return new ApiResponse<>(200, "Usuário excluído com sucesso!", existeUsuario.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
