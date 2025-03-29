import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        userLabel.setBounds(50, 50, 80, 30);
        usernameField.setBounds(140, 50, 200, 30);
        passLabel.setBounds(50, 100, 80, 30);
        passwordField.setBounds(140, 100, 200, 30);
        loginButton.setBounds(90, 160, 100, 40);
        registerButton.setBounds(200, 160, 100, 40);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterPage().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new ProductsPage().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
