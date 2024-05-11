package com.soulcode.hercall.models;

import com.soulcode.hercall.enumerator.TipoPrioridade;
import com.soulcode.hercall.enumerator.TipoSetor;
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

    @Enumerated(EnumType.STRING)
    private TipoPrioridade tipoPrioridade;

    @OneToMany(mappedBy = "prioridade", cascade = CascadeType.ALL)
    private List<Chamado> chamadoList = new ArrayList<>();
}
