package com.soulcode.hercall.models;
import com.soulcode.hercall.enumerator.TipoPrioridade;
import com.soulcode.hercall.enumerator.TipoSetor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Prioridade {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prioridade;


    @Enumerated(EnumType.STRING)
    private TipoPrioridade tipoPrioridade;


}
