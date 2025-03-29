import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CartPage extends JFrame {
    private Map<String, Integer> cart;

    public CartPage(Map<String, Integer> cart) {
        this.cart = cart;
        setTitle("Shopping Cart");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            cartPanel.add(new JLabel(entry.getKey() + " x " + entry.getValue()));
        }

        JButton checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CheckoutPage(cart).setVisible(true);
                dispose();
            }
        });

        add(cartPanel, BorderLayout.CENTER);
        add(checkoutButton, BorderLayout.SOUTH);
    }
}
