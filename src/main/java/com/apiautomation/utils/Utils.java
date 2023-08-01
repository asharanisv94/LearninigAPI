package com.apiautomation.utils;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Utils {

	public static void print() {
		System.out.println("API Testing");
	}
	
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length()))); // generates a random integer and an index in the range [0, characters.length()). 
        }

        return randomString.toString();
    }
	
	
}
