package com.example.Quiz2Linea3.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Jugador")
@Data
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Integer idJugador;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "posicion", length = 50)
    private String posicion;

    @Column(name = "dorsal")
    private Integer dorsal;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @JsonBackReference(value = "equipo-jugador")
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "jugador-estadistica")
    private List<EstadisticasJugador> estadisticas;
}
