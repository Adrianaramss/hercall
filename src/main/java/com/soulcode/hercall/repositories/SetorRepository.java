package com.soulcode.hercall.repositories;

import com.soulcode.hercall.enumerator.TipoSetor;
import com.soulcode.hercall.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    @Query("SELECT COUNT(s) > 0 FROM Setor s WHERE s.tipoSetor = :tipoSetor")
    boolean existByTipoSetor(TipoSetor tipoSetor);

    @Query("SELECT COUNT(s) > 0 FROM Setor s WHERE s.tipoSetor = :tipoSetor AND s.id <> :id")
    boolean existByTipoSetorAndNotId(TipoSetor tipoSetor, Long id);

    @Query("SELECT COUNT(c) > 0 FROM Chamado c WHERE c.setor.id_setor = :idSetor")
    boolean existChamadoByIdSetor(Long idSetor);

    @Query("SELECT s FROM Setor s WHERE s.tipoSetor = :tipoSetor")
    Optional<Setor> findSetorByTipoSetor(TipoSetor tipoSetor);
}
