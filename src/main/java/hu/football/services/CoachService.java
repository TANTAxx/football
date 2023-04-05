package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.model.dto.CoachDto;
import hu.football.model.dto.FieldError;
import hu.football.model.entities.Coach;
import hu.football.model.entities.Team;
import hu.football.respositories.CoachRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static hu.football.constants.ErrorConstants.*;

@Slf4j
@Service
@AllArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final TeamService teamService;

    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }

    public Boolean existsCoach(String fistName, String lastName, Team team) {
        return coachRepository.existsByFirstNameAndLastNameAndTeam(fistName, lastName, team);
    }

    public Coach create(CoachDto coachDto) {
        log.info("Coach save: ");
        Coach coach = new Coach(
                coachDto.getFirstName(),
                coachDto.getLastName(),
                coachDto.getNationality(),
                coachDto.getAge(),
                coachDto.getPlaceOfBirth(),
                coachDto.getCountryOfBirth(),
                coachDto.getDateOfBirth(),
                teamService.getByTeamName(coachDto.getTeam())
        );
        log.info("Coach Saved: {}", coachDto);

        if (existsCoach(coach.getFirstName(), coach.getLastName(), coach.getTeam())) {
            throw new ValidationException(Collections.singletonList(new FieldError("COACH_EXISTS", COACH_EXISTS)));
        } else {
            return coachRepository.save(coach);
        }
    }


    public Coach update(Coach coach) {
        if (Objects.isNull(coach.getFirstName()) || Objects.isNull(coach.getLastName())) {
            throw new NotFoundException(INVALID_FIRST_NAME_AND_LAST_NAME);
        } else {
            return coachRepository.save(coach);
        }
    }

    public List<Coach> findByFirstAndLastName(String firstName, String lastName) {
        log.info("Find by first and last name: {} {} ", firstName, lastName);
        if (Objects.isNull(firstName) || Objects.isNull(lastName)) {
            if (existsCoachName(firstName, lastName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("COACH_NOT_EXISTS",COACH_NOT_EXISTS )));
            } else {
                return coachRepository.findCoachByFirstNameAndLastName(firstName, lastName);
            }
        } else {
            throw new NotFoundException(INVALID_FIRST_NAME_AND_LAST_NAME);
        }
    }

    private Boolean existsCoachName(String firstName, String lastName) {
        return coachRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public Coach findById(Long id) {
        log.info("Find by id: {} ", id);
        return coachRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coach not found by id: " + id));
    }

    public void deleteById(Long id) {
        coachRepository.deleteById(id);
    }
}

