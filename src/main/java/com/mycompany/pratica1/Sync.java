/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Sync {
    private static void register(Server server, Client client) {
        server.RegisterAccount(client.name, client.authToken);
    }
    
    private static void saveData(Server server, Client client) {
        server.SaveData(client.name, client.authToken, client.data);
    }
    
    private static Data getData(Server server, Client client) {
        var encrypted = server.GetData(client.name, client.authToken);
        return encrypted;
    }
    
    private static void io(Server server, ArrayList<Client> clients) throws IOException {
        String option, url, name, password = "";
        Client chosen = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client alice = new Client("Alice", "mozilla");
        alice.saveLogin("facebook.com", "alice@email.com", "senhasecretas");
        alice.saveFavorite("facebook.com");
        clients.add(alice);
        menu: while (true) {
            System.out.println("");
            System.out.println("-------------------");
            System.out.println("Escolha uma opção: ");
            System.out.println("""
                               1 - Criar novo cliente
                               2 - Adicionar favorito ao cliente
                               3 - Adicionar login ao cliente
                               4 - Cadastrar conta no servidor
                               5 - Salvar dados no servidor
                               6 - Obter dados do cliente
                               """);
            option = reader.readLine();
            switch (option) {
                case "1":
                    System.out.println("Digite o nome do cliente: ");
                    name = reader.readLine();
                    System.out.println("Digite a senha: ");
                    password = reader.readLine();
                    Client client = new Client(name, password);
                    clients.add(client);
                    System.out.printf("Usuario %s criado", client.name);
                    break;
                case "2":
                    chosen = clients.get(getClient(clients));
                    System.out.println("Digite o endereco do favorito");
                    url = reader.readLine();
                    chosen.saveFavorite(url);
                    System.out.printf("Favorito %s adicionado ao cliente %s", url, chosen.name);
                    break;
                case "3":
                    chosen = clients.get(getClient(clients));
                    System.out.println("Digite o endereco do login");
                    url = reader.readLine();
                    System.out.println("Digite o email");
                    name = reader.readLine();
                    System.out.println("Digite a senha");
                    password = reader.readLine();
                    
                    chosen.saveLogin(url, name, password);
                    System.out.printf("Login de %s adicionado ao cliente %s (%s / %s)", url, chosen.name, name, password);
                    break;
                case "4":
                    chosen = clients.get(getClient(clients));
                    register(server, chosen);
                    System.out.printf("Cliente %s registrado no servidor", chosen.name);
                    break;
                case "5":
                    chosen = clients.get(getClient(clients));
                    saveData(server, chosen);
                    System.out.printf("Dados do cliente %s persistidos no servidor", chosen.name);
                    break;
                case "6":
                    chosen = clients.get(getClient(clients));
                    var data = getData(server, chosen);
                    System.out.printf("Dados do cliente %s: ", chosen.name);
                    System.out.println(data);
                    break;
                default:
                    break menu;
            }
        }
    }
    
    private static int getClient(ArrayList<Client> clients) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escolha um cliente: ");
        showClients(clients);
        var option = reader.readLine();
        return Integer.parseInt(option);
    }
    
    private static void showClients(ArrayList<Client> clients) {
        int index = 0;
        for (var c : clients) {
            System.out.println(index + " - " + c.name);
            index++;
        }
    }
    
    public static void main (String []args) throws IOException {
        Server server = new Server();
        ArrayList<Client> clients = new ArrayList<Client>();
    
        io(server, clients);
    }
}


        //Client alice = new Client("Alice", "mozilla");
        //alice.saveLogin("facebook.com", "alice@email.com", "senhasecretas");
        //alice.saveFavorite("facebook.com");
