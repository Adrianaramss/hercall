package com.soulcode.hercall.repositories;

import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.id =:id AND c.responsavel.id IS NOT NULL")
    boolean existChamadoComReponsavel(Long id);

    @Query("SELECT c FROM Chamado c WHERE c.prioridade.tipoPrioridade = :tipoPrioridade")
    List<Chamado> findByTipoPrioridade(String tipoPrioridade);

    @Query("SELECT c FROM Chamado c WHERE c.status = :status")
    List<Chamado> findByStatus(TipoStatus status);

    @Query(value = "SELECT " +
            "SUM(CASE WHEN MONTH(data_inicio) = 1 THEN 1 ELSE 0 END) AS janeiro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 2 THEN 1 ELSE 0 END) AS fevereiro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 3 THEN 1 ELSE 0 END) AS marco, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 4 THEN 1 ELSE 0 END) AS abril, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 5 THEN 1 ELSE 0 END) AS maio, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 6 THEN 1 ELSE 0 END) AS junho " +
            "FROM chamado " +
            "WHERE YEAR(data_inicio) = YEAR(CURRENT_DATE())",
            nativeQuery = true)
    List<Object[]> countChamadosByMes();

    @Query(value = "SELECT " +
            "SUM(CASE WHEN status <> 'FINALIZADO' AND status <> 'CANCELADO' THEN 1 ELSE 0 END) AS chamadosAbertos, " +
            "SUM(CASE WHEN status = 'EM_ATENDIMENTO' THEN 1 ELSE 0 END) AS chamadosEmAtendimento, " +
            "SUM(CASE WHEN status = 'FINALIZADO' THEN 1 ELSE 0 END) AS chamadosFinalizados " +
            "FROM chamado", nativeQuery = true
    )
    List<Object[]> countChamadosByStatus();

}
