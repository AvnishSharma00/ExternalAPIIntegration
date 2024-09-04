package com.Avnish.CovidEndpoint.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Avnish.CovidEndpoint.Properties.BBBProperties;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;




    

    import java.security.MessageDigest;
    import java.security.NoSuchAlgorithmException;
    import java.nio.charset.StandardCharsets;
    import java.util.Formatter;
    @Component
    public class BBBUtils {
    
        public static String generateChecksum(String apiMethod, String queryString, String sharedSecret) {
            try {
                // Concatenate method, query string, and secret
                String toHash = apiMethod + queryString + sharedSecret;
                
                // Create SHA-1 digest
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] digest = md.digest(toHash.getBytes(StandardCharsets.UTF_8));
                
                // Convert to hex
                StringBuilder hexString = new StringBuilder();
                try (Formatter formatter = new Formatter(hexString)) {
                    for (byte b : digest) {
                        formatter.format("%02x", b);
                    }
                }
                
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error generating checksum", e);
            }
        }
    }
    

