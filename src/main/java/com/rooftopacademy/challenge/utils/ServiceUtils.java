package com.rooftopacademy.challenge.utils;

import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServiceUtils {

    /**
     * Function in charge of generating a Hash and returning it as a text string
     */

    public String MD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * Function in charge of processing the text and returning a dictionary
     * with the results
     */

    public Map<String, Integer> subStringJSON(String string, Integer chars){
        Map<String, Integer> dict = new LinkedHashMap<>();
        Integer start = 0;
        Integer count = 0;
        String aux;
        String subString;
        do{
            aux = string;
            subString = aux.substring(start, start+chars);
            while (aux.indexOf(subString)>-1){
                aux = aux.substring(aux.indexOf(subString)
                        +subString.length());
                count++;
            }
            dict.put(subString, count);
            start++;
            count=0;
        }while (start+chars <= string.length());
        return dict;
    }
}
