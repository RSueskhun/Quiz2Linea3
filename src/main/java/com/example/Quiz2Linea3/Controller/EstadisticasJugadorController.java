package com.example.Quiz2Linea3.Controller;

import com.example.Quiz2Linea3.Model.EstadisticasJugador;
import com.example.Quiz2Linea3.Service.EstadisticasJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estadisticas")
@CrossOrigin(origins = "*")
public class EstadisticasJugadorController {

    @Autowired
    private EstadisticasJugadorService estadisticasJugadorService;

    // POST - GUARDAR
    @PostMapping(value = "/guardar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> guardar(@RequestBody EstadisticasJugador estadistica) {
        return estadisticasJugadorService.guardar(estadistica);
    }

    // GET - LISTAR TODAS
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return estadisticasJugadorService.listar();
    }

    // GET - BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return estadisticasJugadorService.buscarPorId(id);
    }

    // PUT - ACTUALIZAR
    @PutMapping(value = "/actualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody EstadisticasJugador estadistica) {
        return estadisticasJugadorService.actualizar(id, estadistica);
    }

    // DELETE - ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        return estadisticasJugadorService.eliminarPorId(id);
    }

    // CONSULTA NATIVA 1 - Buscar estadísticas por jugador
    @GetMapping("/buscar/jugador/{idJugador}")
    public ResponseEntity<?> buscarPorJugador(@PathVariable Integer idJugador) {
        return estadisticasJugadorService.buscarPorJugador(idJugador);
    }

    // CONSULTA NATIVA 2 - Promedio de goles por jugador
    @GetMapping("/promedio/goles/{idJugador}")
    public ResponseEntity<?> promedioGolesPorJugador(@PathVariable Integer idJugador) {
        return estadisticasJugadorService.promedioGolesPorJugador(idJugador);
    }
}
