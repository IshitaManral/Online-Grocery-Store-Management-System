import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CheckoutPage extends JFrame {
    private Map<String, Integer> cart;

    public CheckoutPage(Map<String, Integer> cart) {
        this.cart = cart;
        setTitle("Checkout");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            checkoutPanel.add(new JLabel(entry.getKey() + " x " + entry.getValue()));
        }

        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrderConfirmationPage().setVisible(true);
                dispose();
            }
        });

        add(checkoutPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }
}
