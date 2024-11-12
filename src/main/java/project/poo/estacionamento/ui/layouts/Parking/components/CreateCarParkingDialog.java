package project.poo.estacionamento.ui.layouts.Parking.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCarParkingDialog extends BaseDialog {
  public CreateCarParkingDialog() {
    super();

    // Define o layout para o painel
    setLayout(new BoxLayout(
      getContentPane(),
      BoxLayout.Y_AXIS
    )); // Layout vertical

    // Criação dos rótulos e campos de entrada
    JLabel modelLabel = new JLabel("Nome Pessoa:");
    JTextField modelField = new JTextField();
    modelField.setPreferredSize(new Dimension(150, 30));

    JLabel licensePlateLabel = new JLabel("Placa do Carro:");
    JTextField licensePlateField = new JTextField();
    licensePlateField.setPreferredSize(new Dimension(50, 30));

    JLabel imageLabel = new JLabel("Imagem do Carro:");
    JButton uploadButton = new JButton("Escolher Imagem");

    // Configura o painel para a imagem (aqui você pode adicionar uma visualização da imagem, se necessário)
    JPanel imagePanel = new JPanel();
    imagePanel.add(uploadButton);

    // Criação dos botões de confirmar e fechar
    JButton confirmButton = new JButton("Confirmar");
    JButton closeButton = new JButton("Fechar");

    // Adicionando os componentes ao painel
    add(modelLabel);
    add(modelField);
    add(licensePlateLabel);
    add(licensePlateField);
    add(imageLabel);
    add(imagePanel);
    add(confirmButton);
    add(closeButton);

    // Adiciona os ouvintes de eventos
    uploadButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
          // Aqui você pode pegar o arquivo selecionado e realizar o que for necessário, como mostrar a imagem.
          String selectedImagePath = fileChooser.getSelectedFile()
            .getAbsolutePath();
          // Você pode armazenar o caminho da imagem ou exibir a imagem em um JLabel.
          System.out.println("Imagem escolhida: " + selectedImagePath);
        }
      }
    });

    closeButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        dispose(); // Fecha o dialog
      }
    });

    confirmButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        // Aqui você pode adicionar a lógica para confirmar o cadastro
        System.out.println("Nome: " + modelField.getText());
        System.out.println("Placa: " + licensePlateField.getText());
        // Imagem: Aqui você pode pegar a variável com o caminho da imagem ou o próprio objeto de imagem
      }
    });
  }
}