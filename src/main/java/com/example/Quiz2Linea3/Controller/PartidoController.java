package com.example.Quiz2Linea3.Controller;

import com.example.Quiz2Linea3.Model.Partido;
import com.example.Quiz2Linea3.Service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/partido")
@CrossOrigin(origins = "*")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    // POST - GUARDAR
    @PostMapping(value = "/guardar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> guardar(@RequestBody Partido partido) {
        return partidoService.guardar(partido);
    }

    // GET - LISTAR
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return partidoService.listar();
    }

    // GET - BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return partidoService.buscarPorId(id);
    }

    // PUT - ACTUALIZAR
    @PutMapping(value = "/actualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Partido partido) {
        return partidoService.actualizar(id, partido);
    }

    // DELETE - ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        return partidoService.eliminarPorId(id);
    }

    // CONSULTA NATIVA 1 - Buscar por fecha
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        return partidoService.buscarPorFecha(fecha);
    }

    // CONSULTA NATIVA 2 - Contar por estadio
    @GetMapping("/contar/estadio/{estadio}")
    public ResponseEntity<?> contarPorEstadio(@PathVariable String estadio) {
        return partidoService.contarPorEstadio(estadio);
    }
}
