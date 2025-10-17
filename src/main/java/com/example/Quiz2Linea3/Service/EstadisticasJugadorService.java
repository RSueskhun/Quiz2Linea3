package com.example.Quiz2Linea3.Service;

import com.example.Quiz2Linea3.Model.EstadisticasJugador;
import com.example.Quiz2Linea3.Repository.EstadisticasJugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EstadisticasJugadorService {

    @Autowired
    private EstadisticasJugadorRepository estadisticasJugadorRepository;

    // POST - GUARDAR ESTADÍSTICAS
    public ResponseEntity<?> guardar(EstadisticasJugador estadistica) {
        try {
            if (estadistica.getMinutosJugados() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("LOS MINUTOS JUGADOS SON OBLIGATORIOS");
            }

            EstadisticasJugador nueva = estadisticasJugadorRepository.save(estadistica);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("ESTADÍSTICA REGISTRADA CON ID: " + nueva.getIdEstadistica());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("ERROR AL GUARDAR ESTADÍSTICA: " + e.getMessage());
        }
    }

    // GET - LISTAR TODAS
    public ResponseEntity<?> listar() {
        List<EstadisticasJugador> lista = estadisticasJugadorRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("NO EXISTEN ESTADÍSTICAS REGISTRADAS");
        }
        return ResponseEntity.ok(lista);
    }

    // GET - BUSCAR POR ID
    public ResponseEntity<?> buscarPorId(Integer id) {
        Optional<EstadisticasJugador> estadistica = estadisticasJugadorRepository.findById(id);
        if (estadistica.isPresent()) {
            return ResponseEntity.ok(estadistica.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRÓ ESTADÍSTICA CON ID: " + id);
        }
    }

    // PUT - ACTUALIZAR
    public ResponseEntity<?> actualizar(Integer id, EstadisticasJugador datos) {
        Optional<EstadisticasJugador> existente = estadisticasJugadorRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE ESTADÍSTICA CON ID: " + id);
        }

        EstadisticasJugador estadistica = existente.get();
        estadistica.setMinutosJugados(datos.getMinutosJugados());
        estadistica.setGoles(datos.getGoles());
        estadistica.setAsistencias(datos.getAsistencias());
        estadistica.setTarjetasAmarillas(datos.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(datos.getTarjetasRojas());
        estadistica.setJugador(datos.getJugador());
        estadistica.setPartido(datos.getPartido());

        estadisticasJugadorRepository.save(estadistica);
        return ResponseEntity.ok("ESTADÍSTICA CON ID: " + id + " ACTUALIZADA CORRECTAMENTE");
    }

    // DELETE - ELIMINAR
    public ResponseEntity<?> eliminarPorId(Integer id) {
        if (!estadisticasJugadorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE ESTADÍSTICA CON ID: " + id);
        }
        estadisticasJugadorRepository.deleteById(id);
        return ResponseEntity.ok("ESTADÍSTICA CON ID: " + id + " ELIMINADA CORRECTAMENTE");
    }

    // CONSULTA NATIVA 1 - Buscar estadísticas por jugador
    public ResponseEntity<?> buscarPorJugador(Integer idJugador) {
        List<EstadisticasJugador> lista = estadisticasJugadorRepository.buscarPorJugador(idJugador);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRARON ESTADÍSTICAS PARA EL JUGADOR CON ID: " + idJugador);
        }
        return ResponseEntity.ok(lista);
    }

    // CONSULTA NATIVA 2 - Promedio de goles por jugador
    public ResponseEntity<?> promedioGolesPorJugador(Integer idJugador) {
        Double promedio = estadisticasJugadorRepository.promedioGolesPorJugador(idJugador);
        if (promedio == null) promedio = 0.0;
        return ResponseEntity.ok("PROMEDIO DE GOLES DEL JUGADOR CON ID " + idJugador + ": " + promedio);
    }
}
