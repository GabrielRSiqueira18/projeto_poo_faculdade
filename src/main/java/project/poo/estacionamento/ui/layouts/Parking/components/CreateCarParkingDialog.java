package project.poo.estacionamento.ui.layouts.Parking.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CreateCarParkingDialog extends BaseDialog {
  private final JTextField modelField;
  private final JTextField licensePlateField;
  private final JSpinner   timeSpinner;
  private final JLabel     amountLabel;
  private final double     hourlyRate = 10.0; // Valor por hora

  public CreateCarParkingDialog() {
    super();

    // Define o layout principal como GridBagLayout para centralização
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    // Painel para o campo "Nome Pessoa"
    JPanel modelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel modelLabel = new JLabel("Nome Pessoa:");
    modelField = new JTextField(10);
    modelPanel.add(modelLabel);
    modelPanel.add(modelField);

    // Painel para o campo "Placa do Carro"
    JPanel licensePlatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel licensePlateLabel = new JLabel("Placa do Carro:");
    licensePlateField = new JTextField(10);
    licensePlatePanel.add(licensePlateLabel);
    licensePlatePanel.add(licensePlateField);

    // Painel para o botão de upload de imagem
    JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel imageLabel = new JLabel("Imagem do Carro:");
    JButton uploadButton = new JButton("Escolher Imagem");
    imagePanel.add(imageLabel);
    imagePanel.add(uploadButton);

    // Painel para a seleção de horário limite
    JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel timeLabel = new JLabel("Horário Limite:");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    Date now = calendar.getTime();

    timeSpinner = new JSpinner(new SpinnerDateModel(
      now,
      null,
      null,
      Calendar.MINUTE
    ));
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(
      timeSpinner,
      "HH:mm"
    );
    timeSpinner.setEditor(timeEditor);
    timeSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        Date selectedTime = (Date) timeSpinner.getValue();
        LocalTime limitTime = selectedTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        // Obtém o horário atual
        LocalTime now = LocalTime.now();

        // Calcula a diferença em horas, arredondando para cima se houver minutos adicionais
        long hoursDifference = Duration.between(now, limitTime).toHours();
        if (Duration.between(now, limitTime).toMinutes() % 60 != 0) {
          hoursDifference += 1; // Arredonda para cima se houver minutos adicionais
        }

        // Calcula o valor a pagar
        double amountToPay = (hoursDifference >= 1) ? hoursDifference * hourlyRate : 0.0;

        // Atualiza o texto do `amountLabel` com o valor calculado
        amountLabel.setText(String.format("R$%.2f", amountToPay));
      }
    });


    timePanel.add(timeLabel);
    timePanel.add(timeSpinner);

    // Painel para o valor calculado
    JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel amountTextLabel = new JLabel("Valor a Pagar:");
    amountLabel = new JLabel("R$0.00");
    amountPanel.add(amountTextLabel);
    amountPanel.add(amountLabel);

    // Painel para os botões de ação
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton confirmButton = new JButton("Confirmar");
    JButton closeButton = new JButton("Fechar");
    buttonPanel.add(confirmButton);
    buttonPanel.add(closeButton);

    // Adicionando os painéis ao diálogo de forma centralizada
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(modelPanel, gbc);

    gbc.gridy++;
    add(licensePlatePanel, gbc);

    gbc.gridy++;
    add(imagePanel, gbc);

    gbc.gridy++;
    add(timePanel, gbc);

    gbc.gridy++;
    add(amountPanel, gbc);

    gbc.gridy++;
    add(buttonPanel, gbc);

    // Ações dos botões
    uploadButton.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION) {
        String selectedImagePath = fileChooser.getSelectedFile()
          .getAbsolutePath();
        System.out.println("Imagem escolhida: " + selectedImagePath);
      }
    });

    closeButton.addActionListener(e -> dispose());
    confirmButton.addActionListener(e -> {
      String nome = modelField.getText();
      String placa = licensePlateField.getText();
      Date selectedTime = (Date) timeSpinner.getValue();
      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
      String formattedTime = timeFormat.format(selectedTime);
      int hour = Integer.valueOf(formattedTime.substring(0, 2));

      if (hour < LocalDateTime.now().getHour()) {
        JOptionPane.showMessageDialog(
          null,
          "Não é possível estacionar no passado 😊",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );

        amountLabel.setText(String.format("R$%.2f", 0f));
        return;
      }

      if (hour >= 22 || hour < 6) {
        JOptionPane.showMessageDialog(
          this,
          "Horá deve ser entre 6 e 22",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
        amountLabel.setText(String.format("R$%.2f", 0f));
        return;
      }

      System.out.println("Nome: " + nome);
      System.out.println("Placa: " + placa);
      System.out.println("Horário Limite: " +
                         formattedTime); // Exibe apenas a hora
      System.out.println("Valor a Pagar: " + amountLabel.getText());
    });

    // Centraliza o diálogo na tela
    setLocationRelativeTo(null);
    pack(); // Ajusta o tamanho do diálogo aos componentes
  }
}
