package com.soulcode.hercall.repositories;

import com.soulcode.hercall.enumerator.TipoPrioridade;
import com.soulcode.hercall.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.id =:id AND c.responsavel.id IS NOT NULL")
    boolean existChamadoComReponsavel(Long id);

    @Query("SELECT c FROM Chamado c WHERE c.prioridade.tipoPrioridade = :prioridade")
    List<Chamado> findByPrioridade(TipoPrioridade prioridade);

    @Query("SELECT c FROM Chamado c WHERE c.status.nome = :status")
    List<Chamado> findByStatus(String status);

    @Query(value = "SELECT " +
            "SUM(CASE WHEN MONTH(data_inicio) = 1 THEN 1 ELSE 0 END) AS janeiro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 2 THEN 1 ELSE 0 END) AS fevereiro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 3 THEN 1 ELSE 0 END) AS marco, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 4 THEN 1 ELSE 0 END) AS abril, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 5 THEN 1 ELSE 0 END) AS maio, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 6 THEN 1 ELSE 0 END) AS junho, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 7 THEN 1 ELSE 0 END) AS julho, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 8 THEN 1 ELSE 0 END) AS agosto, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 9 THEN 1 ELSE 0 END) AS setembro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 10 THEN 1 ELSE 0 END) AS outubro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 11 THEN 1 ELSE 0 END) AS novembro, " +
            "SUM(CASE WHEN MONTH(data_inicio) = 12 THEN 1 ELSE 0 END) AS dezembro " +
            "FROM chamado " +
            "WHERE YEAR(data_inicio) = YEAR(CURRENT_DATE())",
            nativeQuery = true)
    List<Object[]> contarChamadosPorMes();

    @Query(value = "SELECT " +
            "SUM(CASE WHEN id_status <> 5 AND id_status <> 4 THEN 1 ELSE 0 END) AS chamadosAbertos, " +
            "SUM(CASE WHEN id_status = 2 THEN 1 ELSE 0 END) AS chamadosEmAtendimento, " +
            "SUM(CASE WHEN id_status = 5 THEN 1 ELSE 0 END) AS chamadosFinalizados " +
            "FROM chamado", nativeQuery = true
    )
    List<Object[]> contarChamadosPorStatus();

}
