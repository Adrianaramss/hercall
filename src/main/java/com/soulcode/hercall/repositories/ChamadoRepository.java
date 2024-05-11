package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.id =:id AND c.responsavel.id IS NOT NULL")
    boolean existChamadoComReponsavel(Long id);

}
