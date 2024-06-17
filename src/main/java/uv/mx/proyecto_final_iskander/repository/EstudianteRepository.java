package uv.mx.proyecto_final_iskander.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uv.mx.proyecto_final_iskander.model.Estudiante;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
}
