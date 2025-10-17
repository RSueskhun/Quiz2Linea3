package com.example.Quiz2Linea3.Controller;

import com.example.Quiz2Linea3.Model.Entrenador;
import com.example.Quiz2Linea3.Service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrenador")
@CrossOrigin(origins = "*")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    // POST - GUARDAR ENTRENADOR
    @PostMapping(
            value = "/guardar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardar(@RequestBody Entrenador entrenador) {
        return entrenadorService.guardar(entrenador);
    }

    // GET - LISTAR ENTRENADORES
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return entrenadorService.listar();
    }

    // GET - BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return entrenadorService.buscarPorId(id);
    }

    // PUT - ACTUALIZAR ENTRENADOR
    @PutMapping(
            value = "/actualizar/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        return entrenadorService.actualizar(id, entrenador);
    }

    // DELETE - ELIMINAR ENTRENADOR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        return entrenadorService.eliminarPorId(id);
    }

    // CONSULTA NATIVA 1 - Buscar por especialidad
    @GetMapping("/buscar/especialidad/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidad(@PathVariable String especialidad) {
        return entrenadorService.buscarPorEspecialidad(especialidad);
    }

    // CONSULTA NATIVA 2 - Contar todos los entrenadores
    @GetMapping("/contar")
    public ResponseEntity<?> contarEntrenadores() {
        return entrenadorService.contarEntrenadores();
    }

    // CONSULTA NATIVA 3 - Contar entrenadores por equipo
    @GetMapping("/contar/equipo/{idEquipo}")
    public ResponseEntity<?> contarPorEquipo(@PathVariable Integer idEquipo) {
        return entrenadorService.contarPorEquipo(idEquipo);
    }
}
