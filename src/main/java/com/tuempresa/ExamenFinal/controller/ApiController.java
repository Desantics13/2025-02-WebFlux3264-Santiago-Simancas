package com.tuempresa.tuapp.controller;

import com.tuempresa.tuapp.model.*;
import com.tuempresa.tuapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private CarritoService carritoService;

    // Registro usuario sin protección
    @PostMapping("/usuarios/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    // Listar productos por debajo de un stock "x"
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductosPorStock(@RequestParam int stock) {
        return ResponseEntity.ok(productoService.listarProductosPorStock(stock));
    }

    // Listar comentarios desde una fecha
    @GetMapping("/comentarios")
    public ResponseEntity<List<Comentario>> listarComentariosDesdeFecha(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ResponseEntity.ok(comentarioService.listarComentariosDesdeFecha(fecha));
    }

    // Crear carrito (requiere usuario autenticado - JWT)
    @PostMapping("/carritos")
    public ResponseEntity<?> crearCarrito(@RequestParam Integer idUsuario) {
        try {
            return ResponseEntity.ok(carritoService.crearCarrito(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Agregar productos al carrito (requiere autenticación)
    @PostMapping("/carritos/{idCarrito}/agregar")
    public ResponseEntity<?> agregarProducto(@PathVariable Integer idCarrito,
                                             @RequestParam Integer idProducto,
                                             @RequestParam Integer cantidad) {
        try {
            return ResponseEntity.ok(carritoService.agregarProductoACarrito(idCarrito, idProducto, cantidad));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar productos del carrito asegurando que sea del usuario autenticado
    @GetMapping("/carritos/{idCarrito}/productos")
    public ResponseEntity<?> listarProductosDelCarrito(@PathVariable Integer idCarrito,
                                                      @RequestParam Integer idUsuario) {
        try {
            return ResponseEntity.ok(carritoService.listarProductosDelCarrito(idCarrito, idUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
