package br.com.gabriel.controller;

import br.com.gabriel.service.interfaces.PersonService;
import br.com.gabriel.util.MediaType;
import br.com.gabriel.vo.v1.PersonVO;
import br.com.gabriel.vo.v2.PersonVOV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoints for managing People")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<List<PersonVO>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Create new Person",
            description = "Create new Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
    })
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Create new Person V2",
            description = "Create new Person V2 by passing in a JSON, XML or YML representation of the person",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = PersonVOV2.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
    })
    public ResponseEntity<PersonVOV2> createV2(@RequestBody PersonVOV2 person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createV2(person));
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update Person",
            description = "Update Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "Updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {

        return ResponseEntity.status(HttpStatus.OK).body(personService.update(person));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Person", description = "Delete a Person",
            tags = {"People"})
    @ApiResponses({
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
