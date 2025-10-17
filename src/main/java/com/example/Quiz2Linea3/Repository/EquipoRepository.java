package com.example.Quiz2Linea3.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<com.example.Quiz2Linea3.Model.Equipo, Integer> {

    // CONSULTA NATIVA 1: Buscar equipos por ciudad
    @Query(value = "SELECT * FROM Equipo WHERE ciudad = :ciudad", nativeQuery = true)
    List<com.example.Quiz2Linea3.Model.Equipo> buscarPorCiudad(@Param("ciudad") String ciudad);

    // CONSULTA NATIVA 2: Contar equipos registrados
    @Query(value = "SELECT COUNT(*) FROM Equipo", nativeQuery = true)
    Integer contarEquipos();
}
