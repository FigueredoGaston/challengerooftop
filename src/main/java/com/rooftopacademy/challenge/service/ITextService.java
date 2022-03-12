package com.rooftopacademy.challenge.service;

import com.rooftopacademy.challenge.dto.TextDTO;
import com.rooftopacademy.challenge.dto.response.DELETEOkResponseDTO;
import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.dto.response.POSTOKResponseDTO;

import java.util.List;

public interface ITextService {
    POSTOKResponseDTO newText(TextDTO textDTO);
    GETIdResponseDTO getById(Long id);
    List<GETIdResponseDTO> getList(Integer chars, Integer page, Integer rpp);
    DELETEOkResponseDTO deleteById(Long id);
}
