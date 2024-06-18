package uv.mx.proyecto_final_iskander.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uv.mx.proyecto_final_iskander.model.Estudiante;
import uv.mx.proyecto_final_iskander.repository.EstudianteRepository;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante agregarEstudiante(Estudiante nuevoEstudiante) {
        return estudianteRepository.save(nuevoEstudiante);
    }

    public List<Estudiante> mostrarEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante buscarEstudiante(String idEstudiante) {
        return estudianteRepository.findByIdEstudiante(idEstudiante);
    }

    public void eliminarEstudiante(String idEstudiante) {
        estudianteRepository.deleteById(idEstudiante);
    }
}
