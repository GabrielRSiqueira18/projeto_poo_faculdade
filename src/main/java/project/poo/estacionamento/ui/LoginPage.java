package project.poo.estacionamento.ui;

import project.poo.estacionamento.infra.services.dto.AuthUserDto;

import javax.swing.*;
import java.awt.*;

import static project.poo.estacionamento.Main.userServices;

public class LoginPage extends JFrame {
  private JTextField loginPassword;
  private JButton submit;
  private JPanel loginPanel;
  private JTextField loginEmail;
  private JLabel logo;
  private JLabel loginTitle;
  private JPanel formContainer;

  public LoginPage() {
    JFrame frame = new JFrame("Login");
    setIconImage(new ImageIcon(getClass().getResource("/logo.png")).getImage());
    setTitle("Login");
    setContentPane(loginPanel);
    setMinimumSize(new Dimension(500, 420));

    setLocationRelativeTo(frame);
    loginEmail.setPreferredSize(new Dimension(300, 30));
    loginPassword.setPreferredSize(new Dimension(300, 30));
    submit.setPreferredSize(new Dimension(300, 30));
    submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    logo.setIcon(new ImageIcon(getClass().getResource("/icons/carro.png")));
//    formContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(true);

    submit.addActionListener(e -> {
      String email = loginEmail.getText();
      String password = loginPassword.getText();

      try {
        userServices.auth(new AuthUserDto(email, password));
        JOptionPane.showMessageDialog(frame, "Logged in successfully!");
        dispose();
        SwingUtilities.invokeLater(() -> {
          HomesPage homeFrame = new HomesPage();
          homeFrame.setVisible(true);
        });
      } catch (RuntimeException ex) {
        JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

}
