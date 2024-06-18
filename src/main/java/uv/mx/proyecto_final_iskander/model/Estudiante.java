package uv.mx.proyecto_final_iskander.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "estudiantes")
public class Estudiante {
    private String idEstudiante;
    private String nombreEstudiante;
    private String numeroTelefonico;
    private String correoElectronico;
}
