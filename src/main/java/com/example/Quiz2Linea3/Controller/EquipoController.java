package com.example.Quiz2Linea3.Controller;

import com.example.Quiz2Linea3.Model.Equipo;
import com.example.Quiz2Linea3.Service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipo")
@CrossOrigin(origins = "*")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // POST - GUARDAR EQUIPO
    @PostMapping(
            value = "/guardar",
            consumes = {"application/json", "application/json;charset=UTF-8"},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardar(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }

    // GET - LISTAR EQUIPOS
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return equipoService.listar();
    }

    // GET - BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return equipoService.buscarPorId(id);
    }

    // PUT - ACTUALIZAR
    @PutMapping(
            value = "/actualizar/{id}",
            consumes = {"application/json", "application/json;charset=UTF-8"},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Equipo equipo) {
        return equipoService.actualizar(id, equipo);
    }

    // DELETE - ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
        return equipoService.eliminarPorId(id);
    }

    // CONSULTA NATIVA - Buscar por ciudad
    @GetMapping("/buscar/ciudad/{ciudad}")
    public ResponseEntity<?> buscarPorCiudad(@PathVariable String ciudad) {
        return equipoService.buscarPorCiudad(ciudad);
    }

    // CONSULTA NATIVA - Contar equipos
    @GetMapping("/contar")
    public ResponseEntity<?> contarEquipos() {
        return equipoService.contarEquipos();
    }
}
