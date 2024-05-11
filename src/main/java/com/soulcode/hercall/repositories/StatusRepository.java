package com.soulcode.hercall.repositories;

import com.soulcode.hercall.enumerator.TipoSetor;
import com.soulcode.hercall.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT COUNT(s) > 0 FROM Status s WHERE s.nome = :nome")
    boolean existByNome(String nome);

    @Query("SELECT COUNT(s) > 0 FROM Status s WHERE s.nome = :nome AND s.id <> :id")
    boolean existByNomeAndNotId(String nome, Long id);

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.status.id_status = :idStatus")
    boolean existChamadoByIdStatus(Long idStatus);
}
