package com.example.salesianos.demo.servicios;

import com.example.salesianos.demo.model.Etiqueta;
import com.example.salesianos.demo.repositorios.EtiquetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    public Etiqueta guardar(Etiqueta etiqueta) {

        return etiquetaRepository.save(etiqueta);
    }

    public long contar() {

        return etiquetaRepository.count();
    }
}
