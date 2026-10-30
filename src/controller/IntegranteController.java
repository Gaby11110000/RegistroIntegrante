package controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Integrante;
import view.FormularioIntegrante;

/**
 * Controlador: atiende los eventos de la vista, valida los datos,
 * crea objetos del modelo y mantiene la lista en memoria.
 */
public class IntegranteController {

    private final FormularioIntegrante vista;
    private final List<Integrante> integrantes = new ArrayList<>();

    public IntegranteController(FormularioIntegrante vista) {
        this.vista = vista;
        this.vista.addRegistrarListener(e -> registrarIntegrante());
    }

    private void registrarIntegrante() {
        String nombres = vista.getNombres();
        String apellidos = vista.getApellidos();
        String repositorio = vista.getRepositorio();

        if (nombres.isEmpty()) {
            mostrarError("El campo Nombres no puede estar vacío.");
            return;
        }
        if (apellidos.isEmpty()) {
            mostrarError("El campo Apellidos no puede estar vacío.");
            return;
        }
        if (!esUrlValida(repositorio)) {
            mostrarError("El repositorio debe ser una URL válida.\n"
                    + "Ejemplo: https://github.com/usuario/proyecto");
            return;
        }

        Integrante nuevo = new Integrante(
                nombres,
                apellidos,
                repositorio,
                vista.getTipoEstudiante(),
                vista.getProyectoEducativo());

        integrantes.add(nuevo);
        actualizarVista();
        vista.limpiarFormulario();

        JOptionPane.showMessageDialog(vista,
                "Integrante registrado correctamente.",
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /** Validación básica: esquema http/https y un host con punto (ej. github.com). */
    private boolean esUrlValida(String texto) {
        if (texto == null || texto.isEmpty() || texto.contains(" ")) {
            return false;
        }
        try {
            URI uri = new URI(texto);
            String esquema = uri.getScheme();
            String host = uri.getHost();
            return esquema != null
                    && (esquema.equalsIgnoreCase("http") || esquema.equalsIgnoreCase("https"))
                    && host != null
                    && host.contains(".");
        } catch (Exception ex) {
            return false;
        }
    }

    /** Refresca la tabla con todos los integrantes del ArrayList. */
    private void actualizarVista() {
        vista.limpiarTabla();
        for (Integrante i : integrantes) {
            vista.agregarFila(i.getNombres(), i.getApellidos(), i.getUrlRepositorio(),
                    i.getTipoEstudiante(), i.getProyectoEducativo());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Datos inválidos", JOptionPane.ERROR_MESSAGE);
    }
}
