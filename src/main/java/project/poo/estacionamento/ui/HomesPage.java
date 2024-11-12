package project.poo.estacionamento.ui;

import project.poo.estacionamento.ui.components.Header;
import project.poo.estacionamento.ui.components.Nav;
import project.poo.estacionamento.ui.layouts.ChoiceParkingPanel;
import project.poo.estacionamento.ui.layouts.ParkingPanel;

import javax.swing.*;
import java.awt.*;

public class HomesPage extends JFrame {

  public HomesPage() {
    setTitle("Home");
    setSize(768, 500);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setLayout(new BorderLayout());

    add(new Header(), BorderLayout.NORTH);

    Nav navPanel = new Nav();
    navPanel.setPreferredSize(new Dimension(250, getHeight()));

    add(navPanel, BorderLayout.WEST);

    add(new ParkingPanel(), BorderLayout.CENTER);

  }
}
