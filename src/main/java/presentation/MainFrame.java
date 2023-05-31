package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel panel;
    private JButton clientButton;
    private JButton ordersButton;
    private JButton productButton;
    /**
     * Constructs a new instance of the MainFrame class.
     * This class represents the main frame of the application with buttons for different features.
     */
    public MainFrame() {
        panel = new JPanel();
        clientButton = new JButton("Client");
        ordersButton = new JButton("Orders");
        productButton = new JButton("Product");

        panel.add(clientButton);
        panel.add(ordersButton);
        panel.add(productButton);

        add(panel);

        setTitle("Button Window");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        // Add action listeners for the buttons
        /**
         * ActionListener for the clientButton.
         * Opens a new ClientWindow when the button is clicked.
         */
        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientWindow();
            }
        });
        /**
         * ActionListener for the ordersButton.
         * Opens a new OrdersWindow when the button is clicked.
         */

        ordersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrdersWindow();
            }
        });
        /**
         * ActionListener for the productButton.
         * Opens a new ProductWindow when the button is clicked.
         */
        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProductWindow();
            }
        });
    }

}
