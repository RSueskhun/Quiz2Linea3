package com.example.Quiz2Linea3.Repository;

import com.example.Quiz2Linea3.Model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Integer> {

    // CONSULTA NATIVA 1: Buscar estadísticas por jugador
    @Query(value = "SELECT * FROM estadisticas_jugador WHERE id_jugador = ?1", nativeQuery = true)
    List<EstadisticasJugador> buscarPorJugador(Integer idJugador);

    // CONSULTA NATIVA 2: Calcular promedio de goles por jugador
    @Query(value = "SELECT AVG(goles) FROM estadisticas_jugador WHERE id_jugador = ?1", nativeQuery = true)
    Double promedioGolesPorJugador(Integer idJugador);
}
