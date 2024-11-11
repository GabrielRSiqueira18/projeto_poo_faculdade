package project.poo.estacionamento.ui.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
  public Header() {
    // Definindo a cor de fundo do Header
    setBackground(new Color(255, 140, 0)); // Cor laranja

    // Definindo o layout do painel
    setLayout(new FlowLayout(FlowLayout.CENTER));

    // Criando o JLabel com o texto "Estacionamento"
    JLabel headerLabel = new JLabel("Estacionamento");
    headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Fontes e estilo
    headerLabel.setForeground(Color.WHITE); // Cor do texto

    // Adicionando o JLabel ao painel
    add(headerLabel);
  }
}
