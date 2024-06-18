package uv.mx.proyecto_final_iskander.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "materias")
public class Materia {
    @Id
    private String idMateria;
    private String nombreMateria;
    private String nombreMaestro;
    private List<String> estudiantesCursando;
}
