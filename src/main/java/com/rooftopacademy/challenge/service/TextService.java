package com.rooftopacademy.challenge.service;

import com.rooftopacademy.challenge.dto.TextDTO;
import com.rooftopacademy.challenge.dto.response.DELETEOkResponseDTO;
import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.dto.response.POSTOKResponseDTO;
import com.rooftopacademy.challenge.exception.*;
import com.rooftopacademy.challenge.model.Text;
import com.rooftopacademy.challenge.repository.ITextRepository;
import com.rooftopacademy.challenge.utils.ServiceUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.Valid;
import java.util.*;

@Service
public class TextService implements ITextService{

    private final ITextRepository textRepository;
    ModelMapper mapper = new ModelMapper();
    ServiceUtils serviceUtils = new ServiceUtils();

    public TextService(ITextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    @Transactional
    public POSTOKResponseDTO newText(@Valid TextDTO textDTO) {
        if (textDTO.getChars() == null || textDTO.getChars() < 2)
            textDTO.setChars(2);
        if (textDTO.getChars() > textDTO.getText().length())
            textDTO.setChars(textDTO.getText().length());
        Text text = this.mapper.map(textDTO, Text.class);
        String hash = this.serviceUtils.MD5(text.getText()
                    .toLowerCase(Locale.ROOT)+text.getChars());
        if (hash == null) throw new HashErrorException("Error when create hash");
        text.setHash(hash);
        text.setActive(1);
        Text textAux = this.textRepository.findByHash(hash);
        if (textAux == null) textAux = this.textRepository.save(text);
        return new POSTOKResponseDTO(textAux.getId(),"/text/"+textAux.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public GETIdResponseDTO getById(Long id) {
        Text text = this.textRepository.findById(id)
                .orElseThrow(() ->new NotExistIdException("Text not found"));
        GETIdResponseDTO responseDTO = newResponse(text);
        return responseDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GETIdResponseDTO> getList(Integer chars, Integer page, Integer rpp) {
        if (chars < 2) chars = 2;
        if (page < 1) page = 1;
        if (rpp < 10) rpp = 10;
        if (rpp > 100) rpp = 100;
        Pageable pagesAndElements = PageRequest.of(page-1, rpp);
        List<Text> listText = this.textRepository
                            .findAllByCharsAndActive(chars, 1, pagesAndElements);
        if (listText.size() == 0 || listText == null)
            throw new NotExistCharsException("The requested number of characters does not exist");
        return listResponse(listText);
    }

    @Override
    @Transactional
    public DELETEOkResponseDTO deleteById(Long id) {
        Text text = this.textRepository.findById(id).orElse(null);
        if (text == null) throw new NotExistIdException("Text not found");
        text.setActive(0);
        this.textRepository.save(text);
        return new DELETEOkResponseDTO();
    }

    /**
     * Function responsible for returning a response from a text
     */

    private GETIdResponseDTO newResponse(Text text){
        GETIdResponseDTO responseDTO = new GETIdResponseDTO();
        responseDTO.setId(text.getId());
        responseDTO.setHash(text.getHash());
        responseDTO.setChars(text.getChars());
        responseDTO.setResult(this.serviceUtils.subStringJSON(text.getText(),
                                text.getChars()));
        if (responseDTO.getResult() == null)
            throw new SubStringErrorException("Error when count substrings");
        return responseDTO;
    }

    /**
     * Function responsible for returning a list of paged texts based on the parameters
     */

    public List<GETIdResponseDTO> listResponse(List<Text> textList){
       List<GETIdResponseDTO> list = new ArrayList<>();
       for (Text text: textList) list.add(newResponse(text));
        return list;
    }
}
