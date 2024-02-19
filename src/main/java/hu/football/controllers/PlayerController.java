package hu.football.controllers;

import hu.football.models.dto.football.PlayerDto;
import hu.football.models.entities.football.Player;
import hu.football.services.football.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;


    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayer() {
        return ResponseEntity.ok(playerService.findAllPlayer());
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PlayerDto> searchPlayer(
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "teamName", required = false) String teamName,
            @RequestParam(value = "leagueName", required = false) String leagueName
    ) {
        PlayerDto playerDto = playerService.searchPlayer(
                firstName,
                lastName,
                nationality,
                position,
                teamName,
                leagueName
        );

        return ResponseEntity.ok(playerDto);
    }

    @PostMapping
    public ResponseEntity<PlayerDto> savePlayer(@RequestBody PlayerDto playerDto) {
        PlayerDto save = playerService.savePlayer(playerDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

}
