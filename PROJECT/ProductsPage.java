import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ProductsPage extends JFrame {
    private JPanel productsPanel;
    private Map<String, String> productImages;
    private Map<String, Double> productPrices;
    private static Map<String, Integer> cart = new HashMap<>();

    public ProductsPage() {
        setTitle("Products");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(5, 2, 10, 10));

        initializeProducts();
        loadProducts();

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton cartButton = new JButton("Go to Cart");
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CartPage(cart).setVisible(true);
                dispose();
            }
        });

        add(cartButton, BorderLayout.SOUTH);
    }

    private void initializeProducts() {
        productImages = new HashMap<>();
        productPrices = new HashMap<>();

        productImages.put("Apple", "apple.jpg");
        productPrices.put("Apple", 50.00);

        productImages.put("Banana", "banana.jpg");
        productPrices.put("Banana", 40.00);

        productImages.put("Bread", "bread.jpg");
        productPrices.put("Bread", 35.00);

        productImages.put("Butter", "butter.jpg");
        productPrices.put("Butter", 120.00);

        productImages.put("Cabbage", "cabbage.jpg");
        productPrices.put("Cabbage", 30.00);

        productImages.put("Cheese", "cheese.jpg");
        productPrices.put("Cheese", 250.00);

        productImages.put("Chicken", "chicken.jpg");
        productPrices.put("Chicken", 300.00);

        productImages.put("Sugar", "Sugar.jpg");
        productPrices.put("Sugar", 150.00);

        productImages.put("Eggs", "eggs.jpg");
        productPrices.put("Eggs", 90.00);

        productImages.put("Fish", "fish.jpg");
        productPrices.put("Fish", 350.00);
    }

    private void loadProducts() {
        for (Map.Entry<String, String> entry : productImages.entrySet()) {
            String productName = entry.getKey();
            String imagePath = entry.getValue();
            double price = productPrices.get(productName);

            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

            ImageIcon productIcon = new ImageIcon(imagePath);
            Image img = productIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel productImage = new JLabel(new ImageIcon(img));

            JLabel nameLabel = new JLabel(productName);
            JLabel priceLabel = new JLabel("Price: ₹" + price);

            JButton incrementButton = new JButton("+");
            JButton decrementButton = new JButton("-");
            JLabel quantityLabel = new JLabel("0");

            incrementButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int currentQuantity = cart.getOrDefault(productName, 0);
                    cart.put(productName, currentQuantity + 1);
                    quantityLabel.setText(String.valueOf(cart.get(productName)));
                }
            });

            decrementButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int currentQuantity = cart.getOrDefault(productName, 0);
                    if (currentQuantity > 0) {
                        cart.put(productName, currentQuantity - 1);
                        quantityLabel.setText(String.valueOf(cart.get(productName)));
                    }
                }
            });

            JPanel quantityPanel = new JPanel();
            quantityPanel.add(decrementButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(incrementButton);

            productPanel.add(productImage);
            productPanel.add(nameLabel);
            productPanel.add(priceLabel);
            productPanel.add(quantityPanel);
            productsPanel.add(productPanel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductsPage().setVisible(true));
    }
}
