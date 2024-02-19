package hu.football.services.football;

import hu.football.exceptions.ValidationException;
import hu.football.models.dto.other.FieldError;
import hu.football.models.dto.football.TeamDto;
import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Team;
import hu.football.respositories.football.LeagueRepository;
import hu.football.respositories.football.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hu.football.constants.ErrorConstants.EXISTS_TEAM;

@Slf4j
@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public List<Team> findAllTeam() {
        return teamRepository.findAll();
    }

    public TeamDto findTeamByNameAndNationality(String teamName, String nationality) {
        return new TeamDto(teamRepository.findTeamByTeamNameAndNationality(teamName, nationality).orElse(null));
    }


    public TeamDto findTeamByNameAndNationalityAndLeague(String teamName, String nationality, String leagueName) {
        League league = leagueRepository.findLeagueByName(leagueName).orElse(null);
        return new TeamDto(teamRepository.findTeamByTeamNameAndNationalityAndLeagueId(teamName, nationality, league));
    }

    public TeamDto saveTeam(TeamDto teamDto) {
        if (teamRepository.existsTeamByTeamName(teamDto.getTeamName())) {
            throw new ValidationException(Collections.singletonList(new FieldError("EXISTS_TEAM", EXISTS_TEAM)));
        }

        Team team = null;
        Optional<League> leagueId = leagueRepository.findById(teamDto.getLeagueId());

        if (Objects.nonNull(teamDto.getId())) {
            team = teamRepository.findById(teamDto.getId()).orElse(null);
        }

        if (Objects.isNull(team)) {

            Team entity = new Team(teamDto);
            entity.setLeagueId(leagueId.get());
            Team save = teamRepository.save(entity);
            return new TeamDto(save);
        }

        Team updateTeam = new Team(team, teamDto);
        updateTeam.setLeagueId(leagueId.get());
        if (!updateTeam.equals(team)) {
            team = teamRepository.save(updateTeam);
        }

        return new TeamDto(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
