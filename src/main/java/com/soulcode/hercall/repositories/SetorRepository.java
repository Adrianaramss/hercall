package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    @Query("SELECT COUNT(s) > 0 FROM Setor s WHERE s.tipoSetor = :tipoSetor")
    boolean existByTipoSetor(String tipoSetor);

    @Query("SELECT COUNT(s) > 0 FROM Setor s WHERE s.tipoSetor = :tipoSetor AND s.id_setor <> :id_setor")
    boolean existByTipoSetorAndNotId(String tipoSetor, Long id_setor);

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.setor.id_setor = :id_setor")
    boolean existChamadoByIdSetor(Long id_setor);

    @Query("SELECT s FROM Setor s WHERE s.tipoSetor = :tipoSetor")
    Optional<Setor> findSetorByTipoSetor(String tipoSetor);
}
