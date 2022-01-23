/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    
    public Data GetData(String username, String authCode) {
        if (AuthenticateUser(username, authCode)) {
            return readFromFile(GetByUsername(username).encryptedData);
        }
        
        return null;
    }
    
    public void SaveData(String username, String authCode, Data data) {
        if (AuthenticateUser(username, authCode)) {
            this.accounts.remove(GetByUsername(username));
            
            var location = saveToFile(username, data);
            var updatedAccount = new Account(username, authCode, location);
            
            this.accounts.add(updatedAccount);
        }
    }
    
    private String saveToFile(String username, Data data) {
      String location = "";
      try
      {
         location = username+".ser";
         FileOutputStream fileOut = new FileOutputStream(location);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(data);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in " + location);
      }catch(IOException i)
      {
         System.out.println(i);
      }
      return location;
    }
    
    private Data readFromFile(String location) {
      Data d = null;
      try
      {
         FileInputStream fileIn = new FileInputStream(location);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         d = (Data) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException | ClassNotFoundException i)
      {
      }
      
      return d;
    }
}
