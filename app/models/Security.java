package models;

import java.util.*;
import java.security.*;
import java.math.*;

public class Security
{
	public static String encryptPassword(String password) 
    {
        MessageDigest digest;
        try 
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            String secured = new BigInteger(1, digest.digest()).toString(16);
            return secured;
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}