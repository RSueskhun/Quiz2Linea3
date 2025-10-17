package com.example.Quiz2Linea3.Repository;

import com.example.Quiz2Linea3.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {

    @Query(value = "SELECT * FROM Entrenador WHERE especialidad = :especialidad", nativeQuery = true)
    List<Entrenador> buscarPorEspecialidad(@Param("especialidad") String especialidad);

    @Query(value = "SELECT COUNT(*) FROM Entrenador", nativeQuery = true)
    Integer contarEntrenadores();

    @Query(value = "SELECT COUNT(*) FROM Entrenador WHERE id_equipo = :idEquipo", nativeQuery = true)
    Integer contarPorEquipo(@Param("idEquipo") Integer idEquipo);
}
