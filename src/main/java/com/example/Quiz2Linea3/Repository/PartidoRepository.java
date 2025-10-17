package com.example.Quiz2Linea3.Repository;

import com.example.Quiz2Linea3.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // CONSULTA NATIVA 1: Buscar partidos por fecha
    @Query(value = "SELECT * FROM Partido WHERE fecha = ?1", nativeQuery = true)
    List<Partido> buscarPorFecha(LocalDate fecha);

    // CONSULTA NATIVA 2: Contar partidos jugados en un estadio
    @Query(value = "SELECT COUNT(*) FROM Partido WHERE estadio = ?1", nativeQuery = true)
    Integer contarPorEstadio(String estadio);
}
