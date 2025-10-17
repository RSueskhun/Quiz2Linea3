package com.example.Quiz2Linea3.Controller;

import com.example.Quiz2Linea3.Model.Jugador;
import com.example.Quiz2Linea3.Service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugador")
@CrossOrigin(origins = "*")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // POST - GUARDAR JUGADOR
    @PostMapping(value = "/guardar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> guardar(@RequestBody Jugador jugador) {
        return jugadorService.guardar(jugador);
    }

    // GET - LISTAR TODOS
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return jugadorService.listar();
    }

    // GET - BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return jugadorService.buscarPorId(id);
    }

    // PUT - ACTUALIZAR
    @PutMapping(value = "/actualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) {
        return jugadorService.actualizar(id, jugador);
    }

    // DELETE - ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        return jugadorService.eliminarPorId(id);
    }

    // CONSULTA NATIVA 1 - Buscar jugadores por posición
    @GetMapping("/buscar/posicion/{posicion}")
    public ResponseEntity<?> buscarPorPosicion(@PathVariable String posicion) {
        return jugadorService.buscarPorPosicion(posicion);
    }

    // CONSULTA NATIVA 2 - Contar jugadores por equipo
    @GetMapping("/contar/equipo/{idEquipo}")
    public ResponseEntity<?> contarPorEquipo(@PathVariable Integer idEquipo) {
        return jugadorService.contarPorEquipo(idEquipo);
    }
}
