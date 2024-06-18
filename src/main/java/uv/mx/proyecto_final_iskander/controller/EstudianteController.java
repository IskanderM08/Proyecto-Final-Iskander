package uv.mx.proyecto_final_iskander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody Estudiante nuevoEstudiante) {
        return new ResponseEntity<>(estudianteService.agregarEstudiante(nuevoEstudiante), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiante(@PathVariable String idEstudiante) {
        Estudiante estudiante = estudianteService.buscarEstudiante(idEstudiante);
        return estudiante != null ? new ResponseEntity<>(estudiante, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> mostrarEstudiantes() {
        return new ResponseEntity<>(estudianteService.mostrarEstudiantes(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable String idEstudiante) {
        estudianteService.eliminarEstudiante(idEstudiante);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
