package com.example.Quiz2Linea3.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Entrenador")
@Data
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador")
    private Integer idEntrenador;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @JsonBackReference(value = "equipo-entrenador")
    private Equipo equipo;
}
