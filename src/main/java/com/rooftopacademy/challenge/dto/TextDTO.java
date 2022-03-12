package com.rooftopacademy.challenge.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rooftopacademy.challenge.exception.ValidException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TextDTO {
    @NotBlank(message = "Text empty")
    private String text;

    @Min(value = -500, message = "Not valid chars value", groups = ValidException.class)
    private Integer chars;
}
