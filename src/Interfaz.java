import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import java.time.Duration;
import java.time.LocalTime;

class Interfaz {
    public static void main(String[] args) throws Exception {

        JFrame ventana = new JFrame("CONTROL PARQUEOS INTELIGENTE");
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 15));


        JLabel etiqueta = new JLabel("SISTEMA PAQUEO PLAZA COMERCIAL", SwingConstants.CENTER);

        JButton RegistroEntrada = new JButton("Registro Entrada Vehiculo");
        JButton PagoParqueo = new JButton("Pago Ticket");
        JButton PanelGestion = new JButton("Panel de gestión");

        panel.add(RegistroEntrada);
        panel.add(PagoParqueo);
        panel.add(PanelGestion);

        RegistroEntrada.addActionListener(e -> new InterfazRegistro());
        PagoParqueo.addActionListener(e -> new InterfazPago());
        PanelGestion.addActionListener(e -> new InterfazPanelGestion());

        ventana.add(panel);
        ventana.setVisible(true);

    }
}
