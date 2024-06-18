package uv.mx.proyecto_final_iskander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.mx.proyecto_final_iskander.dto.MateriaDTO;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.model.Materia;
import uv.mx.proyecto_final_iskander.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/materiasAdd")
    public ResponseEntity<Materia> agregarMateria(@RequestBody MateriaDTO nuevaMateriaDTO) {
        Materia nuevaMateria = DTOAEntidad(nuevaMateriaDTO);
        return new ResponseEntity<>(materiaService.agregarMateria(nuevaMateria), HttpStatus.CREATED);
    }

    @GetMapping("/materiasGet/{idMateria}")
    public ResponseEntity<Materia> buscarMateria(@PathVariable String idMateria) {
        Materia materia = materiaService.buscarMateria(idMateria);
        return materia != null ? new ResponseEntity<>(materia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/materiasGetAll")
    public ResponseEntity<List<Materia>> mostrarMaterias() {
        return new ResponseEntity<>(materiaService.mostrarMaterias(), HttpStatus.OK);
    }

    @DeleteMapping("/materiasDelete/{idMateria}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable String idMateria) {
        materiaService.eliminarMateria(idMateria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/materiasUpdate/{idMateria}")
    public ResponseEntity<Materia> modificarMateria(@PathVariable String idMateria, @RequestBody MateriaDTO materiaActualizadaDTO) {
        Materia materiaActualizada = DTOAEntidad(materiaActualizadaDTO);
        Materia materia = materiaService.modificarMateria(idMateria, materiaActualizada);
        if (materia != null) {
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/materiasInscribir/{idMateria}")
    public ResponseEntity<Materia> inscribirEstudiante(@PathVariable String idMateria, @RequestBody Estudiante estudiante) {
        Materia materia = materiaService.inscribirEstudiante(idMateria, estudiante);
        if (materia != null) {
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/materiasListarInscritos/{idMateria}")
    public ResponseEntity<List<String>> listarEstudiantesInscritos(@PathVariable String idMateria) {
        List<String> estudiantesCursando = materiaService.listarEstudiantesInscritos(idMateria);
        if (estudiantesCursando != null) {
            return new ResponseEntity<>(estudiantesCursando, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/materiasEliminarInscrito/{idMateria}/{idEstudiante}")
    public ResponseEntity<Materia> eliminarEstudianteInscrito(@PathVariable String idMateria, @PathVariable String idEstudiante) {
        Materia materia = materiaService.eliminarEstudianteInscrito(idMateria, idEstudiante);
        if (materia != null) {
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Materia DTOAEntidad(MateriaDTO materiaDTO) {
        Materia materia = new Materia();
        materia.setIdMateria(materiaDTO.getIdMateria());
        materia.setNombreMateria(materiaDTO.getNombreMateria());
        materia.setNombreMaestro(materiaDTO.getNombreMaestro());
        materia.setEstudiantesCursando(materiaDTO.getEstudiantesCursando());
        return materia;
    }
}
