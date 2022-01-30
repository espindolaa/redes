/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import com.mycompany.pratica1.security.ScryptExample;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lucas
 */
public class Server {
    private ArrayList<Account> accounts = new ArrayList<Account>();
    
    public void RegisterAccount(String username, byte[] authCode) {
        var code = "";
        try {
        code = new String(authCode, "UTF-8");
        } catch(Exception ex) {
            
        }
        var hashedAuthCode = hashAuthCode(code).getBytes();
        var account = new Account(username, hashedAuthCode);
        this.accounts.add(account);
    }
    
    private Account GetByUsername(String username){
        System.out.println(this.accounts);
        System.out.println(username);
        for (var account : this.accounts) {
            System.out.println(account.username);
            if (account.username == null ? username == null : account.username.equals(username)) {
                System.out.println("ACHOU");
                return account;
            }
        }
        return null;
    }
    
    private String hashAuthCode(String authCode) {
        String hashedAuthCode = "";
        try {
        hashedAuthCode = ScryptExample.hash(authCode);
        } catch(NoSuchAlgorithmException ex) {
         System.out.println(ex);
        }
        return hashedAuthCode;
    }
    
    public boolean AuthenticateUser(String username, byte[] authCode) {
        var code = "";
        try {
        code = new String(authCode, "UTF-8");
        } catch(Exception ex) {
            
        }
        var hashedAuthCode = hashAuthCode(code).getBytes();
        
        var account = GetByUsername(username);
        System.out.println(account.authCode);
        System.out.println(hashedAuthCode);
        return Arrays.equals(account.authCode, hashedAuthCode);
    }
    
    public Data GetData(String username, byte[] authCode) {
        if (AuthenticateUser(username, authCode)) {
            return readFromFile(GetByUsername(username).encryptedData);
        }
        
        return null;
    }
    
    public void SaveData(String username, byte[] authCode, Data data) {
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
