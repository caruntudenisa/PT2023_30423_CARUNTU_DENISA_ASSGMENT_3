package presentation;

import bll.ClientBusinessLogic;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Represents a window for managing client information.
 */
public class ClientWindow extends JFrame {
    /**
     * Constructs a new instance of the ClientWindow class.
     * Initializes the client management window with buttons and input fields for adding, editing, deleting, and viewing clients.
     */

    public ClientWindow(){
        setTitle("Client Window");
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
        JLabel ageLabel = new JLabel("Age:");
        JLabel emailLabel = new JLabel("Email:");

        // Create the text fields
        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField ageTextField = new JTextField();
        JTextField emailTextField = new JTextField();

        // Add labels and text fields to the input panel
        inputPanel.add(idLabel);
        inputPanel.add(idTextField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageTextField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);
        ClientBusinessLogic clientBusinessLogic = new ClientBusinessLogic();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(nameTextField.getText(),Integer.parseInt(ageTextField.getText()),emailTextField.getText());

                try {
                    clientBusinessLogic.addClient(client);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(Integer.parseInt(idTextField.getText()),nameTextField.getText(),Integer.parseInt(ageTextField.getText()),emailTextField.getText());
                clientBusinessLogic.editClient(client);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientBusinessLogic.deleteClient(Integer.parseInt(idTextField.getText()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Client> clientList = clientBusinessLogic.viewClients();
                new ViewClientsList(clientList);
            }
        });
        setVisible(true);
    }
}
