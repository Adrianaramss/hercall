package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
