package com.example.Quiz2Linea3.Service;

import com.example.Quiz2Linea3.Model.Partido;
import com.example.Quiz2Linea3.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    // POST - GUARDAR PARTIDO
    public ResponseEntity<?> guardar(Partido partido) {
        try {
            if (partido.getFecha() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("LA FECHA DEL PARTIDO ES OBLIGATORIA");
            }

            Partido nuevo = partidoRepository.save(partido);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("PARTIDO CREADO CON ID: " + nuevo.getIdPartido());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("ERROR AL GUARDAR PARTIDO: " + e.getMessage());
        }
    }

    // GET - LISTAR TODOS LOS PARTIDOS
    public ResponseEntity<?> listar() {
        List<Partido> lista = partidoRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("NO EXISTEN PARTIDOS REGISTRADOS");
        }
        return ResponseEntity.ok(lista);
    }

    // GET - BUSCAR POR ID
    public ResponseEntity<?> buscarPorId(Integer id) {
        Optional<Partido> partido = partidoRepository.findById(id);
        if (partido.isPresent()) {
            return ResponseEntity.ok(partido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRÓ PARTIDO CON ID: " + id);
        }
    }

    // PUT - ACTUALIZAR PARTIDO
    public ResponseEntity<?> actualizar(Integer id, Partido datos) {
        Optional<Partido> existente = partidoRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE PARTIDO CON ID: " + id);
        }

        Partido partido = existente.get();
        partido.setFecha(datos.getFecha());
        partido.setEstadio(datos.getEstadio());
        partido.setGolesLocal(datos.getGolesLocal());
        partido.setGolesVisita(datos.getGolesVisita());
        partido.setEquipoLocal(datos.getEquipoLocal());
        partido.setEquipoVisita(datos.getEquipoVisita());

        partidoRepository.save(partido);
        return ResponseEntity.ok("PARTIDO CON ID: " + id + " ACTUALIZADO CORRECTAMENTE");
    }

    // DELETE - ELIMINAR PARTIDO
    public ResponseEntity<?> eliminarPorId(Integer id) {
        if (!partidoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE PARTIDO CON ID: " + id);
        }
        partidoRepository.deleteById(id);
        return ResponseEntity.ok("PARTIDO CON ID: " + id + " ELIMINADO CORRECTAMENTE");
    }

    // CONSULTA NATIVA 1 - Buscar por fecha
    public ResponseEntity<?> buscarPorFecha(LocalDate fecha) {
        List<Partido> partidos = partidoRepository.buscarPorFecha(fecha);
        if (partidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRARON PARTIDOS EN LA FECHA: " + fecha);
        }
        return ResponseEntity.ok(partidos);
    }

    // CONSULTA NATIVA 2 - Contar por estadio
    public ResponseEntity<?> contarPorEstadio(String estadio) {
        Integer total = partidoRepository.contarPorEstadio(estadio);
        return ResponseEntity.ok("TOTAL DE PARTIDOS JUGADOS EN EL ESTADIO '" + estadio + "': " + total);
    }
}
