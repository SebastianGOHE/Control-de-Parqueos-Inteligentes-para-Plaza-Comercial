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

public class Interfaz {
    public static void main(String[] args) throws Exception {

        JFrame ventana = new JFrame("CONTROL PARQUEOS INTELIGENTE");
        ventana.setSize(500, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10,15));


        JLabel etiqueta = new JLabel("REGISTRO DE VEHICULO", SwingConstants.CENTER);

        JRadioButton rbAuto = new JRadioButton("AUTO", true);
        JRadioButton rbMoto = new JRadioButton("MOTO");

        ButtonGroup grupoVehiculo = new ButtonGroup();
        grupoVehiculo.add(rbAuto);
        grupoVehiculo.add(rbMoto);

        //JLabel lbltipo = new JPanel(new FlowLayout(FlowLayout.LEFT , 20, 5));
        panel.add(rbAuto);
        panel.add(rbMoto);

        JLabel HORAS = new JLabel("CANTIDAD DE HORAS: ");
        JTextField campoHoras = new JTextField();

        panel.add(HORAS);
        panel.add(campoHoras);

        JLabel lblTotal = new JLabel("TOTAL A PAGAR: RD$ ");
        panel.add(lblTotal);
        

        JButton Calcular = new JButton("CALCULAR");
        JButton Limpiar = new JButton("LIMPIAR");

        JLabel tot = new JLabel("");
        panel.add(tot);

        panel.add(Calcular);
        panel.add(Limpiar);

        Calcular.addActionListener(e -> {

            try{
                int horas = Integer.parseInt(campoHoras.getText().trim());

                if(horas <= 0){
                    JOptionPane.showMessageDialog(ventana, "Las horas deben ser mayores a 0.");
                    return;
                }

                double tarifa;

                if(rbAuto.isSelected()){
                    tarifa = 50;
                } else {
                    tarifa = 25;
                }

                double total = horas * tarifa;
                lblTotal.setText("TOTAL A PAGAR: RD$ " + total);

            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(ventana, "ESCRIBA UN NUMERO VALIDO EN HORAS!");  
                campoHoras.requestFocus();
            }
        });

        Limpiar.addActionListener(e -> {
            double total = 0;
            lblTotal.setText("TOTAL A PAGAR: RD$ " + total);
        });


        ventana.add(panel);
        ventana.setVisible(true);

    }
}
