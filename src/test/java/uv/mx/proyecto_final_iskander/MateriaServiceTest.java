package uv.mx.proyecto_final_iskander;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.model.Materia;
import uv.mx.proyecto_final_iskander.service.MateriaService;
import uv.mx.proyecto_final_iskander.repository.MateriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private MateriaService materiaService;

    private Materia materia;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        materia = new Materia();
        materia.setIdMateria("1");
        materia.setNombreMateria("Materia de Prueba");
        materia.setNombreMaestro("Maestro de Prueba");
        materia.setEstudiantesCursando(new ArrayList<>());

        estudiante = new Estudiante();
        estudiante.setIdEstudiante("1");
        estudiante.setNombreEstudiante("Estudiante de Prueba");
        estudiante.setNumeroTelefonico("1234567890");
        estudiante.setCorreoElectronico("estudiante@prueba.com");
    }

    @Test
    void testAgregarMateria() {
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        Materia materiaGuardada = materiaService.agregarMateria(materia);

        assertEquals(materia, materiaGuardada);
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void testBuscarMateriaExistente() {
        when(materiaRepository.findByIdMateria("1")).thenReturn(materia);

        Materia materiaEncontrada = materiaService.buscarMateria("1");

        assertEquals(materia, materiaEncontrada);
        verify(materiaRepository, times(1)).findByIdMateria("1");
    }

    @Test
    void testBuscarMateriaNoExistente() {
        when(materiaRepository.findByIdMateria(anyString())).thenReturn(null);

        Materia materiaEncontrada = materiaService.buscarMateria("2");

        assertEquals(null, materiaEncontrada);
        verify(materiaRepository, times(1)).findByIdMateria("2");
    }

    @Test
    void testMostrarMaterias() {
        List<Materia> materias = new ArrayList<>();
        materias.add(materia);
        when(materiaRepository.findAll()).thenReturn(materias);

        List<Materia> materiasObtenidas = materiaService.mostrarMaterias();

        assertEquals(materias, materiasObtenidas);
        verify(materiaRepository, times(1)).findAll();
    }

    @Test
    void testEliminarMateriaExistente() {
        doNothing().when(materiaRepository).deleteById("1");

        materiaService.eliminarMateria("1");

        verify(materiaRepository, times(1)).deleteById("1");
    }

    @Test
    void testEliminarMateriaNoExistente() {
        doThrow(RuntimeException.class).when(materiaRepository).deleteById("2");

        try {
            materiaService.eliminarMateria("2");
        } catch (RuntimeException e) {
            // Test passed
        }

        verify(materiaRepository, times(1)).deleteById("2");
    }

    @Test
    void testInscribirEstudiante() {
        List<String> estudiantesCursando = new ArrayList<>();
        estudiantesCursando.add(estudiante.getIdEstudiante());

        when(materiaRepository.findByIdMateria("1")).thenReturn(materia);
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        Materia materiaConEstudiante = materiaService.inscribirEstudiante("1", estudiante);

        assertEquals(estudiantesCursando, materiaConEstudiante.getEstudiantesCursando());
        verify(materiaRepository, times(1)).findByIdMateria("1");
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void testListarEstudiantesInscritos() {
        List<String> estudiantesCursando = new ArrayList<>();
        estudiantesCursando.add(estudiante.getIdEstudiante());
        materia.setEstudiantesCursando(estudiantesCursando);

        when(materiaRepository.findByIdMateria("1")).thenReturn(materia);

        List<String> estudiantesObtenidos = materiaService.listarEstudiantesInscritos("1");

        assertEquals(estudiantesCursando, estudiantesObtenidos);
        verify(materiaRepository, times(1)).findByIdMateria("1");
    }

    @Test
    void testEliminarEstudianteInscrito() {
        List<String> estudiantesCursando = new ArrayList<>();
        estudiantesCursando.add(estudiante.getIdEstudiante());
        materia.setEstudiantesCursando(estudiantesCursando);

        when(materiaRepository.findByIdMateria("1")).thenReturn(materia);
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia);

        Materia materiaSinEstudiante = materiaService.eliminarEstudianteInscrito("1", "1");

        assertEquals(0, materiaSinEstudiante.getEstudiantesCursando().size());
        verify(materiaRepository, times(1)).findByIdMateria("1");
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void testModificarMateriaExistente() {
        Materia materiaActualizada = new Materia();
        materiaActualizada.setIdMateria("1");
        materiaActualizada.setNombreMateria("Materia Actualizada");
        materiaActualizada.setNombreMaestro("Maestro Actualizado");

        when(materiaRepository.findById("1")).thenReturn(Optional.of(materia));
        when(materiaRepository.save(any(Materia.class))).thenReturn(materiaActualizada);

        Materia materiaModificada = materiaService.modificarMateria("1", materiaActualizada);

        assertEquals(materiaActualizada, materiaModificada);
        verify(materiaRepository, times(1)).findById("1");
        verify(materiaRepository, times(1)).save(any(Materia.class));
    }

    @Test
    void testModificarMateriaNoExistente() {
        Materia materiaActualizada = new Materia();
        materiaActualizada.setIdMateria("2");
        materiaActualizada.setNombreMateria("Materia Actualizada");

        when(materiaRepository.findById("2")).thenReturn(Optional.empty());

        try {
            materiaService.modificarMateria("2", materiaActualizada);
        } catch (RuntimeException e) {
            // Test passed
        }

        verify(materiaRepository, times(1)).findById("2");
        verify(materiaRepository, never()).save(any(Materia.class));
    }
}


