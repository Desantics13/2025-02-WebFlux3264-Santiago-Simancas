package com.tuempresa.tuapp.repository;

import com.tuempresa.tuapp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByStockLessThan(Integer stock);
}
