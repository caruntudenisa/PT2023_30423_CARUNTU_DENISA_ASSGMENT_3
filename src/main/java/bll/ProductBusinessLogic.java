package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
/**
 * The ProductBusinessLogic class represents the business logic for managing products.
 * It interacts with the ProductDAO class to perform operations related to products.
 */

public class ProductBusinessLogic {
    private ProductDAO productDAO;
    /**
     * Constructs a new instance of the ProductBusinessLogic class and initializes the ProductDAO.
     */
    public ProductBusinessLogic(){
        productDAO = new ProductDAO();
    }
    /**
     * Finds a product by its ID.
     *
     * @param idProduct the ID of the product to find
     * @return the Product object representing the found product
     * @throws Exception if the product is not found
     */
    public Product findProduct(int idProduct) throws Exception {
        Product product =productDAO.findById(idProduct);
        if(product == null){
            throw new Exception("Product not found");
        }
        return product;
    }
    /**
     * Edits an existing product.
     *
     * @param product the Product object representing the product to be edited
     * @return true if the product is successfully edited, false otherwise
     */
    public boolean editProduct(Product product){
        Product aux = productDAO.update(product);
        if(aux==null){
            return false;
        }
        return true;
    }
    /**
     * Retrieves a list of all products.
     *
     * @return a List of Product objects representing the products
     */
    public List<Product> viewProducts(){
        List<Product> myProducts = productDAO.findAll();
        return myProducts;
    }
    /**
     * Adds a new product to the system.
     *
     * @param product the Product object representing the product to be added
     * @return true if the product is successfully added, false otherwise
     * @throws Exception if an error occurs while adding the product
     */
    public boolean addProduct(Product product) throws Exception {
        if(productDAO.insert(product)==false){
            throw new Exception("Product not added");
        }
        return true;
    }
    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return true if the product is successfully deleted, false otherwise
     */
    public boolean deleteProduct(int id){
        return productDAO.delete(id);
    }

}
