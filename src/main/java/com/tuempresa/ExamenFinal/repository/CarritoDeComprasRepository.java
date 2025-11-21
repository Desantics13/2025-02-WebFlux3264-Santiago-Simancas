package com.tuempresa.tuapp.repository;

import com.tuempresa.tuapp.model.CarritoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoDeComprasRepository extends JpaRepository<CarritoDeCompras, Integer> {
    List<CarritoDeCompras> findByUsuarioIdUsuario(Integer idUsuario);
}
