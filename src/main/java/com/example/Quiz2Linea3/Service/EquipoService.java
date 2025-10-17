package com.example.Quiz2Linea3.Service;

import com.example.Quiz2Linea3.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    // POST - GUARDAR EQUIPO
    public ResponseEntity<?> guardar(com.example.Quiz2Linea3.Model.Equipo equipo) {
        try {
            if (equipo.getNombre() == null || equipo.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EL NOMBRE DEL EQUIPO ES OBLIGATORIO");
            }

            com.example.Quiz2Linea3.Model.Equipo nuevo = equipoRepository.save(equipo);
            return ResponseEntity.status(HttpStatus.CREATED).body("EQUIPO CREADO CON ID: " + nuevo.getIdEquipo());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR AL GUARDAR EQUIPO: " + e.getMessage());
        }
    }

    // GET - LISTAR EQUIPOS
    public ResponseEntity<?> listar() {
        List<com.example.Quiz2Linea3.Model.Equipo> equipos = equipoRepository.findAll();
        if (equipos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO EXISTEN EQUIPOS REGISTRADOS");
        }
        return ResponseEntity.ok(equipos);
    }

    // GET - BUSCAR EQUIPO POR ID
    public ResponseEntity<?> buscarPorId(Integer id) {
        try {
            Optional<com.example.Quiz2Linea3.Model.Equipo> equipo = equipoRepository.findById(id);

            if (equipo.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(equipo.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("NO SE ENCONTRÓ EQUIPO CON ID: " + id);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR AL BUSCAR EQUIPO CON ID: " + id + ". DETALLE: " + e.getMessage());
        }
    }

    // PUT - ACTUALIZAR EQUIPO
    public ResponseEntity<?> actualizar(Integer id, com.example.Quiz2Linea3.Model.Equipo equipoActualizado) {
        Optional<com.example.Quiz2Linea3.Model.Equipo> existente = equipoRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCONTRÓ EQUIPO CON ID: " + id);
        }

        com.example.Quiz2Linea3.Model.Equipo equipo = existente.get();
        equipo.setNombre(equipoActualizado.getNombre());
        equipo.setCiudad(equipoActualizado.getCiudad());
        equipo.setFundacion(equipoActualizado.getFundacion());

        equipoRepository.save(equipo);
        return ResponseEntity.ok("EQUIPO CON ID: " + id + " ACTUALIZADO CORRECTAMENTE");
    }

    // DELETE - ELIMINAR EQUIPO
    public ResponseEntity<?> eliminarPorId(Integer id) {
        if (!equipoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO EXISTE EQUIPO CON ID: " + id);
        }
        equipoRepository.deleteById(id);
        return ResponseEntity.ok("EQUIPO CON ID: " + id + " ELIMINADO CORRECTAMENTE");
    }

    // CONSULTA NATIVA - BUSCAR EQUIPOS POR CIUDAD
    public ResponseEntity<?> buscarPorCiudad(String ciudad) {
        List<com.example.Quiz2Linea3.Model.Equipo> equipos = equipoRepository.buscarPorCiudad(ciudad);
        if (equipos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCONTRARON EQUIPOS EN LA CIUDAD: " + ciudad);
        }
        return ResponseEntity.ok(equipos);
    }

    // CONSULTA NATIVA - CONTAR EQUIPOS
    public ResponseEntity<?> contarEquipos() {
        Integer total = equipoRepository.contarEquipos();
        return ResponseEntity.ok("TOTAL DE EQUIPOS REGISTRADOS: " + total);
    }
}
