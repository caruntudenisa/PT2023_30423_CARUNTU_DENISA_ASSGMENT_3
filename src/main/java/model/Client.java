package model;
/**
 * The Client class represents a client entity with their information.
 */
public class Client {
    private int id;
    private String name;
    private int age;
    private String email;
    /**
     * Constructs a new instance of the Client class with default values.
     */
    public Client() {
    }
    /**
     * Constructs a new instance of the Client class with the specified id, name, age, and email.
     *
     * @param id    the unique identifier of the client
     * @param name  the name of the client
     * @param age   the age of the client
     * @param email the email address of the client
     */

    public Client(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    /**
     * Constructs a new instance of the Client class with the specified name, age, and email.
     *
     * @param name  the name of the client
     * @param age   the age of the client
     * @param email the email address of the client
     */

    public Client(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    /**
     * Retrieves the id of the client.
     *
     * @return the id of the client
     */

    public int getId() {
        return id;
    }
    /**
     * Sets the id of the client.
     *
     * @param id the id of the client
     */

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the name of the client.
     *
     * @return the name of the client
     */

    public String getName() {
        return name;
    }
    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retrieves the age of the client.
     *
     * @return the age of the client
     */

    public int getAge() {
        return age;
    }
    /**
     * Sets the age of the client.
     *
     * @param age the age of the client
     */

    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Retrieves the email address of the client.
     *
     * @return the email address of the client
     */

    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the client.
     *
     * @param email the email address of the client
     */

    public void setEmail(String email) {
        this.email = email;
    }
}
