/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.srm.test.encryption;

import com.srm.swings.encription.SecureEncryption;
import java.security.KeyStore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author umprasad
 */
public class Encryption {
   
    private SecureEncryption secureEncryption=null;
    
    private final static Logger  LOGGER=LoggerFactory.getLogger(Encryption.class);
    
    @Test
    public void encrytionTest() throws Exception{
        String key = "srnet@123456";
        secureEncryption=new SecureEncryption();
        //secureEncryption.fixKeyLength();
        String encrypted=secureEncryption.encrypt(key);
        LOGGER.info("Encryption{}"+encrypted);
        String decrypted=secureEncryption.decrypt(encrypted);
        LOGGER.info("Encryption{}"+decrypted);
    }
}
