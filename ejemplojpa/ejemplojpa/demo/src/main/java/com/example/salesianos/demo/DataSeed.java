package com.example.salesianos.demo;

import com.example.salesianos.demo.model.Comentario;
import com.example.salesianos.demo.model.Etiqueta;
import com.example.salesianos.demo.model.Proyecto;
import com.example.salesianos.demo.model.Tarea;
import com.example.salesianos.demo.servicios.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//IA
@Component
@RequiredArgsConstructor
public class DataSeed {

    private final ProyectoService proyectoService;
    private final TareaService tareaService;
    private final EtiquetaService etiquetaService;
    private final ComentarioService comentarioService;

    @PostConstruct
    public void init() {

        // Crear 2 proyectos
        Proyecto proyecto1 = Proyecto.builder()
                .nombre("Proyecto Web")
                .descripcion("Desarrollo de una aplicación web")
                .build();

        Proyecto proyecto2 = Proyecto.builder()
                .nombre("Proyecto Móvil")
                .descripcion("Desarrollo de una app móvil")
                .build();

        // Crear 3 tareas
        Tarea tarea1 = Tarea.builder()
                .titulo("Diseñar base de datos")
                .estado("EN_PROGRESO")
                .build();

        Tarea tarea2 = Tarea.builder()
                .titulo("Implementar API REST")
                .estado("PENDIENTE")
                .build();

        Tarea tarea3 = Tarea.builder()
                .titulo("Crear interfaz de usuario")
                .estado("PENDIENTE")
                .build();

        // Asignar tareas a proyectos
        proyecto1.addTarea(tarea1);
        proyecto1.addTarea(tarea2);
        proyecto2.addTarea(tarea3);

        // Crear 3 comentarios (al menos 2 en la misma tarea)
        // Se añaden a la tarea con addComentario -> se persisten en cascada al guardar la tarea
        Comentario comentario1 = Comentario.builder()
                .texto("Hay que revisar las relaciones entre tablas")
                .autor("Mario")
                .build();

        Comentario comentario2 = Comentario.builder()
                .texto("Añadida la tabla de etiquetas")
                .autor("Ana")
                .build();

        Comentario comentario3 = Comentario.builder()
                .texto("Falta definir los endpoints")
                .autor("Mario")
                .build();

        // 2 comentarios en tarea1, 1 comentario en tarea2
        tarea1.addComentario(comentario1);
        tarea1.addComentario(comentario2);
        tarea2.addComentario(comentario3);

        // Crear 2 etiquetas (al menos una compartida por dos tareas)
        // Con la relación @OneToMany actual, se crean instancias separadas con el mismo nombre
        Etiqueta etiquetaUrgente1 = Etiqueta.builder()
                .nombre("URGENTE")
                .build();

        Etiqueta etiquetaUrgente2 = Etiqueta.builder()
                .nombre("URGENTE")
                .build();

        Etiqueta etiquetaBackend = Etiqueta.builder()
                .nombre("BACKEND")
                .build();

        // "URGENTE" compartida por tarea1 y tarea2 (misma etiqueta en dos tareas)
        tarea1.addEtiqueta(etiquetaUrgente1);
        tarea2.addEtiqueta(etiquetaUrgente2);
        // "BACKEND" solo en tarea2
        tarea2.addEtiqueta(etiquetaBackend);

        // Guardar proyectos (persiste en cascada: proyecto -> tareas -> comentarios y etiquetas)
        proyectoService.guardar(proyecto1);
        proyectoService.guardar(proyecto2);

        // Mostrar detalle de las tareas guardadas
        System.out.println("===== DATOS INICIALES CARGADOS =====");
        System.out.println("Proyectos: " + proyectoService.listarTodos().size());
        System.out.println("Tareas: " + tareaService.listarTodos().size());
        System.out.println("Comentarios: " + comentarioService.contar());
        System.out.println("Etiquetas: " + etiquetaService.contar());
        System.out.println("====================================");
    }
}
