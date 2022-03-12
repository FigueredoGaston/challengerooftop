package com.rooftopacademy.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Devuelve objeto vacío en lugar de null
public class POSTOKResponseDTO implements Serializable {

    private Long id;
    private String url;
}
