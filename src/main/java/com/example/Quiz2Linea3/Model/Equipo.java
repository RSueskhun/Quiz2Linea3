package com.example.Quiz2Linea3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Equipo")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer idEquipo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fundacion")
    private LocalDate fundacion;

    // Relación 1:N con Jugador
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "equipo-jugador")
    private List<Jugador> jugadores;

    // Relación 1:N con Entrenador
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "equipo-entrenador")
    private List<Entrenador> entrenadores;
}
