package com.example.Quiz2Linea3.Service;

import com.example.Quiz2Linea3.Model.Entrenador;
import com.example.Quiz2Linea3.Repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    // POST - GUARDAR ENTRENADOR
    public ResponseEntity<?> guardar(Entrenador entrenador) {
        try {
            if (entrenador.getNombre() == null || entrenador.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("EL NOMBRE DEL ENTRENADOR ES OBLIGATORIO");
            }
            Entrenador nuevo = entrenadorRepository.save(entrenador);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("ENTRENADOR REGISTRADO CON ID: " + nuevo.getIdEntrenador());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR AL GUARDAR ENTRENADOR: " + e.getMessage());
        }
    }

    // GET - LISTAR ENTRENADORES
    public ResponseEntity<?> listar() {
        List<Entrenador> lista = entrenadorRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("NO EXISTEN ENTRENADORES REGISTRADOS");
        }
        return ResponseEntity.ok(lista);
    }

    // GET - BUSCAR POR ID
    public ResponseEntity<?> buscarPorId(Integer id) {
        Optional<Entrenador> entrenador = entrenadorRepository.findById(id);
        if (entrenador.isPresent()) {
            return ResponseEntity.ok(entrenador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRÓ ENTRENADOR CON ID: " + id);
        }
    }

    // PUT - ACTUALIZAR ENTRENADOR
    public ResponseEntity<?> actualizar(Integer id, Entrenador entrenadorActualizado) {
        Optional<Entrenador> existente = entrenadorRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE ENTRENADOR CON ID: " + id);
        }

        Entrenador entrenador = existente.get();
        entrenador.setNombre(entrenadorActualizado.getNombre());
        entrenador.setEspecialidad(entrenadorActualizado.getEspecialidad());
        entrenador.setEquipo(entrenadorActualizado.getEquipo());

        entrenadorRepository.save(entrenador);
        return ResponseEntity.ok("ENTRENADOR CON ID " + id + " ACTUALIZADO CORRECTAMENTE");
    }

    // DELETE - ELIMINAR ENTRENADOR
    public ResponseEntity<?> eliminarPorId(Integer id) {
        if (!entrenadorRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO EXISTE ENTRENADOR CON ID: " + id);
        }
        entrenadorRepository.deleteById(id);
        return ResponseEntity.ok("ENTRENADOR ELIMINADO CON ÉXITO");
    }

    // CONSULTA NATIVA 1 - Buscar por especialidad
    public ResponseEntity<?> buscarPorEspecialidad(String especialidad) {
        List<Entrenador> lista = entrenadorRepository.buscarPorEspecialidad(especialidad);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("NO SE ENCONTRARON ENTRENADORES CON ESPECIALIDAD: " + especialidad);
        }
        return ResponseEntity.ok(lista);
    }

    // CONSULTA NATIVA 2 - Contar todos los entrenadores
    public ResponseEntity<?> contarEntrenadores() {
        Integer total = entrenadorRepository.contarEntrenadores();
        return ResponseEntity.ok("TOTAL DE ENTRENADORES REGISTRADOS: " + total);
    }

    // CONSULTA NATIVA 3 - Contar entrenadores por equipo
    public ResponseEntity<?> contarPorEquipo(Integer idEquipo) {
        Integer total = entrenadorRepository.contarPorEquipo(idEquipo);
        return ResponseEntity.ok("TOTAL DE ENTRENADORES EN EL EQUIPO ID " + idEquipo + ": " + total);
    }
}
