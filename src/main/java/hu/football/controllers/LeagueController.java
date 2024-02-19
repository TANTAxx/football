package hu.football.controllers;

import hu.football.models.dto.football.LeagueDto;
import hu.football.models.entities.football.League;
import hu.football.services.football.LeagueService;
import lombok.AllArgsConstructor;
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

    @GetMapping(path = "/search")
    public ResponseEntity<LeagueDto> searchLeague(@RequestParam(value = "leagueName") String leagueName,
                                                        @RequestParam(value = "nationality" , required = false) String nationality) {
        return ResponseEntity.ok(leagueService.searchLeague(leagueName, nationality));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<LeagueDto> saveLeague(@RequestBody LeagueDto leagueDto) {
        LeagueDto saveLeague = leagueService.saveLeague(leagueDto);
        return new ResponseEntity<>(saveLeague, HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        leagueService.deleteById(id);
    }
}
