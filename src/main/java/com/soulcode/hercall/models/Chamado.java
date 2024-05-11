package com.soulcode.hercall.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column
    private LocalDate data_inicio;

    @Column
    private  LocalDate data_termino;

    @ManyToOne
    @JoinColumn (name = "id_status", nullable = false)
    private  Status status;

    @ManyToOne
    @JoinColumn (name = "id_setor", nullable = false)
    private Setor setor;


    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Usuario solicitante;


    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "id_prioridade")
    private Prioridade prioridade;

    public Chamado() {
    }
    public Chamado(Long idChamado) {
    }

}
