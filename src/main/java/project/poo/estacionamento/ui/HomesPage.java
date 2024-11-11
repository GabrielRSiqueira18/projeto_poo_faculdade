package project.poo.estacionamento.ui;

import project.poo.estacionamento.ui.components.Header;
import project.poo.estacionamento.ui.layouts.DefaultPanel;
import project.poo.estacionamento.ui.components.Nav;

import javax.swing.*;
import java.awt.*;

public class HomesPage extends JFrame {

  public HomesPage() {
    setTitle("Home");
    setSize(768, 500);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Layout principal usando BorderLayout
    setLayout(new BorderLayout());

    // Adiciona o Header na parte superior (ocupando toda a largura)
    add(new Header(), BorderLayout.NORTH);

    // Cria o painel Nav (esquerdo)
    Nav navPanel = new Nav();
    navPanel.setPreferredSize(new Dimension(250, getHeight())); // Define a largura do Nav (250px)

    // Adiciona o Nav na parte esquerda com largura fixa
    add(navPanel, BorderLayout.WEST);

    // Adiciona o painel direito (conteúdo principal)
    add(new DefaultPanel(), BorderLayout.CENTER);

  }
}
