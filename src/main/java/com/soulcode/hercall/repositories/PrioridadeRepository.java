package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Prioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {

    @Query("SELECT COUNT(p) > 0 FROM Prioridade p WHERE p.tipoPrioridade = :tipoPrioridade")
    boolean existByTipoPrioridade(String tipoPrioridade);

    @Query("SELECT COUNT(p) > 0 FROM Prioridade p WHERE p.tipoPrioridade = :tipoPrioridade AND p.id_prioridade <> :id_prioridade")
    boolean existByTipoPrioridadeAndNotId(String tipoPrioridade, Long id_prioridade);

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.prioridade.id_prioridade = :id_prioridade")
    boolean existChamadoByIdPrioridade(Long id_prioridade);
}
