package hu.football.controllers;

import hu.football.model.dto.TeamDto;
import hu.football.model.entities.Player;
import hu.football.model.entities.Team;
import hu.football.services.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeam() {
        return ResponseEntity.ok(teamService.findAllTeam());
    }

    @GetMapping(path = "/name")
    public ResponseEntity<Team> getTeam(@RequestParam("teamName") String teamName) {
        return ResponseEntity.ok(teamService.getByTeamName(teamName));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTeam(@RequestBody TeamDto teamDto) {
        teamService.saveTeam(teamDto);
        log.info("Saved Successfully");
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(team));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        teamService.deleteById(id);
    }
}
