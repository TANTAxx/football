package hu.football.controllers;

import hu.football.models.dto.football.TeamDto;
import hu.football.models.entities.football.Team;
import hu.football.services.football.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    @GetMapping("/search")
    public ResponseEntity<TeamDto> searchTeam(@RequestParam(value = "teamName") String teamName,
                                              @RequestParam(value = "nationality", required = false) String nationality) {

        TeamDto searchTeam = teamService.findTeamByNameAndNationality(teamName, nationality);
        return new ResponseEntity<>(searchTeam, OK);
    }


    @PostMapping(path = "/save")
    public ResponseEntity<TeamDto> saveTeam(@RequestBody TeamDto teamDto) {
        TeamDto save = teamService.saveTeam(teamDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        teamService.deleteById(id);
    }
}
