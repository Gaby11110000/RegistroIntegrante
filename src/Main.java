import controller.IntegranteController;
import javax.swing.SwingUtilities;
import view.FormularioIntegrante;

/**
 * Punto de entrada: crea la vista, la vincula con el controlador
 * y muestra la ventana principal.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioIntegrante vista = new FormularioIntegrante();
            new IntegranteController(vista); // el controlador se engancha a la vista
            vista.setVisible(true);
        });
    }
}
