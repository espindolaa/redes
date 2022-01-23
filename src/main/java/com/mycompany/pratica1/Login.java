/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    public String url;
    private String username;
    private String password;

    public Login(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" + "url=" + url + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
