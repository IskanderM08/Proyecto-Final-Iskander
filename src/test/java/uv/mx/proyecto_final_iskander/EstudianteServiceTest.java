package uv.mx.proyecto_final_iskander;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.service.EstudianteService;
import uv.mx.proyecto_final_iskander.repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        estudiante.setIdEstudiante("1");
        estudiante.setNombreEstudiante("Estudiante de Prueba");
        estudiante.setNumeroTelefonico("1234567890");
        estudiante.setCorreoElectronico("estudiante@prueba.com");
    }

    @Test
    void testAgregarEstudiante() {
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante estudianteGuardado = estudianteService.agregarEstudiante(estudiante);

        assertEquals(estudiante, estudianteGuardado);
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void testBuscarEstudianteExistente() {
        when(estudianteRepository.findByIdEstudiante("1")).thenReturn(estudiante);

        Estudiante estudianteEncontrado = estudianteService.buscarEstudiante("1");

        assertEquals(estudiante, estudianteEncontrado);
        verify(estudianteRepository, times(1)).findByIdEstudiante("1");
    }

    @Test
    void testBuscarEstudianteNoExistente() {
        when(estudianteRepository.findByIdEstudiante(anyString())).thenReturn(null);

        Estudiante estudianteEncontrado = estudianteService.buscarEstudiante("2");

        assertEquals(null, estudianteEncontrado);
        verify(estudianteRepository, times(1)).findByIdEstudiante("2");
    }

    @Test
    void testMostrarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante);
        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        List<Estudiante> estudiantesObtenidos = estudianteService.mostrarEstudiantes();

        assertEquals(estudiantes, estudiantesObtenidos);
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testEliminarEstudianteExistente() {
        doNothing().when(estudianteRepository).deleteById("1");

        estudianteService.eliminarEstudiante("1");

        verify(estudianteRepository, times(1)).deleteById("1");
    }

    @Test
    void testEliminarEstudianteNoExistente() {
        doThrow(RuntimeException.class).when(estudianteRepository).deleteById("2");

        try {
            estudianteService.eliminarEstudiante("2");
        } catch (RuntimeException e) {
            // Test passed
        }

        verify(estudianteRepository, times(1)).deleteById("2");
    }

    @Test
    void testModificarEstudianteExistente() {
        Estudiante estudianteActualizado = new Estudiante();
        estudianteActualizado.setIdEstudiante("1");
        estudianteActualizado.setNombreEstudiante("Estudiante Actualizado");
        estudianteActualizado.setNumeroTelefonico("9876543210");
        estudianteActualizado.setCorreoElectronico("actualizado@prueba.com");

        when(estudianteRepository.findById("1")).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudianteActualizado);

        Estudiante estudianteModificado = estudianteService.modificarEstudiante("1", estudianteActualizado);

        assertEquals(estudianteActualizado, estudianteModificado);
        verify(estudianteRepository, times(1)).findById("1");
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void testModificarEstudianteNoExistente() {
        Estudiante estudianteActualizado = new Estudiante();
        estudianteActualizado.setIdEstudiante("2");
        estudianteActualizado.setNombreEstudiante("Estudiante Actualizado");

        when(estudianteRepository.findById("2")).thenReturn(Optional.empty());

        try {
            estudianteService.modificarEstudiante("2", estudianteActualizado);
        } catch (RuntimeException e) {
            // Test passed
        }

        verify(estudianteRepository, times(1)).findById("2");
        verify(estudianteRepository, never()).save(any(Estudiante.class));
    }
}

