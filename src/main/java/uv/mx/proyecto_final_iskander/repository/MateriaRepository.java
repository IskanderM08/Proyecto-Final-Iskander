package uv.mx.proyecto_final_iskander.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uv.mx.proyecto_final_iskander.model.Materia;

public interface MateriaRepository extends MongoRepository<Materia, String> {
}
