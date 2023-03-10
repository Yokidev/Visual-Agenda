import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class InterfazG extends JFrame {
    private static final DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter fechaInglesa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtCita;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtObservacion;
    private JTextField txtCP;
    private JTextField txtEmail;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnSave;
    private JButton btnBuscar;
    private JButton btnDel;
    private JButton btnClear;
    private boolean alta = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new 1());
    }

    private void EstadoCtrl(boolean buscar, boolean save, boolean del) {
        this.btnBuscar.setEnabled(buscar);
        this.btnSave.setEnabled(save);
        this.btnDel.setEnabled(del);
    }

    private void MostrarContacto() {
        this.txtId.setText(Integer.toString(Cita.getId()));
        this.txtCita.setText(Cita.getCita());
        this.txtFecha.setText(Cita.getFecha().format(formatofecha));
        this.txtHora.setText(Cita.getHora().format(formatoHora));
        this.txtObservacion.setText(Cita.getObservacion());
        this.EstadoCtrl(false, true, true);
    }

    private void IniciarTabla() {
        try {
            DAO.RellenarTabla(0, Conexion.conectar(), (String)null, (String)null, (String)null, this.modelo);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    private void DonLimpio() {
        this.txtId.setText("");
        this.txtCita.setText("");
        this.txtFecha.setText("");
        this.txtHora.setText("");
        this.txtObservacion.setText("");
        Cita.setCita("");
        Cita.setFecha(Date.valueOf("1993-01-01"));
        Cita.setHora(Time.valueOf("00:00:00"));
        Cita.setId(0);
        Cita.setObservacion("");
        this.IniciarTabla();
        this.EstadoCtrl(true, false, false);
    }

    public InterfazG() {
        this.setResizable(false);
        this.setTitle("Agenda de Citas");
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 868, 791);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.contentPane.setLayout(new BorderLayout(0, 0));
        this.setContentPane(this.contentPane);
        JLabel lblNewLabel = new JLabel("Agenda de Citas");
        lblNewLabel.setForeground(new Color(128, 0, 0));
        lblNewLabel.setFont(new Font("Century Schoolbook", 1, 40));
        lblNewLabel.setHorizontalAlignment(0);
        this.contentPane.add(lblNewLabel, "North");
        JPanel panel = new JPanel();
        this.contentPane.add(panel, "Center");
        panel.setLayout(new BorderLayout(0, 0));
        JPanel pSuper = new JPanel();
        panel.add(pSuper, "North");
        pSuper.setLayout(new GridLayout(0, 2, 0, 0));
        JLabel lblNewLabel_1 = new JLabel("Id:");
        lblNewLabel_1.setFont(new Font("Garamond", 1, 20));
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBackground(new Color(211, 211, 211));
        lblNewLabel_1.setHorizontalAlignment(2);
        pSuper.add(lblNewLabel_1);
        this.txtId = new JTextField();
        this.txtId.setForeground(new Color(128, 0, 0));
        this.txtId.setFont(new Font("Garamond", 0, 20));
        this.txtId.setName("Id");
        this.txtId.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pSuper.add(this.txtId);
        this.txtId.setColumns(10);
        JLabel lblNewLabel_2 = new JLabel("Cita:");
        lblNewLabel_2.setFont(new Font("Garamond", 1, 20));
        lblNewLabel_2.setOpaque(true);
        lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_2.setHorizontalAlignment(2);
        pSuper.add(lblNewLabel_2);
        this.txtCita = new JTextField();
        this.txtCita.setBackground(new Color(245, 245, 245));
        this.txtCita.setForeground(new Color(128, 0, 0));
        this.txtCita.setFont(new Font("Garamond", 0, 20));
        this.txtCita.setName("Cita");
        this.txtCita.setColumns(10);
        pSuper.add(this.txtCita);
        JLabel lblNewLabel_3 = new JLabel("Fecha");
        lblNewLabel_3.setFont(new Font("Garamond", 1, 20));
        lblNewLabel_3.setOpaque(true);
        lblNewLabel_3.setBackground(new Color(211, 211, 211));
        lblNewLabel_3.setHorizontalAlignment(2);
        lblNewLabel_3.setBounds(new Rectangle(0, 0, 100, 0));
        pSuper.add(lblNewLabel_3);
        this.txtFecha = new JTextField();
        this.txtFecha.setForeground(new Color(128, 0, 0));
        this.txtFecha.setFont(new Font("Garamond", 0, 20));
        this.txtFecha.setName("Fecha");
        this.txtFecha.setColumns(10);
        pSuper.add(this.txtFecha);
        JLabel lblNewLabel_4 = new JLabel("Hora:");
        lblNewLabel_4.setFont(new Font("Garamond", 1, 20));
        lblNewLabel_4.setOpaque(true);
        lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pSuper.add(lblNewLabel_4);
        this.txtHora = new JTextField();
        this.txtHora.setBackground(new Color(245, 245, 245));
        this.txtHora.setForeground(new Color(128, 0, 0));
        this.txtHora.setFont(new Font("Garamond", 0, 20));
        this.txtHora.setName("Hora");
        this.txtHora.setColumns(10);
        pSuper.add(this.txtHora);
        JLabel lblNewLabel_5 = new JLabel("Observacion:");
        lblNewLabel_5.setFont(new Font("Garamond", 1, 20));
        lblNewLabel_5.setOpaque(true);
        lblNewLabel_5.setBackground(new Color(211, 211, 211));
        lblNewLabel_5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pSuper.add(lblNewLabel_5);
        this.txtObservacion = new JTextField();
        this.txtObservacion.setForeground(new Color(128, 0, 0));
        this.txtObservacion.setFont(new Font("Garamond", 0, 20));
        this.txtObservacion.setName("Observacion");
        this.txtObservacion.setColumns(10);
        pSuper.add(this.txtObservacion);
        JPanel pCtrl1 = new JPanel();
        pCtrl1.setLayout(new GridLayout(1, 2));
        this.btnBuscar = new JButton();
        this.btnBuscar.setFont(new Font("Garamond", 1, 20));
        this.btnBuscar.setName("Buscar");
        this.btnBuscar.setText("Buscar");
        pCtrl1.add(this.btnBuscar);
        this.btnSave = new JButton();
        this.btnSave.setFont(new Font("Garamond", 1, 20));
        this.btnSave.setName("Guardar");
        this.btnSave.setText("Guardar");
        pCtrl1.add(this.btnSave);
        pSuper.add(pCtrl1);
        JPanel pCtrl2 = new JPanel();
        pCtrl2.setLayout(new GridLayout(1, 2, 0, 0));
        this.btnDel = new JButton();
        this.btnDel.setFont(new Font("Garamond", 1, 20));
        this.btnDel.setName("Borrar");
        this.btnDel.setText("Borrar");
        pCtrl2.add(this.btnDel);
        this.btnClear = new JButton();
        this.btnClear.setFont(new Font("Garamond", 1, 20));
        this.btnClear.setName("Clear");
        this.btnClear.setText("Limpiar");
        pCtrl2.add(this.btnClear);
        pSuper.add(pCtrl2);
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "Center");
        this.tabla = new JTable();
        this.tabla.setSelectionMode(0);
        this.tabla.setFont(new Font("Arial Narrow", 0, 10));
        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("ID");
        this.modelo.addColumn("Cita");
        this.modelo.addColumn("Fecha");
        this.modelo.addColumn("Hora");
        this.modelo.addColumn("Observacion");
        this.tabla.setModel(this.modelo);
        this.tabla.getTableHeader().setFont(new Font("Arial Narrow", 0, 10));
        this.tabla.getTableHeader().setForeground(new Color(128, 0, 0));
        this.tabla.getTableHeader().setBackground(new Color(245, 245, 245));
        this.tabla.getColumnModel().getColumn(0).setPreferredWidth(35);
        this.tabla.getColumnModel().getColumn(0).setResizable(false);
        this.tabla.getColumnModel().getColumn(1).setPreferredWidth(204);
        this.tabla.getColumnModel().getColumn(1).setResizable(false);
        this.tabla.getColumnModel().getColumn(2).setPreferredWidth(226);
        this.tabla.getColumnModel().getColumn(2).setResizable(false);
        this.tabla.getColumnModel().getColumn(3).setPreferredWidth(220);
        this.tabla.getColumnModel().getColumn(4).setResizable(false);
        this.tabla.getColumnModel().getColumn(4).setPreferredWidth(45);
        this.tabla.setName("Tabla");
        this.DonLimpio();
        scrollPane.setViewportView(this.tabla);
        this.tabla.addMouseListener(new 2(this));
        this.btnClear.addActionListener(new 3(this));
        this.btnBuscar.addActionListener(new 4(this));
        this.btnSave.addActionListener(new 5(this));
        this.btnDel.addActionListener(new 6(this));
    }
}
