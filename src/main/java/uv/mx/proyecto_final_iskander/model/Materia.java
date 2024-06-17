package uv.mx.proyecto_final_iskander.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "Materias")
public class Materia {
    private long idMateria;
    private String nombreMateria;
    private String nombreProfesor;
    private List<String> estudiantesCursando;
}
