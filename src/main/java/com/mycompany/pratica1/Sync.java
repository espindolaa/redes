/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

/**
 *
 * @author Lucas
 */
public class Sync {
    
    private Server server = new Server();
    
    private void register(Client client) {
        server.RegisterAccount(client.name, client.authToken);
    }
    
    private void saveData(Client client) {
        server.SaveData(client.name, client.authToken, client.data);
    }
    
    private Data getData(Client client) {
        var encrypted = server.GetData(client.name, client.authToken);
        var data = new Data();// decrypt data
        return data;
    }
    
    public void main (String []args) {
        Client alice = new Client("Alice", "mozilla");
        alice.saveLogin("facebook.com", "alice@email.com", "senhasecretas");
        alice.saveFavorite("facebook.com");
        
        register(alice);
        saveData(alice);
        getData(alice);
    }
}
