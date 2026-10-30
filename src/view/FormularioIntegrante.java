package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Vista: contiene únicamente la interfaz gráfica.
 * No tiene lógica de negocio ni validaciones.
 */
public class FormularioIntegrante extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField txtNombres = new JTextField(25);
    private final JTextField txtApellidos = new JTextField(25);
    private final JTextField txtRepositorio = new JTextField(25);

    private final JRadioButton rbNuevo = new JRadioButton("Nuevo", true);
    private final JRadioButton rbAntiguo = new JRadioButton("Antiguo");
    private final ButtonGroup grupoTipo = new ButtonGroup();

    private final JComboBox<String> cmbProyecto = new JComboBox<>(new String[]{
            "Ingeniería de Sistemas",
            "Ingeniería Electrónica",
            "Ingeniería Industrial",
            "Ingeniería Civil"
    });

    private final JButton btnRegistrar = new JButton("Registrar");

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Nombres", "Apellidos", "Repositorio", "Tipo", "Proyecto educativo"}, 0) {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);

    public FormularioIntegrante() {
        super("Registro de integrantes - Proyecto educativo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(crearPanelFormulario(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(780, 520));
        setLocationRelativeTo(null);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del integrante"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombres
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        panel.add(txtNombres, gbc);

        // Apellidos
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        panel.add(txtApellidos, gbc);

        // Repositorio
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panel.add(new JLabel("Repositorio (URL GitHub):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        panel.add(txtRepositorio, gbc);

        // Tipo de estudiante
        grupoTipo.add(rbNuevo);
        grupoTipo.add(rbAntiguo);
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTipo.add(rbNuevo);
        panelTipo.add(rbAntiguo);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panel.add(new JLabel("Tipo de estudiante:"), gbc);
        gbc.gridx = 1;
        panel.add(panelTipo, gbc);

        // Proyecto educativo
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Proyecto educativo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cmbProyecto, gbc);

        // Botón
        gbc.gridx = 1; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnRegistrar, gbc);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Integrantes registrados"));
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    // ---------- Métodos de acceso para el controlador ----------

    public String getNombres() {
        return txtNombres.getText().trim();
    }

    public String getApellidos() {
        return txtApellidos.getText().trim();
    }

    public String getRepositorio() {
        return txtRepositorio.getText().trim();
    }

    public String getTipoEstudiante() {
        return rbNuevo.isSelected() ? "Nuevo" : "Antiguo";
    }

    public String getProyectoEducativo() {
        return (String) cmbProyecto.getSelectedItem();
    }

    public void addRegistrarListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtRepositorio.setText("");
        rbNuevo.setSelected(true);
        cmbProyecto.setSelectedIndex(0);
        txtNombres.requestFocusInWindow();
    }

    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    public void agregarFila(String nombres, String apellidos, String repositorio,
                            String tipo, String proyecto) {
        modeloTabla.addRow(new Object[]{nombres, apellidos, repositorio, tipo, proyecto});
    }
}
