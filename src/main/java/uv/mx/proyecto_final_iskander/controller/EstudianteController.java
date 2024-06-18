package uv.mx.proyecto_final_iskander.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uv.mx.proyecto_final_iskander.dto.EstudianteDTO;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/estudiantesAdd")
    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody EstudianteDTO nuevoEstudianteDTO) {
        Estudiante nuevoEstudiante = DTOAEntidad(nuevoEstudianteDTO);
        return new ResponseEntity<>(estudianteService.agregarEstudiante(nuevoEstudiante), HttpStatus.CREATED);
    }

    @GetMapping("/estudiantesGet/{idEstudiante}")
    public ResponseEntity<Estudiante> buscarEstudiante(@PathVariable String idEstudiante) {
        Estudiante estudiante = estudianteService.buscarEstudiante(idEstudiante);
        return estudiante != null ? new ResponseEntity<>(estudiante, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/estudiantesGetAll")
    public ResponseEntity<List<Estudiante>> mostrarEstudiantes() {
        return new ResponseEntity<>(estudianteService.mostrarEstudiantes(), HttpStatus.OK);
    }

    @DeleteMapping("/estudiantesDelete")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable String idEstudiante) {
        estudianteService.eliminarEstudiante(idEstudiante);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/estudiantesUpdate/{idEstudiante}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable String idEstudiante, @RequestBody EstudianteDTO estudianteActualizadoDTO) {
        Estudiante estudianteActualizado = DTOAEntidad(estudianteActualizadoDTO);
        Estudiante estudiante = estudianteService.modificarEstudiante(idEstudiante, estudianteActualizado);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    private Estudiante DTOAEntidad(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(estudianteDTO.getIdEstudiante());
        estudiante.setNombreEstudiante(estudianteDTO.getNombreEstudiante());
        estudiante.setNumeroTelefonico(estudianteDTO.getNumeroTelefonico());
        estudiante.setCorreoElectronico(estudianteDTO.getCorreoElectronico());
        return estudiante;
    }
}
