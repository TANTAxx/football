package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.model.dto.TeamDto;
import hu.football.model.entities.Team;
import hu.football.respositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public List<Team> getByTeamName(String teamName) {
        if (Objects.isNull(teamName)) {
            throw new NotFoundException(INVALID_TEAM_NAME);
        } else {
            return teamRepository.findByTeamName(teamName);
        }
    }

    public Team saveTeam(TeamDto teamDto) {
        Team team = new Team(
                teamDto.getNationality(),
                teamDto.getTeamName(),
                teamDto.getFounded(),
                teamDto.getAddress(),
                teamDto.getStadium(),
                leagueService.getByLeagueName(teamDto.getLeague())
        );

        log.info("Team save : {}", team);

        return teamRepository.save(team);
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
