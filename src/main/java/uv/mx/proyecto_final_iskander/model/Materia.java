package uv.mx.proyecto_final_iskander.model;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Data
@Document(collection = "materias")
public class Materia {
    @Id
    private String idMateria;

    @NotBlank
    private String nombreMateria;

    @NotBlank
    private String nombreMaestro;

    private List<String> estudiantesCursando;
}
