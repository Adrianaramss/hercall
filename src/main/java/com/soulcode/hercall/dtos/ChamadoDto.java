package com.soulcode.hercall.dtos;
import com.soulcode.hercall.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter


public class ChamadoDto {
    private Long id;
    private String descricao;
    private Date data_inicio;
    private Date data_termino;
    private Status status;
    private Setor setor;
    private Usuario solicitante;
    private Usuario responsavel;
    private Prioridade prioridade;

    //construtor vazio
    public ChamadoDto(){

    }
    //costrutor do id
    public ChamadoDto(Long id){

    }

    public ChamadoDto(Chamado chamado) {
        this.id = chamado.getId();
        this.descricao = chamado.getDescricao();
        this.data_inicio = chamado.getData_inicio();
        this.data_termino = chamado.getData_termino();
        this.status = chamado.getStatus();
        this.setor = chamado.getSetor();
        this.solicitante = chamado.getSolicitante();
        this.responsavel = chamado.getResponsavel();
        this.prioridade = chamado.getPrioridade();
    }

    public static Chamado convert(ChamadoDto chamadoDto){
        Chamado chamado = new Chamado(chamadoDto.id);

        chamado.setDescricao(chamadoDto.getDescricao());
        chamado.setData_inicio(chamadoDto.getData_inicio());
        chamado.setData_termino(chamadoDto.getData_termino());
        chamado.setId(chamadoDto.getId());
        chamado.setSetor(chamadoDto.getSetor());
        chamado.setStatus(chamadoDto.getStatus());
        chamado.setSolicitante(chamadoDto.getSolicitante());
        chamado.setPrioridade(chamadoDto.getPrioridade());
        chamado.setResponsavel(chamadoDto.getResponsavel());

        return  chamado;
    }



}