package com.soulcode.hercall.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Prioridade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prioridade;

    @Column(nullable = false, unique = true)
    private String tipoPrioridade;

    @OneToMany(mappedBy = "prioridade", cascade = CascadeType.ALL)
    private List<Chamado> chamadoList = new ArrayList<>();
}
