package presentation;

import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewProductsList extends JFrame {
    /**
     * Constructs a new instance of the ViewProductsList class.
     *
     * @param productList the list of products to display in the table
     */
    public ViewProductsList(List<Product> productList) {
        JTable table;
        setTitle("View Products List");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table model with column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Quantity");

        // Add client data to the table model
        for (Product product: productList) {
            Object[] rowData = new Object[4];
            rowData[0] = product.getId();
            rowData[1] = product.getName();
            rowData[2] = product.getPrice();
            rowData[3] = product.getQuantity();
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
