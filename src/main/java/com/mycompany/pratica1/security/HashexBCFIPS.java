package com.mycompany.pratica1.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.MessageDigest;
import java.util.Scanner;

import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

/**
 *
 * @author Carla Este exemplo mostra o uso da classe Hash da BouncyCastle.
 * Fevereiro 2021.
 */
public class HashexBCFIPS {
    public static byte[] calculateSha3Digest(byte[] data)
            throws GeneralSecurityException {
        MessageDigest hash = MessageDigest.getInstance("SHA3-512", "BCFIPS");
        System.out.println("Tamanho em bytes SHA3-512 = " + hash.getDigestLength());
        return hash.digest(data);
    }

    public static byte[] calculateHash(String calcular) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, GeneralSecurityException {
        // Instanciar um novo Security provider
        int addProvider = Security.addProvider(new BouncyCastleFipsProvider());
                
        byte [] valorHash2 = calculateSha3Digest(calcular.getBytes());
        return valorHash2;
        
    }
}
