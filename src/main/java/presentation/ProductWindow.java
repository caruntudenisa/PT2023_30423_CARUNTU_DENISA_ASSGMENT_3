package presentation;

import bll.ClientBusinessLogic;
import bll.ProductBusinessLogic;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductWindow extends JFrame {
    /**
     * Constructs a new instance of the ProductWindow class.
     */
    public ProductWindow(){
        setTitle("Product Window");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create the main panel
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Create the button panel on the left side
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        panel.add(buttonPanel, BorderLayout.WEST);

        // Create the buttons
        JButton addButton = new JButton("ADD");
        JButton deleteButton = new JButton("DELETE");
        JButton viewAllButton = new JButton("VIEW ALL");
        JButton editButton = new JButton("EDIT");

        // Add buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewAllButton);
        buttonPanel.add(editButton);

        // Create the input panel on the right side
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        panel.add(inputPanel, BorderLayout.CENTER);

        // Create the labels
        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel quantityLabel = new JLabel("Quantity:");

        // Create the text fields
        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField priceTextField = new JTextField();
        JTextField quantityTextField = new JTextField();

        // Add labels and text fields to the input panel
        inputPanel.add(idLabel);
        inputPanel.add(idTextField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceTextField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityTextField);
        // Create an instance of ProductBusinessLogic
        ProductBusinessLogic productBusinessLogic = new ProductBusinessLogic();
        // ActionListener for addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product(nameTextField.getText(),Integer.parseInt(priceTextField.getText()),Integer.parseInt(quantityTextField.getText()));

                try {
                    productBusinessLogic.addProduct(product);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // ActionListener for editButton
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product(Integer.parseInt(idTextField.getText()),nameTextField.getText(),Integer.parseInt(priceTextField.getText()),Integer.parseInt(quantityTextField.getText()));
                productBusinessLogic.editProduct(product);
            }
        });
        // ActionListener for deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productBusinessLogic.deleteProduct(Integer.parseInt(idTextField.getText()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // ActionListener for viewAllButton
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> productList = productBusinessLogic.viewProducts();
                new ViewProductsList(productList);
            }
        });
        setVisible(true);
    }
}
