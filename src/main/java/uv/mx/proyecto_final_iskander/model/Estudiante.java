package uv.mx.proyecto_final_iskander.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "Estudiantes")
public class Estudiante {
    private long idEstudiante;
    private String nombreEstudiante;
    private String numeroTelefonico;
    private String correoElectronico;
}
