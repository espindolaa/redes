/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pratica1;

import java.util.HashSet;

/**
 *
 * @author Lucas
 */
public class Data {
    HashSet<Login> logins;
    HashSet<String> favorites;

    public Data() {
        this.logins = new HashSet<Login>();
        this.favorites = new HashSet<String>();
    }

    @Override
    public String toString() {
        return "Data{" + "logins=" + logins + ", favorites=" + favorites + '}';
    }
    
    
    
}
