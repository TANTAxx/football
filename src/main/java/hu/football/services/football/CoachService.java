package hu.football.services.football;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.models.dto.football.CoachDto;
import hu.football.models.dto.other.FieldError;
import hu.football.models.entities.football.Coach;
import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Team;
import hu.football.respositories.football.CoachRepository;
import hu.football.respositories.football.LeagueRepository;
import hu.football.respositories.football.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hu.football.constants.ErrorConstants.EXISTS_COACH;

@Slf4j
@Service
@AllArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public List<Coach> findAllCoach() {
        return coachRepository.findAll();
    }

    public CoachDto saveCoach(CoachDto coachDto) {
        if (coachRepository.existsCoachByFirstNameAndLastNameAndNationality(coachDto.getFirstName(), coachDto.getLastName(), coachDto.getNationality())) {
            throw new ValidationException(Collections.singletonList(new FieldError("EXISTS_COACH", EXISTS_COACH)));
        }
        Optional<League> leagueById = leagueRepository.findById(coachDto.getLeagueId());
        Optional<Team> teamById = teamRepository.findById(coachDto.getTeamId());
        Coach coach = null;

        if (Objects.nonNull(coachDto.getId())) {
            coach = coachRepository.findById(coachDto.getId()).orElse(null);
        }

        if (Objects.isNull(coach)) {

            Coach entity = new Coach(coachDto);
            entity.setLeagueId(leagueById.get());
            entity.setTeamId(teamById.get());
            Coach save = coachRepository.save(entity);
            return new CoachDto(save);
        }

        Coach updateCoach = new Coach(coach, coachDto);
        updateCoach.setLeagueId(leagueById.get());
        updateCoach.setTeamId(teamById.get());
        if (!updateCoach.equals(coach)) {
            coach = coachRepository.save(updateCoach);
        }

        return new CoachDto(coach);
    }

    public Coach searchCoach(String firstName, String lastName, String teamName, String leagueName) {
        Team searchTeam = teamRepository.findTeamByTeamName(teamName).orElse(null);
        League searchLeague = leagueRepository.findLeagueByName(leagueName).orElse(null);

        Optional<Coach> searchCoach = coachRepository.findByFirstNameAndLastNameAndTeamIdAndLeagueId(
                firstName,
                lastName,
                searchTeam,
                searchLeague
        );

        if (searchCoach.isEmpty()) {
            throw new ValidationException(Collections.singletonList(new FieldError("NOT_FOUND", "Not found coach!")));
        } else {
            return searchCoach.get();
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

