package com.soulcode.hercall.repositories;

import com.soulcode.hercall.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
