import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPage extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterPage() {
        setTitle("Register");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 30);
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 80, 30);
        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 150, 80, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);

        registerButton = new JButton("Register");
        registerButton.setBounds(90, 220, 100, 40);

        backButton = new JButton("Back to Login");
        backButton.setBounds(200, 220, 140, 40);

        add(userLabel);
        add(usernameField);
        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passwordField);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage().setVisible(true);
                dispose();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Pass*Word231");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // Storing password directly (not recommended for production)
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginPage().setVisible(true);
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterPage().setVisible(true));
    }
}
