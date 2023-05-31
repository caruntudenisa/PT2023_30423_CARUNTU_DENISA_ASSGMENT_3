package presentation;

import bll.OrdersBusinessLogic;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrdersWindow extends JFrame {
    /**
     * Constructs a new instance of the OrdersWindow class.
     */
    public OrdersWindow(){
        setTitle("Orders Window");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Create the button panel on the left side
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        panel.add(buttonPanel, BorderLayout.WEST);

        // Create the buttons
        JButton addButton = new JButton("ADD");
        JButton viewAllButton = new JButton("VIEW ALL");

        // Add buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(viewAllButton);

        // Create the input panel on the right side
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        panel.add(inputPanel, BorderLayout.CENTER);

        // Create the labels
        JLabel idClientLabel = new JLabel("ID Client:");
        JLabel idProductLabel = new JLabel("ID Product:");
        JLabel quantityLabel = new JLabel("Quantity:");

        // Create the text fields
        JTextField idClientTextField = new JTextField();
        JTextField idProductTextField = new JTextField();
        JTextField quantityTextField = new JTextField();

        // Add labels and text fields to the input panel
        inputPanel.add(idClientLabel);
        inputPanel.add(idClientTextField);
        inputPanel.add(idProductLabel);
        inputPanel.add(idProductTextField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityTextField);
        // Create an instance of OrdersBusinessLogic
        OrdersBusinessLogic ordersBusinessLogic = new OrdersBusinessLogic();
        // ActionListener for addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orders orders = new Orders(Integer.parseInt(idClientTextField.getText()),Integer.parseInt(idProductTextField.getText()),Integer.parseInt(quantityTextField.getText()));
                try {
                    ordersBusinessLogic.addOrders(orders);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        // ActionListener for viewAllButton
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Orders> ordersList = ordersBusinessLogic.viewOrderss();
                new ViewOrdersList(ordersList);
            }
        });
        setVisible(true);
    }
}
