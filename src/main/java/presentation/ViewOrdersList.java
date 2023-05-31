package presentation;

import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewOrdersList extends JFrame {
    /**
     * Constructs a new instance of the ViewOrdersList class.
     *
     * @param ordersList the list of orders to display in the table
     */
    public ViewOrdersList(List<Orders> ordersList) {
        JTable table;
        setTitle("View Orders List");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table model with column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("ID_Client");
        tableModel.addColumn("ID_Product");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Total_Price");

        // Add client data to the table model
        for (Orders orders:ordersList) {
            Object[] rowData = new Object[5];
            rowData[0] = orders.getId();
            rowData[1] = orders.getId_client();
            rowData[2] = orders.getId_product();
            rowData[3] = orders.getQuantity();
            rowData[4] = orders.getTotal_price();
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
