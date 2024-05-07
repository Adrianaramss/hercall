package com.soulcode.hercall.dtos;

import com.soulcode.hercall.enumerator.TipoUsuario;
import com.soulcode.hercall.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private String senha;
    private TipoUsuario tipoUsuario;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.matricula = usuario.getMatricula();
        this.tipoUsuario = usuario.getTipo_usuario();
    }

    public static Usuario convert(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario(usuarioDto.getId());

        usuario.setEmail(usuarioDto.getEmail());
        usuario.setTipo_usuario(usuarioDto.getTipoUsuario());
        usuario.setNome(usuarioDto.getNome());
        usuario.setId(usuarioDto.getId());
        usuario.setMatricula(usuarioDto.getMatricula());
        usuario.setSenha(usuarioDto.getSenha());
        return  usuario;
    }

}