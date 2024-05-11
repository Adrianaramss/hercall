package com.soulcode.hercall.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private Date data_inicio;

    @Column
    private  Date data_termino;

    @ManyToOne
    @JoinColumn (name = "id_status", nullable = false)
    private  Status status;

    @ManyToOne
    @JoinColumn (name = "id_setor", nullable = false)
    private Setor setor;


    @ManyToOne
    @JoinColumn(name = "id_solicitante", nullable = false)
    private Usuario solicitante;


    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "id_prioridade", nullable = false)
    private Prioridade prioridade;

    public Chamado() {
    }
    public Chamado(Long idChamado) {
    }

}
