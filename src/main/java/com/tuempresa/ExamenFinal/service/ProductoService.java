package com.tuempresa.tuapp.service;

import com.tuempresa.tuapp.model.Producto;
import com.tuempresa.tuapp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductosPorStock(int stock) {
        return productoRepository.findByStockLessThan(stock);
    }
}
