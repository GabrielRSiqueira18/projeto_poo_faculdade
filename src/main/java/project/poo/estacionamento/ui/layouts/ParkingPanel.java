package project.poo.estacionamento.ui.layouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingPanel extends JPanel {
  public static int parkingZoneNumber = 1;

  private ChoiceParkingPanel[] choicesParkingPanel = new ChoiceParkingPanel[5];
  private int currentIndex = 0; // Índice do painel atualmente exibido
  private JPanel contentPanel; // Painel para exibir o ChoiceParkingPanel atual

  public ParkingPanel() {
    setLayout(new BorderLayout());

    // Inicializa os painéis de escolha e define tamanho preferido
    for (int i = 0; i < choicesParkingPanel.length; i++) {
      ChoiceParkingPanel panel = new ChoiceParkingPanel();
      panel.setPreferredSize(new Dimension((768 - 250), 350));
      choicesParkingPanel[i] = panel;
    }

    // Painel para exibir o ChoiceParkingPanel atual
    contentPanel = new JPanel(new BorderLayout());
    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    add(contentPanel, BorderLayout.CENTER);

    // Painel para os botões na parte inferior
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    JButton previousButton = new JButton("Anterior");
    JButton nextButton = new JButton("Próximo");

    buttonPanel.add(previousButton);
    buttonPanel.add(nextButton);
    add(buttonPanel, BorderLayout.SOUTH);

    // Ação para o botão "Próximo"
    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showNextPanel();
      }
    });

    // Ação para o botão "Anterior"
    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showPreviousPanel();
      }
    });
  }

  // Método para exibir o próximo painel
  private void showNextPanel() {
    // Remove o painel atual
    contentPanel.remove(choicesParkingPanel[currentIndex]);

    // Incrementa o índice e ajusta se for necessário
    currentIndex = (currentIndex + 1) % choicesParkingPanel.length;

    // Adiciona o novo painel e atualiza a interface
    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
  }

  // Método para exibir o painel anterior
  private void showPreviousPanel() {
    // Remove o painel atual
    contentPanel.remove(choicesParkingPanel[currentIndex]);

    // Decrementa o índice e ajusta se for necessário
    currentIndex = (currentIndex - 1 + choicesParkingPanel.length) % choicesParkingPanel.length;

    // Adiciona o novo painel e atualiza a interface
    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
  }
}
