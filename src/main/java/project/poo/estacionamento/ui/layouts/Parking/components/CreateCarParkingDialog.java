package project.poo.estacionamento.ui.layouts.Parking.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CreateCarParkingDialog extends BaseDialog {
  public static boolean    confirmed = false;

  private final JTextField modelField;
  private final JTextField licensePlateField;
  private final JTextField cpfField;
  private final JSpinner   timeSpinner;
  private final JLabel     amountLabel;
  private final double     hourlyRate = 10.0; // Valor por hora
  double amountToPay = 0f;

  public CreateCarParkingDialog(ParkingButton button) {
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

    // Painel para o campo "CPF"
    JPanel cpfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel cpfLabel = new JLabel("CPF:");
    cpfField = new JTextField(10);
    cpfPanel.add(cpfLabel);
    cpfPanel.add(cpfField);

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
      @Override public void stateChanged(ChangeEvent e) {
        Date selectedTime = (Date) timeSpinner.getValue();
        LocalTime limitTime = selectedTime.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalTime();

        // Obtém o horário atual
        LocalTime now = LocalTime.now();

        // Calcula a diferença em horas, arredondando para cima se houver minutos adicionais
        long hoursDifference = Duration.between(now, limitTime).toHours();
        if (Duration.between(now, limitTime).toMinutes() % 60 != 0) {
          hoursDifference
            += 1; // Arredonda para cima se houver minutos adicionais
        }

        // Calcula o valor a pagar
        amountToPay = (hoursDifference >= 1)
          ? hoursDifference * hourlyRate
          : 0.0;

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
    add(cpfPanel, gbc);

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
      String nome = modelField.getText().trim();
      String placa = licensePlateField.getText().trim();
      String cpf = cpfField.getText().trim();
      Date selectedTime = (Date) timeSpinner.getValue();

      // Cria um Calendar para manipulação de horas e minutos
      Calendar currentCalendar = Calendar.getInstance();
      currentCalendar.setTime(new Date()); // Hora atual

      // Cria outro Calendar para o horário selecionado no spinner
      Calendar selectedCalendar = Calendar.getInstance();
      selectedCalendar.setTime(new Date()); // Hora selecionada
      selectedCalendar.set(Calendar.HOUR_OF_DAY, selectedTime.getHours());
      selectedCalendar.set(Calendar.MINUTE, selectedTime.getMinutes());

      int selectedHour = selectedCalendar.get(Calendar.HOUR_OF_DAY);
      int selectedMinute = selectedCalendar.get(Calendar.MINUTE);

      // Verifica se a diferença de minutos é menor que 30
      long minutesDifference = (
                                 selectedCalendar.getTimeInMillis() -
                                 currentCalendar.getTimeInMillis()
                               ) / (60 * 1000); // Diferença em minutos
      if (minutesDifference < 30) {
        JOptionPane.showMessageDialog(
          null,
          "Mínimo 30 minutos, por favor! 😒",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );

        amountLabel.setText(String.format("R$%.2f", 0f));
        return;
      }

      if (selectedHour < currentCalendar.get(Calendar.HOUR_OF_DAY) || (
        selectedHour == currentCalendar.get(Calendar.HOUR_OF_DAY) &&
        selectedMinute < currentCalendar.get(Calendar.MINUTE)
      )) {
        JOptionPane.showMessageDialog(
          this,
          "Não é possível estacionar no passado 😊",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );

        amountLabel.setText(String.format("R$%.2f", 0f));
        return;
      }

      // Verifica se a hora está entre 6h e 22h
      if (selectedHour < 6 || selectedHour >= 22) {
        JOptionPane.showMessageDialog(
          this,
          "A hora deve ser entre 6h e 22h",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
        amountLabel.setText(String.format("R$%.2f", 0f));
        return;
      }

      if (nome.isEmpty() || placa.length() != 7 || (cpf.length() != 11)) {
        JOptionPane.showMessageDialog(
          this,
          "Preencha todos os campos!!! 👌",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
        dispose();
        return;
      }

      // Se passar todas as validações, a ação é confirmada
      button.props.personName = nome;
      button.props.licensePlate = placa;
      button.props.dateStarted = LocalDateTime.now();
      button.props.dateEnded
        = LocalDateTime.ofInstant(
        selectedCalendar.getTime().toInstant(),
        selectedCalendar.getTimeZone().toZoneId()
      );
      button.props.occupied = true;
      button.props.passwordToPay = UUID.randomUUID()
        .toString()
        .substring(0, 4)
        .toLowerCase();
      button.props.priceValue = amountToPay;
      button.setText("");
      button.props.cpf = cpf;
      button.setHorizontalAlignment(SwingConstants.CENTER);
      button.setVerticalAlignment(SwingConstants.CENTER);

      button.setHorizontalTextPosition(SwingConstants.CENTER);
      button.setVerticalTextPosition(SwingConstants.CENTER);
      button.setBackground(new Color(250, 125, 125));

      button.setIcon(new ImageIcon(getClass().getResource("/icons/hatchback.png")));

      dispose();
      JOptionPane.showMessageDialog(
        this,
        "Parabéns estacionou o carro!!" +
        "\nO preço será de: " +
        amountToPay +
        "\nInício: " +
        button.props.getDateFormatted(button.props.dateStarted) +
        "\nFinal: " +
        button.props.getDateFormatted(button.props.dateEnded) +
        "\nSua senha, usar no pagamento: R$" +
        button.props.passwordToPay,
        "Estacionado",
        JOptionPane.INFORMATION_MESSAGE
      );

    });

    // Centraliza o diálogo na tela
    setLocationRelativeTo(null);
    pack(); // Ajusta o tamanho do diálogo aos componentes
  }
}
