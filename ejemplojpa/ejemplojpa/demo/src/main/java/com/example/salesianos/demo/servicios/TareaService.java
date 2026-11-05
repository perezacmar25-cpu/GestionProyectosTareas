package com.example.salesianos.demo.servicios;

import com.example.salesianos.demo.model.Etiqueta;
import com.example.salesianos.demo.model.Tarea;
import com.example.salesianos.demo.repositorios.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public List<Tarea> listarTodos() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public void mostrarDetalle(Long id) {
        Optional<Tarea> opt = tareaRepository.findById(id);

        if (opt.isPresent()) {
            Tarea tarea = opt.get();

            System.out.println("Titulo: " + tarea.getTitulo());
            System.out.println("Comentarios: " + tarea.getComentarios().size());
            System.out.println("Etiquetas:");
            for (Etiqueta etiqueta : tarea.getEtiquetas()) {
                System.out.println(etiqueta.getNombre());
            }
        }
    }
}
