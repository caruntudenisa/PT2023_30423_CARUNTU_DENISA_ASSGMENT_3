package presentation;

import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewClientsList extends JFrame {
    /**
     * Constructs a new instance of the ViewClientsList class.
     *
     * @param clientList the list of clients to display in the table
     */
    public ViewClientsList(List<Client> clientList) {
            JTable table;
            setTitle("View Clients List");
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Create the table model with column names
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Age");
            tableModel.addColumn("Email");

            // Add client data to the table model
            for (Client client : clientList) {
                Object[] rowData = new Object[4];
                rowData[0] = client.getId();
                rowData[1] = client.getName();
                rowData[2] = client.getAge();
                rowData[3] = client.getEmail();
                tableModel.addRow(rowData);
            }

            // Create the table with the table model
            table = new JTable(tableModel);

            // Set table properties
            table.setPreferredScrollableViewportSize(new Dimension(400, 300));
            table.setFillsViewportHeight(true);

            // Create scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            getContentPane().add(scrollPane);

            pack();
            setVisible(true);
    }
}
