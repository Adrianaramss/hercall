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

    @Column(unique = true, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Chamado> chamadoListResponsavel = new ArrayList<>();

    @OneToMany(mappedBy = "solicitante", cascade = CascadeType.ALL)
    private List<Chamado> chamadoListSolicitante = new ArrayList<>();

    public Usuario(Long idUsuario) {
    }

}
