package com.example.Quiz2Linea3.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EstadisticasJugador")
@Data
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Integer idEstadistica;

    @Column(name = "minutos_jugados")
    private Integer minutosJugados;

    @Column(name = "goles")
    private Integer goles;

    @Column(name = "asistencias")
    private Integer asistencias;

    @Column(name = "tarjetas_amarillas")
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas")
    private Integer tarjetasRojas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    @JsonBackReference(value = "jugador-estadistica")
    private Jugador jugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido")
    @JsonBackReference(value = "partido-estadistica")
    private Partido partido;
}
