package com.example.salesianos.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proyecto")
    @Builder.Default
    private List<Tarea> tareas = new ArrayList<>();

    public void addTarea(Tarea tarea){
        tareas.add(tarea);
        tarea.setProyecto(this);
    }

    public void removeTarea(Tarea tarea){
        tareas.remove(tarea);
        tarea.setProyecto(null);
    }
}
