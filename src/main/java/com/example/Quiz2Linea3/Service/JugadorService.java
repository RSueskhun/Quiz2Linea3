package com.example.Quiz2Linea3.Service;

import com.example.Quiz2Linea3.Model.Jugador;
import com.example.Quiz2Linea3.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    // POST - GUARDAR JUGADOR
    public ResponseEntity<?> guardar(Jugador jugador) {
        try {
            if (jugador.getNombre() == null || jugador.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("EL NOMBRE DEL JUGADOR ES OBLIGATORIO");
            }

            Jugador nuevo = jugadorRepository.save(jugador);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("JUGADOR CREADO CON ID: " + nuevo.getIdJugador());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("ERROR AL GUARDAR JUGADOR: " + e.getMessage());
        }
    }

    // GET - LISTAR JUGADORES
    public ResponseEntity<?> listar() {
        List<Jugador> lista = jugadorRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("NO EXISTEN JUGADORES REGISTRADOS");
        }
        return ResponseEntity.ok(lista);
    }

    // GET - BUSCAR POR ID
    public ResponseEntity<?> buscarPorId(Integer id) {
        Optional<Jugador> jugador = jugadorRepository.findById(id);
        if (jugador.isPresent()) {
            return ResponseEntity.ok(jugador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRÓ JUGADOR CON ID: " + id);
        }
    }

    // PUT - ACTUALIZAR JUGADOR
    public ResponseEntity<?> actualizar(Integer id, Jugador datos) {
        Optional<Jugador> existente = jugadorRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE JUGADOR CON ID: " + id);
        }

        Jugador jugador = existente.get();
        jugador.setNombre(datos.getNombre());
        jugador.setPosicion(datos.getPosicion());
        jugador.setDorsal(datos.getDorsal());
        jugador.setFechaNac(datos.getFechaNac());
        jugador.setNacionalidad(datos.getNacionalidad());
        jugador.setEquipo(datos.getEquipo());

        jugadorRepository.save(jugador);
        return ResponseEntity.ok("JUGADOR CON ID: " + id + " ACTUALIZADO CORRECTAMENTE");
    }

    // DELETE - ELIMINAR JUGADOR
    public ResponseEntity<?> eliminarPorId(Integer id) {
        if (!jugadorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE JUGADOR CON ID: " + id);
        }
        jugadorRepository.deleteById(id);
        return ResponseEntity.ok("JUGADOR CON ID: " + id + " ELIMINADO CORRECTAMENTE");
    }

    // CONSULTA NATIVA 1 - Buscar jugadores por posición
    public ResponseEntity<?> buscarPorPosicion(String posicion) {
        List<Jugador> jugadores = jugadorRepository.buscarPorPosicion(posicion);
        if (jugadores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRARON JUGADORES EN LA POSICIÓN: " + posicion);
        }
        return ResponseEntity.ok(jugadores);
    }

    // CONSULTA NATIVA 2 - Contar jugadores por equipo
    public ResponseEntity<?> contarPorEquipo(Integer idEquipo) {
        Integer total = jugadorRepository.contarPorEquipo(idEquipo);
        return ResponseEntity.ok("TOTAL DE JUGADORES EN EL EQUIPO " + idEquipo + ": " + total);
    }
}
