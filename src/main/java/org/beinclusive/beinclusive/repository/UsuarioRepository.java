package org.beinclusive.beinclusive.repository;

import java.util.Optional;

import org.beinclusive.beinclusive.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);

}

