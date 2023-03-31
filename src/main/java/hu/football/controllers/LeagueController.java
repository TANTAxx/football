package hu.football.controllers;

import hu.football.model.dto.LeagueDto;
import hu.football.model.entities.League;
import hu.football.services.LeagueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/league")
public class LeagueController {

    private final LeagueService leagueService;


    @GetMapping
    public ResponseEntity<List<League>> getAllLeague() {
        return ResponseEntity.ok(leagueService.findAllLeague());
    }

    @GetMapping(path = "/league")
    public ResponseEntity<League> getLeagueByLeagueName(@RequestParam("leagueName") String leagueName,
                                                        @RequestParam("nationality") String nationality) {
        return ResponseEntity.ok(leagueService.findByLeagueNameAndNationaly(leagueName, nationality));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<League> saveLeague(@RequestBody League league) {
        return ResponseEntity.status(201).body(leagueService.create(league));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<League> updateLeague(@RequestBody League league) {
        return ResponseEntity.ok(leagueService.update(league));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        leagueService.deleteById(id);
    }


}
