package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.model.dto.FieldError;
import hu.football.model.dto.LeagueDto;
import hu.football.model.dto.TeamDto;
import hu.football.model.entities.League;
import hu.football.model.entities.Team;
import hu.football.respositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hu.football.constants.ErrorConstants.INVALID_FIRST_NAME_OR_LAST_NAME;
import static hu.football.constants.ErrorConstants.INVALID_TEAM_NAME;

@Slf4j
@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final LeagueService leagueService;

    public List<Team> findAllTeam() {
        return teamRepository.findAll();
    }

    public Team getByTeamName(String teamName) {
        Optional<Team> teamOptional = teamRepository.findByTeamName(teamName);

        if (teamOptional.isPresent()) {
            return teamOptional.get();
        } else {
            return null;
        }
    }


    public Team saveTeam(TeamDto teamDto) {
        if (!teamRepository.existsByTeamName(teamDto.getTeamName())) {
            log.info("Team save: ");
            Team team = new Team(
                    leagueService.getByLeagueNationality(teamDto.getNationality()),
                    teamDto.getTeamName(),
                    teamDto.getFounded(),
                    teamDto.getAddress(),
                    teamDto.getStadium(),
                    leagueService.getByLeagueName(teamDto.getLeague())
            );
            log.info("Team saved: {}", team);
          return teamRepository.save(team);
        } else {
            throw new ValidationException(Collections.singletonList(new FieldError("Team Name", "Duplicated Team Name")));
        }
    }

    public Team update(Team team) {
        if (Objects.isNull(team.getTeamName())) {
            throw new NotFoundException(INVALID_FIRST_NAME_OR_LAST_NAME);
        } else {
            return teamRepository.save(team);
        }
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
