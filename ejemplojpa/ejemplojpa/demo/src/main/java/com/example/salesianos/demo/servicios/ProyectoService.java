package com.example.salesianos.demo.servicios;

import com.example.salesianos.demo.model.Proyecto;
import com.example.salesianos.demo.repositorios.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public Proyecto guardar(Proyecto proyecto) {

        return proyectoRepository.save(proyecto);
    }

    public List<Proyecto> listarTodos() {

        return proyectoRepository.findAll();
    }
}
