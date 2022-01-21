/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Lucas
 */
public class Client {
    public String name;
    public String authToken;
    public Data data;

    public Client(String name, String password) {
        var derivedPassword = "pbkdf" + password;// função de derivação de senha usando o alice.password
        var token = "hkdf" + derivedPassword;// função de hash sobre a derivedPassword
        
        this.name = name;
        this.authToken = token;
    }
    
    public void saveLogin(String url, String username, String password) {
        this.data.logins.add(new Login(url, username, password));
    }
    
    public void removeLogin(String url) {
        this.data.logins.removeIf(login -> login.url == url);
    }
    
    public void saveFavorite(String url) {
        this.data.favorites.add(url);
    }
    
    public void removeFavorite(String url) {
        this.data.favorites.remove(url);
    }
}

