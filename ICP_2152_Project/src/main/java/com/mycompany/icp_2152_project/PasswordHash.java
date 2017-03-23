/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icp_2152_project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Dion
 */
public class PasswordHash {
    
    static Map<String, String> DB = new HashMap<>();
    public static final String SALT = "my-salt-text";
    
    public static void main(String[] args) {
        PasswordHash hash = new PasswordHash();
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your details...");
        System.out.println("Username: ");
        String inputA = s.nextLine();
        System.out.println("Password: ");
        String inputB= s.nextLine();
        hash.signUp(inputA, inputB);
        if (hash.login("dion", "jordan")) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Error, username or password incorrect");
        }
        System.out.println(DB.toString());
    }

    public String hashPassword(String password) {
        String tempPassword = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5 - secure one way hash functions
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(tempPassword.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        return generatedPassword;
    }

    public void signUp(String username, String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        DB.put(username, hashedPassword);
    }

    public Boolean login(String username, String password) {
        Boolean connected = false;
        // remember to use the same SALT value use used while storing password for the first time
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        String storedPasswordHash = DB.get(username);
        if (hashedPassword.equals(storedPasswordHash)) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    public String generateHash(String password) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(password.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return hash.toString();
    }
}