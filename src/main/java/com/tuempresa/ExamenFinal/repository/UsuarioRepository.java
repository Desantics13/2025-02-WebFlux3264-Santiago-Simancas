package com.tuempresa.tuapp.repository;

import com.tuempresa.tuapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    java.util.Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}
