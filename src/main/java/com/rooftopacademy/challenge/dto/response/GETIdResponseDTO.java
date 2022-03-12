package com.rooftopacademy.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Devuelve objeto vacío en lugar de null
public class GETIdResponseDTO {
    private Long id;
    private String hash;
    private Integer chars;
    private Map<String, Integer> result;
}
