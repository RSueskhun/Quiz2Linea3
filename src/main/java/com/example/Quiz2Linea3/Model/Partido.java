package com.example.Quiz2Linea3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Partido")
@Data
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Integer idPartido;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estadio", length = 100)
    private String estadio;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visita")
    private Integer golesVisita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_local", referencedColumnName = "id_equipo")
    @JsonBackReference(value = "equipo-local")
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_visita", referencedColumnName = "id_equipo")
    @JsonBackReference(value = "equipo-visita")
    private Equipo equipoVisita;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "partido-estadistica")
    private List<EstadisticasJugador> estadisticas;
}
