package com.tuempresa.tuapp.repository;

import com.tuempresa.tuapp.model.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {}
