package com.rooftopacademy.challenge.unit.service;

import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.exception.NotExistIdException;
import com.rooftopacademy.challenge.model.Text;
import com.rooftopacademy.challenge.repository.ITextRepository;
import com.rooftopacademy.challenge.service.TextService;
import com.rooftopacademy.challenge.utils.UtilsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TextServiceTest {

    @Mock
    ITextRepository textRepository;

    @InjectMocks
    TextService textService;

    //Happy path

    @Test
    @DisplayName("getById: correct search by ID")
    void getByIdCorrect(){
        //Init Mocks
        Text textAux = UtilsTest.newText();

        //Arrange
        GETIdResponseDTO responseDTO = new GETIdResponseDTO(1L,
                "aba671cbce53235f31a1e2501c38836f",
                2, UtilsTest.subStringJSON("solo se que nada se", 2));

        //Mocks
        when(this.textRepository.findById(1L))
                .thenReturn(Optional.of(textAux));

        //Act
        GETIdResponseDTO result = this.textService.getById(1L);

        //Assert
        Assertions.assertEquals(responseDTO.getId(), result.getId());

        //Verify Mock
        verify(this.textRepository, times(1))
                .findById(1L);
    }

    //Unhappy path

    @Test
    @DisplayName("getById: catch exception if not exist text")
    void getByIdIncorrect(){

        //Mocks
        when(this.textRepository.findById(1L))
                .thenReturn(Optional.empty());

        //Act && Assert
        Assertions.assertThrows(NotExistIdException.class,
                () -> this.textService.getById(1L));

        //Verify Mock
        verify(this.textRepository, times(1))
                .findById(1L);
    }
}
