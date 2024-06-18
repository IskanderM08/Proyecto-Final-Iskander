package uv.mx.proyecto_final_iskander.model;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "estudiantes")
public class Estudiante {
    @Id
    private String idEstudiante;

    @NotBlank
    private String nombreEstudiante;

    @NotBlank
    private String numeroTelefonico;

    @NotBlank
    private String correoElectronico;
}
