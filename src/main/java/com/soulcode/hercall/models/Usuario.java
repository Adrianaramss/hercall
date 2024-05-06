package com.soulcode.hercall.models;

import com.soulcode.hercall.enumerator.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String nome;

    @Column (unique = true, nullable = false  )
    private String  email;

    @Column (nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;

    @Column
    private String matricula;

    public Usuario(Long idUsuario) {
    }
}
