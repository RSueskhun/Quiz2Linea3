package com.example.Quiz2Linea3.Repository;

import com.example.Quiz2Linea3.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    // CONSULTA NATIVA 1: Buscar jugadores por posición
    @Query(value = "SELECT * FROM Jugador WHERE posicion = ?1", nativeQuery = true)
    List<Jugador> buscarPorPosicion(String posicion);

    // CONSULTA NATIVA 2: Contar jugadores por equipo
    @Query(value = "SELECT COUNT(*) FROM Jugador WHERE id_equipo = ?1", nativeQuery = true)
    Integer contarPorEquipo(Integer idEquipo);
}
