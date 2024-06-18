package uv.mx.proyecto_final_iskander;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uv.mx.proyecto_final_iskander.controller.MateriaController;
import uv.mx.proyecto_final_iskander.dto.MateriaDTO;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.model.Materia;
import uv.mx.proyecto_final_iskander.service.MateriaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    private MateriaDTO materiaDTO;
    private Materia materia;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        materiaDTO = new MateriaDTO();
        materiaDTO.setIdMateria("1");
        materiaDTO.setNombreMateria("Materia de Prueba");
        materiaDTO.setNombreMaestro("Maestro de Prueba");

        materia = new Materia();
        materia.setIdMateria("1");
        materia.setNombreMateria("Materia de Prueba");
        materia.setNombreMaestro("Maestro de Prueba");

        estudiante = new Estudiante();
        estudiante.setIdEstudiante("1");
        estudiante.setNombreEstudiante("Estudiante de Prueba");
        estudiante.setNumeroTelefonico("1234567890");
        estudiante.setCorreoElectronico("estudiante@prueba.com");
    }

    @Test
    void testAgregarMateria() {
        when(materiaService.agregarMateria(any(Materia.class))).thenReturn(materia);

        ResponseEntity<Materia> response = materiaController.agregarMateria(materiaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(materia, response.getBody());
        verify(materiaService, times(1)).agregarMateria(any(Materia.class));
    }

    @Test
    void testBuscarMateriaExistente() {
        when(materiaService.buscarMateria("1")).thenReturn(materia);

        ResponseEntity<Materia> response = materiaController.buscarMateria("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materia, response.getBody());
        verify(materiaService, times(1)).buscarMateria("1");
    }

    @Test
    void testBuscarMateriaNoExistente() {
        when(materiaService.buscarMateria(anyString())).thenReturn(null);

        ResponseEntity<Materia> response = materiaController.buscarMateria("2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(materiaService, times(1)).buscarMateria("2");
    }

    @Test
    void testMostrarMaterias() {
        List<Materia> materias = new ArrayList<>();
        materias.add(materia);
        when(materiaService.mostrarMaterias()).thenReturn(materias);

        ResponseEntity<List<Materia>> response = materiaController.mostrarMaterias();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materias, response.getBody());
        verify(materiaService, times(1)).mostrarMaterias();
    }

    @Test
    void testEliminarMateriaExistente() {
        doNothing().when(materiaService).eliminarMateria("1");

        ResponseEntity<Void> response = materiaController.eliminarMateria("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(materiaService, times(1)).eliminarMateria("1");
    }

    @Test
    void testInscribirEstudianteExistente() {
        when(materiaService.inscribirEstudiante(eq("1"), any(Estudiante.class))).thenReturn(materia);

        ResponseEntity<Materia> response = materiaController.inscribirEstudiante("1", estudiante);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materia, response.getBody());
        verify(materiaService, times(1)).inscribirEstudiante(eq("1"), any(Estudiante.class));
    }

    @Test
    void testListarEstudiantesInscritos() {
        List<String> estudiantesCursando = new ArrayList<>();
        estudiantesCursando.add(estudiante.getIdEstudiante());
        when(materiaService.listarEstudiantesInscritos("1")).thenReturn(estudiantesCursando);

        ResponseEntity<List<String>> response = materiaController.listarEstudiantesInscritos("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiantesCursando, response.getBody());
        verify(materiaService, times(1)).listarEstudiantesInscritos("1");
    }

    @Test
    void testEliminarEstudianteInscritoExistente() {
        when(materiaService.eliminarEstudianteInscrito("1", "1")).thenReturn(materia);

        ResponseEntity<Materia> response = materiaController.eliminarEstudianteInscrito("1", "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materia, response.getBody());
        verify(materiaService, times(1)).eliminarEstudianteInscrito("1", "1");
    }

    @Test
    void testEliminarEstudianteInscritoNoExistente() {
        when(materiaService.eliminarEstudianteInscrito("1", "2")).thenReturn(null);

        ResponseEntity<Materia> response = materiaController.eliminarEstudianteInscrito("1", "2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(materiaService, times(1)).eliminarEstudianteInscrito("1", "2");
    }
}
