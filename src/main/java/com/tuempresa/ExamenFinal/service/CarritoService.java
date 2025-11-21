package com.tuempresa.tuapp.service;

import com.tuempresa.tuapp.model.*;
import com.tuempresa.tuapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoDeComprasRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public CarritoDeCompras crearCarrito(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        CarritoDeCompras carrito = new CarritoDeCompras();
        carrito.setUsuario(usuario);
        carrito.setProductos(List.of());
        carrito.setSubtotal(0.0);
        carrito.setImpuestos(0.0);
        return carritoRepository.save(carrito);
    }

    @Transactional
    public CarritoDeCompras agregarProductoACarrito(Integer idCarrito, Integer idProducto, Integer cantidad) throws Exception {
        CarritoDeCompras carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new Exception("Carrito no encontrado"));
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new Exception("Producto no encontrado"));

        if (producto.getStock() < cantidad) {
            throw new Exception("Stock insuficiente");
        }

        // Descontar stock
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        // Agregar productos al carrito y recalcular subtotal y impuestos
        carrito.getProductos().add(producto);
        double subtotal = carrito.getProductos().stream().mapToDouble(Producto::getPrecio).sum();
        carrito.setSubtotal(subtotal);
        carrito.setImpuestos(subtotal * 0.19); // Ejemplo impuesto 19%
        return carritoRepository.save(carrito);
    }

    public List<Producto> listarProductosDelCarrito(Integer idCarrito, Integer idUsuario) throws Exception {
        CarritoDeCompras carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new Exception("Carrito no encontrado"));
        if (!carrito.getUsuario().getIdUsuario().equals(idUsuario)) {
            throw new Exception("Acceso denegado al carrito");
        }
        return carrito.getProductos();
    }
}
