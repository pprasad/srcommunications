/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.swings.encription;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author umprasad
 */
@Component
public class SecureEncryption {
    private final static Logger  LOGGER=LoggerFactory.getLogger(SecureEncryption.class);
    private static final String ALGO = "AES";
    private static final String keyStr ="srcommunications";
    private Key generateKey() throws Exception {
        byte[] keyValue = keyStr.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keyValue = sha.digest(keyValue);
        keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit       
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    public String encrypt(String Data) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
            return encryptedValue;
    }
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);       
        byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
}
