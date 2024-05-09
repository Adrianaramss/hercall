package com.soulcode.hercall.repositories;

import com.soulcode.hercall.enumerator.TipoPrioridade;
import com.soulcode.hercall.models.Prioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Prioridade p WHERE p.tipoPrioridade = :tipoPrioridade")
    boolean existByTipoPrioridade(TipoPrioridade tipoPrioridade);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Prioridade p WHERE p.tipoPrioridade = :tipoPrioridade AND p.id <> :id")
    boolean existByTipoPrioridadeAndNotId(TipoPrioridade tipoPrioridade, Long id);

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.prioridade.id_prioridade = :idPrioridade")
    boolean existChamadoByIdPrioridade(Long idPrioridade);
}
