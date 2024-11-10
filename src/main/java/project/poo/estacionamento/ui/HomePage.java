package project.poo.estacionamento.ui;

import project.poo.estacionamento.infra.database.models.CarModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static project.poo.estacionamento.Main.carParkingServices;
import static project.poo.estacionamento.Main.carServices;

public class HomePage extends JFrame {
  private JLabel welcomeLabel = new JLabel("Bem vindo!");
  private JLabel dateLabel = new JLabel("");
  private JLabel carsRegisteredLabel = new JLabel("Carros cadastrados:");
  private JLabel carsParkedLabel = new JLabel("Carros estacionados:");

  private JButton registerCarButton = new JButton("Cadastrar Carro");
  private JButton viewCarButton = new JButton("Ver Carro");
  private JButton updateCarButton = new JButton("Atualizar Carro");
  private JButton removeCarButton = new JButton("Remover Carro");
  private JButton fineCarButton = new JButton("Multar Carro");
  private JButton viewFinedCarsButton = new JButton("Ver Carros Multados");

  private JPanel rightPanel = new JPanel();

  public HomePage() {
    setTitle("Exemplo de Grid Layout");
    setSize(768, 500);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    // Painel esquerdo
    JPanel leftPanel = new JPanel();
    leftPanel.setBackground(new Color(255, 140, 0));
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setPreferredSize(new Dimension(150, getHeight()));

    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    carsRegisteredLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    carsParkedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    leftPanel.add(welcomeLabel);
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(dateLabel);
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(carsRegisteredLabel);
    leftPanel.add(Box.createVerticalStrut(10));
    leftPanel.add(carsParkedLabel);
    leftPanel.add(Box.createVerticalStrut(20));

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
    buttonsPanel.setOpaque(false);

    for (JButton button : new JButton[]{registerCarButton, viewCarButton, updateCarButton, removeCarButton, fineCarButton, viewFinedCarsButton}) {
      button.setBackground(new Color(255, 165, 0));
      button.setOpaque(true);
      button.setBorderPainted(false);
      button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      button.setAlignmentX(Component.CENTER_ALIGNMENT);
      button.setPreferredSize(new Dimension(150, 40));
      button.setFont(new Font("Arial", Font.PLAIN, 14));
      buttonsPanel.add(button);
      buttonsPanel.add(Box.createVerticalStrut(15));
    }

    leftPanel.add(buttonsPanel);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.insets = new Insets(0, 0, 0, 0);
    add(leftPanel, gbc);
    leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // Painel direito
    rightPanel.setBackground(new Color(0, 0, 139));
    rightPanel.setLayout(new CardLayout());

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    add(rightPanel, gbc);

    JPanel footerPanel = new JPanel();
    footerPanel.setBackground(Color.LIGHT_GRAY);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 1;
    gbc.gridwidth = 2;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    add(footerPanel, gbc);

    updateDateNowText();
    updateCarsText();
    updateCarsParkedText();

    registerCarButton.addActionListener(e -> showPanel("Cadastrar Carro"));
    viewCarButton.addActionListener(e -> {
      updateCarsText();
      showPanel("Visualizar Carro");
    });
    updateCarButton.addActionListener(e -> showPanel("Atualizar Carro"));
    removeCarButton.addActionListener(e -> showPanel("Remover Carro"));
    fineCarButton.addActionListener(e -> showPanel("Multar Carro"));
    viewFinedCarsButton.addActionListener(e -> showPanel("Visualizar Carros Multados"));
  }

  private JPanel createPanel(String panelName) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(5, 5, 5, 5);

    switch (panelName) {
      case "Cadastrar Carro" -> {
        panel.add(new JLabel("Modelo:"), gbc);
        JTextField modelField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(modelField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Placa:"), gbc);
        JTextField plateField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(plateField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(255, 165, 0));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(registerButton, gbc);
        registerButton.addActionListener(e -> {

          String model = modelField.getText();
          String plate = plateField.getText();

          if (plate.length() != 6) {
            JOptionPane.showMessageDialog(panel, "Não foi possível cadastrar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }

          try {
            carServices.create(new CarModel(null, plate, model));
            updateCarsText();
            JOptionPane.showMessageDialog(panel, "Carro cadastrado com sucesso: " + plate);
            showPanel("Visualizar Carro");
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Não foi possível cadastrar", "Error", JOptionPane.ERROR_MESSAGE);
          }
        });
      }
      case "Visualizar Carro" -> {
        panel.removeAll();

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Modelo", "Placa"}, 0);
        JTable table = new JTable(tableModel);

        List<CarModel> cars = carServices.findMany();
        for (CarModel car : cars) {
          tableModel.addRow(new Object[]{car.getModel(), car.getLicensePlate()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        panel.revalidate();
        panel.repaint();
      }
    }
    return panel;
  }

  private void showPanel(String panelName) {
    CardLayout cardLayout = (CardLayout) rightPanel.getLayout();
    JPanel newPanel = createPanel(panelName);
    newPanel.setName(panelName);
    rightPanel.removeAll();
    rightPanel.add(newPanel, panelName);
    cardLayout.show(rightPanel, panelName);
  }

  private void updateDateNowText() {
    new Thread(() -> {
      while (true) {
        try {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          String currentDate = sdf.format(new Date());
          SwingUtilities.invokeLater(() -> dateLabel.setText("Data e hora atual: " + currentDate));
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private void updateCarsText() {
    int carQuantity = carServices.findMany().size();
    carsRegisteredLabel.setText("Carros cadastrados: " + carQuantity);
  }

  private void updateCarsParkedText() {
    int carsParkedQuantity = carParkingServices.findMany().size();
    carsParkedLabel.setText("Carros estacionados: " + carsParkedQuantity);
  }
}
