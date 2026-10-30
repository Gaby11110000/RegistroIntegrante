package model;

/**
 * Modelo: representa a un participante del proyecto educativo.
 */
public class Integrante {

    private String nombres;
    private String apellidos;
    private String urlRepositorio;
    private String tipoEstudiante;
    private String proyectoEducativo;

    public Integrante(String nombres, String apellidos, String urlRepositorio,
                      String tipoEstudiante, String proyectoEducativo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.urlRepositorio = urlRepositorio;
        this.tipoEstudiante = tipoEstudiante;
        this.proyectoEducativo = proyectoEducativo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUrlRepositorio() {
        return urlRepositorio;
    }

    public void setUrlRepositorio(String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }

    public String getTipoEstudiante() {
        return tipoEstudiante;
    }

    public void setTipoEstudiante(String tipoEstudiante) {
        this.tipoEstudiante = tipoEstudiante;
    }

    public String getProyectoEducativo() {
        return proyectoEducativo;
    }

    public void setProyectoEducativo(String proyectoEducativo) {
        this.proyectoEducativo = proyectoEducativo;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + tipoEstudiante + ") - " + proyectoEducativo;
    }
}
