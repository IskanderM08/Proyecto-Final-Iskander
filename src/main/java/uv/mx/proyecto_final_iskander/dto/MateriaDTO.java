package uv.mx.proyecto_final_iskander.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class MateriaDTO {

    private String idMateria;

    @NotBlank
    private String nombreMateria;

    @NotBlank
    private String nombreMaestro;

    private List<String> estudiantesCursando;

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public List<String> getEstudiantesCursando() {
        return estudiantesCursando;
    }

    public void setEstudiantesCursando(List<String> estudiantesCursando) {
        this.estudiantesCursando = estudiantesCursando;
    }
}