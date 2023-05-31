package bll;

import dao.ClientDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;

import java.util.List;
/**
 * The OrdersBusinessLogic class represents the business logic for managing orders.
 * It interacts with the OrdersDAO and ProductDAO classes to perform operations related to orders.
 */
public class OrdersBusinessLogic {
    private OrdersDAO ordersDAO;
    /**
     * Constructs a new instance of the OrdersBusinessLogic class and initializes the OrdersDAO.
     */
    public OrdersBusinessLogic(){
        ordersDAO = new OrdersDAO();
    }
    /**
     * Retrieves a list of all orders.
     *
     * @return a List of Orders objects representing the orders
     */
    public List<Orders> viewOrderss(){
        List<Orders> myOrderss = ordersDAO.findAll();
        return myOrderss;
    }
    /**
     * Adds a new order to the system.
     *
     * @param orders the Orders object representing the order to be added
     * @return true if the order is successfully added, false otherwise
     * @throws Exception if an error occurs while adding the order
     */
    public boolean addOrders(Orders orders) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(orders.getId_product());
        orders.setTotal_price(product.getPrice()*orders.getQuantity());
        if(ordersDAO.insert(orders)==false){
            throw new Exception("Orders not added");
        }
        product.setQuantity(product.getQuantity()-orders.getQuantity());
        productDAO.update(product);
        return true;
    }

}
