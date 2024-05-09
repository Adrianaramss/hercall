package com.soulcode.hercall.models;
import com.soulcode.hercall.enumerator.TipoSetor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_setor;

    @Enumerated(EnumType.STRING)
    private TipoSetor tipoSetor;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    private List<Chamado> chamadoList = new ArrayList<>();

}
