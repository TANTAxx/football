package hu.football.controllers;

import hu.football.models.dto.football.CoachDto;
import hu.football.models.entities.football.Coach;
import hu.football.services.football.CoachService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/coach")
public class CoachController {

    private final CoachService coachService;

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoach() {
        return ResponseEntity.ok(coachService.findAllCoach());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Coach> searchCoach(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "teamName", required = false) String teamName,
            @RequestParam(value = "leagueName", required = false) String leagueName
    ) {
        Coach search = coachService.searchCoach(firstName, lastName, teamName, leagueName);
        return ResponseEntity.ok(search);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<CoachDto> saveCoach(@RequestBody CoachDto coachDto) {
        CoachDto saveCoach = coachService.saveCoach(coachDto);
        return new ResponseEntity<>(saveCoach, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        coachService.deleteById(id);
    }
}
