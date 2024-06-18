package uv.mx.proyecto_final_iskander;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uv.mx.proyecto_final_iskander.dto.EstudianteDTO;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.controller.EstudianteController;
import uv.mx.proyecto_final_iskander.service.EstudianteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteControllerTest {

    @Mock
    private EstudianteService estudianteService;

    @InjectMocks
    private EstudianteController estudianteController;

    private EstudianteDTO estudianteDTO;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudianteDTO = new EstudianteDTO();
        estudianteDTO.setIdEstudiante("1");
        estudianteDTO.setNombreEstudiante("Estudiante de Prueba");
        estudianteDTO.setNumeroTelefonico("1234567890");
        estudianteDTO.setCorreoElectronico("estudiante@prueba.com");

        estudiante = new Estudiante();
        estudiante.setIdEstudiante("1");
        estudiante.setNombreEstudiante("Estudiante de Prueba");
        estudiante.setNumeroTelefonico("1234567890");
        estudiante.setCorreoElectronico("estudiante@prueba.com");
    }

    @Test
    void testAgregarEstudiante() {
        when(estudianteService.agregarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        ResponseEntity<Estudiante> response = estudianteController.agregarEstudiante(estudianteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
        verify(estudianteService, times(1)).agregarEstudiante(any(Estudiante.class));
    }

    @Test
    void testBuscarEstudianteExistente() {
        when(estudianteService.buscarEstudiante("1")).thenReturn(estudiante);

        ResponseEntity<Estudiante> response = estudianteController.buscarEstudiante("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
        verify(estudianteService, times(1)).buscarEstudiante("1");
    }

    @Test
    void testBuscarEstudianteNoExistente() {
        when(estudianteService.buscarEstudiante(anyString())).thenReturn(null);

        ResponseEntity<Estudiante> response = estudianteController.buscarEstudiante("2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(estudianteService, times(1)).buscarEstudiante("2");
    }

    @Test
    void testMostrarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante);
        when(estudianteService.mostrarEstudiantes()).thenReturn(estudiantes);

        ResponseEntity<List<Estudiante>> response = estudianteController.mostrarEstudiantes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiantes, response.getBody());
        verify(estudianteService, times(1)).mostrarEstudiantes();
    }

    @Test
    void testEliminarEstudianteExistente() {
        doNothing().when(estudianteService).eliminarEstudiante("1");

        ResponseEntity<Void> response = estudianteController.eliminarEstudiante("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(estudianteService, times(1)).eliminarEstudiante("1");
    }

    @Test
    void testActualizarEstudianteExistente() {
        when(estudianteService.modificarEstudiante(eq("1"), any(Estudiante.class))).thenReturn(estudiante);

        ResponseEntity<Estudiante> response = estudianteController.actualizarEstudiante("1", estudianteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
        verify(estudianteService, times(1)).modificarEstudiante(eq("1"), any(Estudiante.class));
    }
}

