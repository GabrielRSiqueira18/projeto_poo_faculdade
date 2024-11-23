package project.poo.estacionamento.ui.layouts.Parking.components;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RemoveCarParkingDialog extends BaseDialog {
  private final JTextField passwordField;
  private final JTextField cpfField;
  private final JButton okButton;

  public RemoveCarParkingDialog(ParkingButton button) {
    super();

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    // Painel para o campo "Senha"
    JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel passwordLabel = new JLabel("Digite sua senha:");
    passwordField = new JTextField(10);
    passwordPanel.add(passwordLabel);
    passwordPanel.add(passwordField);

    // Painel para o campo "CPF"
    JPanel cpfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel cpfLabel = new JLabel("Digite seu CPF:");
    cpfField = new JTextField(10);
    cpfPanel.add(cpfLabel);
    cpfPanel.add(cpfField);

    // Adiciona os painéis de senha e CPF ao layout principal com o uso de gbc
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(passwordPanel, gbc);

    gbc.gridy = 1;
    add(cpfPanel, gbc);

    // Cria e adiciona o QR Code ao layout
    JLabel qrCodeLabel = new JLabel();
    qrCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    BufferedImage qrImage = generateQRCodeImage(button.props.passwordToPay, 100, 100);
    if (qrImage != null) {
      qrCodeLabel.setIcon(new ImageIcon(qrImage));
    } else {
      JOptionPane.showMessageDialog(this, "Erro ao gerar QR Code.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    gbc.gridy = 2;
    add(qrCodeLabel, gbc);

    // Botão centralizado abaixo do QR Code
    okButton = new JButton("Pagar");
    gbc.gridy = 3;
    gbc.gridwidth = 1; // Reseta o gridwidth para evitar sobreposição
    gbc.anchor = GridBagConstraints.CENTER;
    add(okButton, gbc);

    // Ação do botão
    okButton.addActionListener(e -> {
      String enteredCPF = cpfField.getText().trim();
      String enteredPassword = passwordField.getText().trim();

      if (!button.props.cpf.equals(enteredCPF) || !button.props.passwordToPay.equals(enteredPassword)) {
        JOptionPane.showMessageDialog(this, "CPF ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      button.setBackground(Color.gray);
      button.setText("Vaga: " + button.props.id);
      button.setIcon(null);
      button.reset();
      dispose();

    });
  }

  private static BufferedImage generateQRCodeImage(String text, int width, int height) {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    try {
      BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
        }
      }
      return image;
    } catch (WriterException e) {
      e.printStackTrace();
      return null;
    }
  }
}
