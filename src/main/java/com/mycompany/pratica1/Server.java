/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Server {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    
    public void RegisterAccount(String username, String authCode) {
        // SCRYPT sobre o auth code (?)
        accounts.add(new Account(username, authCode));
    }
    
    private Account GetByUsername(String username){
        for (var account : this.accounts) {
            if (account.username == username) {
                return account;
            }
        }
        return null;
    }
    
    public boolean AuthenticateUser(String username, String authCode) {
        // SCRYPT sobre o auth code (?)
        
        var account = GetByUsername(username);
        return account != null ? account.authCode == authCode : false;
    }
    
    public String GetData(String username, String authCode) {
        if (AuthenticateUser(username, authCode)) {
            return GetByUsername(username).encryptedData;
        }
        
        return "Validação falhou";
    }
    
    public void SaveData(String username, String authCode, Data data) {
        if (AuthenticateUser(username, authCode)) {
            this.accounts.remove(GetByUsername(username));
            
            String serializedData = "data"; // serializar e criptografar data
            var updatedAccount = new Account(username, authCode, serializedData);
            
            this.accounts.add(updatedAccount);
        }
    }
    
}
