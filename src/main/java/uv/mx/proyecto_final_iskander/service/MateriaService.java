package uv.mx.proyecto_final_iskander.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.model.Materia;
import uv.mx.proyecto_final_iskander.repository.MateriaRepository;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public Materia agregarMateria(Materia nuevaMateria) {
        return materiaRepository.save(nuevaMateria);
    }

    public List<Materia> mostrarMaterias() {
        return materiaRepository.findAll();
    }

    public Materia buscarMateria(String idMateria) {
        return materiaRepository.findByIdMateria(idMateria);
    }

    public void eliminarMateria(String idMateria) {
        materiaRepository.deleteById(idMateria);
    }

    public Materia inscribirEstudiante(String idMateria, Estudiante estudiante) {
        Materia materia = materiaRepository.findByIdMateria(idMateria);

        if (materia != null) {
            List<String> estudiantesCursando = materia.getEstudiantesCursando();
            estudiantesCursando.add(estudiante.getIdEstudiante());
            materia.setEstudiantesCursando(estudiantesCursando);
            return materiaRepository.save(materia);
        }

        return null;
    }

    public List<String> listarEstudiantesInscritos(String idMateria) {
        Materia materia = materiaRepository.findByIdMateria(idMateria);
        if (materia != null) {
            return materia.getEstudiantesCursando();
        }
        return null;
    }

    public Materia eliminarEstudianteInscrito(String idMateria, String idEstudiante) {
        Materia materia = materiaRepository.findByIdMateria(idMateria);
        if (materia != null) {
            List<String> estudiantesCursando = materia.getEstudiantesCursando();
            estudiantesCursando.remove(idEstudiante);
            materia.setEstudiantesCursando(estudiantesCursando);
            return materiaRepository.save(materia);
        }
        return null;
    }

}
