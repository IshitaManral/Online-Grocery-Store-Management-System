import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderConfirmationPage extends JFrame {
    public OrderConfirmationPage() {
        setTitle("Order Confirmation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Order Confirmed!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel messageLabel = new JLabel("Thank you for your purchase.");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(messageLabel);

        JButton homeButton = new JButton("Back to Products");
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProductsPage().setVisible(true);
                dispose();
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(homeButton);

        add(panel);
    }
}