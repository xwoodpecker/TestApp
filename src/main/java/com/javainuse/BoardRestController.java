package com.javainuse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/boards")
public class BoardRestController {
    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Operation(summary = "Get all boards")
    @GetMapping(path = "/")
    public ResponseEntity<List<Board>> getBoards() {
        return new ResponseEntity<>(boardRepository.findAll(), HttpStatus.OK);

    }

    @Operation(summary = "Get all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id){
        ResponseEntity<Board> response;
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

}
