package com.rooftopacademy.challenge.controller;

import com.rooftopacademy.challenge.dto.TextDTO;
import com.rooftopacademy.challenge.dto.response.DELETEOkResponseDTO;
import com.rooftopacademy.challenge.dto.response.GETIdResponseDTO;
import com.rooftopacademy.challenge.dto.response.POSTOKResponseDTO;
import com.rooftopacademy.challenge.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/text")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,
                                    RequestMethod.DELETE})
public class TextController {

    @Autowired
    private ITextService textService;

    /**
     * EndPoint 1: insert a new record in the database
     * @param "TextDTO"
     * @return POSTIdResponseDTO
     */
    @PostMapping()
    public ResponseEntity<POSTOKResponseDTO> newText(@RequestBody @Valid TextDTO textDTO){
        return ResponseEntity.ok(this.textService.newText(textDTO));
    }

    /**
     * EndPoint 2: search a record in the database by ID
     * @param "Long id"
     * @return GETIdResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<GETIdResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.textService.getById(id));
    }

    /**
     * EndPoint 3: search a list of record in the database by optionals parameters
     * @param "Integer chars, Integer page, Integer rpp"
     * @return List<GETIdResponseDTO>
     */
    @GetMapping
    public ResponseEntity<List<GETIdResponseDTO>> getPage(
                    @RequestParam(defaultValue = "2") Integer chars,
                    @RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer rpp){
        return ResponseEntity.ok(this.textService.getList(chars, page, rpp));
    }

    /**
     * EndPoint 4: delete a record in the database by ID
     * @param "Long id"
     * @return DELETEIdResponseDTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DELETEOkResponseDTO> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(this.textService.deleteById(id));
    }
}
