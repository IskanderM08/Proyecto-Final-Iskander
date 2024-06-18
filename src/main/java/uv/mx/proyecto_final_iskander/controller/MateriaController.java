package uv.mx.proyecto_final_iskander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.mx.proyecto_final_iskander.model.Materia;
import uv.mx.proyecto_final_iskander.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @PostMapping
    public ResponseEntity<Materia> agregarMateria(@RequestBody Materia nuevaMateria) {
        return new ResponseEntity<>(materiaService.agregarMateria(nuevaMateria), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarMateria(@PathVariable String idMateria) {
        Materia materia = materiaService.buscarMateria(idMateria);
        return materia != null ? new ResponseEntity<>(materia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Materia>> mostrarMaterias() {
        return new ResponseEntity<>(materiaService.mostrarMaterias(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable String idMateria) {
        materiaService.eliminarMateria(idMateria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
