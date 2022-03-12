package com.rooftopacademy.challenge.utils;

import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.exception.SubStringErrorException;
import com.rooftopacademy.challenge.model.Text;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class with utilities for tests
 */

public class UtilsTest {

    public static Text newText(){
        Text text = new Text();
        text.setId(1L);
        text.setActive(1);
        text.setText("solo se que nada se");
        text.setHash("aba671cbce53235f31a1e2501c38836f");
        text.setChars(2);
        return text;
    }

    /**
     * Function in charge of processing the text and returning a dictionary
     * with the results
     */

    public static Map<String, Integer> subStringJSON(String string, Integer chars){
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

    public static GETIdResponseDTO newResponse(){
        Text text = newText();
        GETIdResponseDTO responseDTO = new GETIdResponseDTO();
        responseDTO.setId(text.getId());
        responseDTO.setHash(text.getHash());
        responseDTO.setChars(text.getChars());
        responseDTO.setResult(subStringJSON(text.getText(),
                text.getChars()));
        return responseDTO;
    }
}
