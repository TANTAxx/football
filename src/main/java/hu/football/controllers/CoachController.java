package hu.football.controllers;

import hu.football.model.entities.Coach;
import hu.football.services.CoachService;
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
    public ResponseEntity<List<Coach>> getAllCoach(){
        return ResponseEntity.ok(coachService.findAllCoach());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id){
        return ResponseEntity.ok(coachService.findById(id));
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<Coach>> getCoachFirstAndLastName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
            ){
        return ResponseEntity.ok(coachService.findByFirstAndLastName(firstName,lastName));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Coach> saveCoach(@RequestBody Coach coach){
        return ResponseEntity.status(201).body(coachService.create(coach));
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Coach> updateCoach(@RequestBody Coach coach){
        return ResponseEntity.ok(coachService.create(coach));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        coachService.deleteById(id);
    }


}
