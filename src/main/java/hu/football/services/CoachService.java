package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.model.entities.Coach;
import hu.football.respositories.CoachRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static hu.football.constants.ErrorConstants.INVALID_FIRST_NAME_OR_LAST_NAME;

@Slf4j
@Service
@AllArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;

    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }

    public Coach create(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach update(Coach coach) {
        if (Objects.isNull(coach.getFirstName()) || Objects.isNull(coach.getLastName())) {
            throw new NotFoundException(INVALID_FIRST_NAME_OR_LAST_NAME);
        } else {
            return coachRepository.save(coach);
        }
    }

    public List<Coach> findByFirstAndLastName(String firstName, String lastName) {
        log.info("Find by first and last name: {} {} ", firstName, lastName);
        if (Objects.isNull(firstName) || Objects.isNull(lastName)) {
            throw new NotFoundException(INVALID_FIRST_NAME_OR_LAST_NAME);
        } else {
            return coachRepository.findCoachByFirstNameAndLastName(firstName, lastName);
        }
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

