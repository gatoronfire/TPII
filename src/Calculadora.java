import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

    private JTextField pantallaPrincipal;
    private JLabel historial; // Para ver con qué estás operando
    private double resultado = 0;
    private String operadorPendiente = "=";
    private boolean nuevaOperacion = true;

    public Calculadora() {
        setTitle("Calculadora Pro");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR (PANTALLAS) ---
        JPanel panelPantallas = new JPanel(new GridLayout(2, 1));

        historial = new JLabel(" ");
        historial.setHorizontalAlignment(SwingConstants.RIGHT);
        historial.setFont(new Font("Monospaced", Font.PLAIN, 16));

        pantallaPrincipal = new JTextField("0");
        pantallaPrincipal.setEditable(false);
        pantallaPrincipal.setHorizontalAlignment(JTextField.RIGHT);
        pantallaPrincipal.setFont(new Font("Monospaced", Font.BOLD, 35));
        pantallaPrincipal.setBorder(null);

        panelPantallas.add(historial);
        panelPantallas.add(pantallaPrincipal);
        add(panelPantallas, BorderLayout.NORTH);

        // --- PANEL DE BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 8, 8));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] etiquetas = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : etiquetas) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.BOLD, 20));

            if ("0123456789".contains(texto)) {
                boton.addActionListener(new ClickNumero());
            } else if (texto.equals("C")) {
                boton.addActionListener(e -> reiniciar());
            } else {
                boton.addActionListener(new ClickOperador());
            }
            panelBotones.add(boton);
        }
        add(panelBotones, BorderLayout.CENTER);
    }

    private class ClickNumero implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numero = e.getActionCommand();
            if (nuevaOperacion) {
                pantallaPrincipal.setText(numero);
                nuevaOperacion = false;
            } else {
                pantallaPrincipal.setText(pantallaPrincipal.getText() + numero);
            }
        }
    }

    private class ClickOperador implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String operacionPulsada = e.getActionCommand();

            try {
                double valorPantalla = Double.parseDouble(pantallaPrincipal.getText());

                if (operadorPendiente.equals("=")) {
                    resultado = valorPantalla;
                } else {
                    ejecutarOperacion(valorPantalla);
                }

                if (operacionPulsada.equals("=")) {
                    historial.setText(" ");
                } else {
                    historial.setText(formatear(resultado) + " " + operacionPulsada);
                }

                operadorPendiente = operacionPulsada;
                nuevaOperacion = true;

            } catch (NumberFormatException ex) {
                reiniciar();
                pantallaPrincipal.setText("Error");
            }
        }
    }

    private void ejecutarOperacion(double valor) {
        switch (operadorPendiente) {
            case "+" -> resultado += valor;
            case "-" -> resultado -= valor;
            case "*" -> resultado *= valor;
            case "/" -> {
                if (valor != 0) resultado /= valor;
                else {
                    reiniciar();
                    pantallaPrincipal.setText("Error: Div/0");
                    return;
                }
            }
        }
        pantallaPrincipal.setText(formatear(resultado));
    }

    private String formatear(double d) {
        if (d % 1 == 0) return String.valueOf((int) d);
        return String.valueOf(d);
    }

    private void reiniciar() {
        pantallaPrincipal.setText("0");
        historial.setText(" ");
        resultado = 0;
        operadorPendiente = "=";
        nuevaOperacion = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora().setVisible(true));
    }
}