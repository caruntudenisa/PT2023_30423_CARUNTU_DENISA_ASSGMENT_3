package model;

public class Orders {
    private int id;
    private int id_client;
    private int id_product;
    private int quantity;
    private int total_price;

    /**
     * Constructs a new instance of the Orders class.
     * This is an empty constructor.
     */

    public Orders() {
    }

    /**
     * Constructs a new instance of the Orders class with the specified ID, client ID, product ID, quantity, and total price.
     *
     * @param id          the ID of the order
     * @param id_client   the ID of the client
     * @param id_product  the ID of the product
     * @param quantity    the quantity of the product in the order
     * @param total_price the total price of the order
     */
    public Orders(int id, int id_client, int id_product, int quantity, int total_price) {
        this.id = id;
        this.id_client = id_client;
        this.id_product = id_product;
        this.quantity = quantity;
        this.total_price = total_price;
    }
    /**
     * Constructs a new instance of the Orders class with the specified client ID, product ID, quantity, and total price.
     *
     * @param id_client   the ID of the client
     * @param id_product  the ID of the product
     * @param quantity    the quantity of the product in the order
     * @param total_price the total price of the order
     */
    public Orders(int id_client, int id_product, int quantity, int total_price) {
        this.id_client = id_client;
        this.id_product = id_product;
        this.quantity = quantity;
        this.total_price = total_price;
    }
    /**
     * Constructs a new instance of the Orders class with the specified client ID, product ID, and quantity.
     *
     * @param id_client  the ID of the client
     * @param id_product the ID of the product
     * @param quantity   the quantity of the product in the order
     */
    public Orders(int id_client, int id_product, int quantity) {
        this.id_client = id_client;
        this.id_product = id_product;
        this.quantity = quantity;
    }
    /**
     * Retrieves the ID of the order.
     *
     * @return the ID of the order
     */

    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the order.
     *
     * @param id the ID of the order
     */

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the ID of the client.
     *
     * @return the ID of the client
     */

    public int getId_client() {
        return id_client;
    }
    /**
     * Sets the ID of the client.
     *
     * @param id_client the ID of the client
     */

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    /**
     * Retrieves the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId_product() {
        return id_product;
    }
    /**
     * Sets the ID of the product.
     *
     * @param id_product the ID of the product
     */

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return the quantity of the product in the order
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity the quantity of the product in the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Retrieves the total price of the order.
     *
     * @return the total price of the order
     */
    public int getTotal_price() {
        return total_price;
    }
    /**
     * Sets the total price of the order.
     *
     * @param total_price the total price of the order
     */
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
