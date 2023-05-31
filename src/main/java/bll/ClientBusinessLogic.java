package bll;

import dao.ClientDAO;
import model.Client;

import java.util.List;
/**
 * Business logic class for handling client operations.
 */
public class ClientBusinessLogic {
    private ClientDAO clientDAO;
    /**
     * Constructs a new instance of the ClientBusinessLogic class.
     */
    public ClientBusinessLogic(){
        clientDAO = new ClientDAO();
    }
    /**
     * Finds a client by their ID.
     *
     * @param idClient the ID of the client to find
     * @return the found client
     * @throws Exception if the client is not found
     */
    public Client findClient(int idClient) throws Exception {
        Client client = clientDAO.findById(idClient);
        if(client == null){
            throw new Exception("Client not found");
        }
        return client;
    }
    /**
     * Edits an existing client.
     *
     * @param client the client to edit
     * @return true if the client was successfully edited, false otherwise
     */
    public boolean editClient(Client client){
        Client aux = clientDAO.update(client);
        if(aux==null){
            return false;
        }
        return true;
    }
    /**
     * Retrieves a list of all clients.
     *
     * @return the list of clients
     */
    public List<Client> viewClients(){
        List<Client> myClients = clientDAO.findAll();
        return myClients;
    }
    /**
     * Adds a new client.
     *
     * @param client the client to add
     * @return true if the client was successfully added
     * @throws Exception if the client was not added
     */
    public boolean addClient(Client client) throws Exception {
        if(clientDAO.insert(client)==false){
            throw new Exception("Client not added");
        }
        return true;
    }
    /**
     * Deletes a client by their ID.
     *
     * @param id the ID of the client to delete
     * @return true if the client was successfully deleted
     */
    public boolean deleteClient(int id){
        return clientDAO.delete(id);
    }

}
