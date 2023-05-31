package model;
/**
 * Represents a product.
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    /**
     * Constructs a new instance of the Product class with the specified ID, name, price, and quantity.
     *
     * @param id       the ID of the product
     * @param name     the name of the product
     * @param price    the price of the product
     * @param quantity the quantity of the product
     */

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    /**
     * Constructs a new instance of the Product class with the specified name, price, and quantity.
     *
     * @param name     the name of the product
     * @param price    the price of the product
     * @param quantity the quantity of the product
     */

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    /**
     * Constructs a new instance of the Product class.
     * This is an empty constructor.
     */

    public Product() {
    }
    /**
     * Retrieves the ID of the product.
     *
     * @return the ID of the product
     */

    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */

    public String getName() {
        return name;
    }
    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */

    public int getPrice() {
        return price;
    }
    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */

    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * Retrieves the quantity of the product.
     *
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Sets the quantity of the product.
     *
     * @param quantity the quantity of the product
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
