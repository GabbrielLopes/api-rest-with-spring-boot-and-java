package br.com.gabriel.controller;

import br.com.gabriel.service.interfaces.BookService;
import br.com.gabriel.util.MediaType;
import br.com.gabriel.vo.v1.BookVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Find a Book", description = "Find a Book",
            tags = {"Book"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<BookVO> findById(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Find all Books", description = "Find all Books",
            tags = {"Book"})
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<List<BookVO>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Create new Book",
            description = "Create new Book by passing in a JSON, XML or YML representation of the book",
            tags = {"Book"})
    @ApiResponses({
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
    })
    public ResponseEntity<BookVO> create(@RequestBody BookVO book) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update Book",
            description = "Update Book by passing in a JSON, XML or YML representation of the book",
            tags = {"Book"})
    @ApiResponses({
            @ApiResponse(description = "Updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<BookVO> update(@RequestBody BookVO book) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(book));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Book", description = "Delete a Book",
            tags = {"Book"})
    @ApiResponses({
            @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content),
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
