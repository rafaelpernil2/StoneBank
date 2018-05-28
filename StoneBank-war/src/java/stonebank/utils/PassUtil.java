/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rafaelpernil
 */
public class PassUtil {
    
    /**Genera el hash de la contraseña que se le pase por parámetro
     * 
     * @param contrasena
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static String generarHash(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest msgdgst = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = msgdgst.digest(contrasena.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);

        }
        return hexString.toString();
    }

    public static boolean contrasenaVacia(String contrasena) throws NoSuchAlgorithmException {
        return PassUtil.generarHash(contrasena).equalsIgnoreCase("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
    }

}