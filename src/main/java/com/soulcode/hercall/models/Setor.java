package com.soulcode.hercall.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_setor;

    @Column(nullable = false, unique = true)
    private String tipoSetor;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    private List<Chamado> chamadoList = new ArrayList<>();

}
