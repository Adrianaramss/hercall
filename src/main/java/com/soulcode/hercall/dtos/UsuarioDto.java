package com.soulcode.hercall.dtos;

import com.soulcode.hercall.enumerator.TipoUsuario;
import com.soulcode.hercall.models.Usuario;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A matrícula é obrigatóra")
    private String matricula;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    private String confirmaSenha;

    @NotBlank(message = "O tipo de usuário é obrigatório")
    private TipoUsuario tipoUsuario;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.matricula = usuario.getMatricula();
        this.tipoUsuario = usuario.getTipo_usuario();
    }

    public static Usuario convert(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getId());

        usuario.setEmail(usuarioDto.getEmail());
        usuario.setTipo_usuario(usuarioDto.getTipoUsuario());
        usuario.setNome(usuarioDto.getNome());
        usuario.setId(usuarioDto.getId());
        usuario.setMatricula(usuarioDto.getMatricula());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }

}