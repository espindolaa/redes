/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import com.mycompany.pratica1.security.HashexBCFIPS;
import com.mycompany.pratica1.security.PBKDF2UtilBCFIPS;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Client {
    public String name;
    public byte[] authToken;
    public Data data;

    public Client(String name, String password) {
        var derivedPassword = "";
        byte[] token = null;
        
        try {
            derivedPassword = PBKDF2UtilBCFIPS.getDerivedKey(password);
            token = HashexBCFIPS.calculateHash(derivedPassword);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.name = name;
        this.authToken = token;
        this.data = new Data();
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

