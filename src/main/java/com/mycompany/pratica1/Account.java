/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

/**
 *
 * @author Lucas
 */
public class Account {
    public String username;
    public String authCode;
    public String encryptedData;

    public Account(String username, String authCode) {
        this.username = username;
        this.authCode = authCode;
    }

    Account(String username, String authCode, String data) {
        this.username = username;
        this.authCode = authCode;
        this.encryptedData = data;
    }
    
}
