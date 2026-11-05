package com.example.salesianos.demo.servicios;

import com.example.salesianos.demo.repositorios.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public long contar() {

        return comentarioRepository.count();
    }
}
