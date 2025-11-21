package com.tuempresa.tuapp.service;

import com.tuempresa.tuapp.model.Comentario;
import com.tuempresa.tuapp.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> listarComentariosDesdeFecha(Date fecha) {
        return comentarioRepository.findByFechaAfter(fecha);
    }
}
