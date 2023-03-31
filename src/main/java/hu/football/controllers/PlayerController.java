package hu.football.controllers;

import hu.football.model.entities.Player;
import hu.football.model.entities.Team;
import hu.football.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(path = "/findByName")
    public ResponseEntity<List<Player>> getPlayer(@RequestParam("firstName") String firstName,
                                                  @RequestParam("lastName") String lastName) {
        return ResponseEntity.ok(playerService.findPlayerByFirstAndLastName(firstName, lastName));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Player> searchPlayer(
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("nationality") String nationality,
            @RequestParam("position") String position,
            @RequestParam("team") Team team
    ) {
        return ResponseEntity.ok(playerService.searchPlayer(
                firstName,
                lastName,
                nationality,
                position,
                team
        ));
    }

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        return ResponseEntity.status(201).body(playerService.create(player));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.update(player));
    }
}
