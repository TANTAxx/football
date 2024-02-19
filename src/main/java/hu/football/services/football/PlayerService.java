package hu.football.services.football;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.models.dto.other.FieldError;
import hu.football.models.dto.football.PlayerDto;
import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Player;
import hu.football.models.entities.football.Team;
import hu.football.respositories.football.LeagueRepository;
import hu.football.respositories.football.PlayerRepository;
import hu.football.respositories.football.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hu.football.constants.ErrorConstants.EXISTS_PLAYER;

@Slf4j
@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;


    public Player findById(Long id) {
        log.info("Find by id: {} ", id);
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Player not found by id: " + id));
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public List<Player> findAllPlayer() {
        return playerRepository.findAll();
    }

    public PlayerDto savePlayer(PlayerDto playerDto) {
        if (playerRepository.existsPlayerByFirstNameAndLastNameAndNationality(
                playerDto.getFirstName(),
                playerDto.getLastName(),
                playerDto.getNationality()
        )) {
            throw new ValidationException(Collections.singletonList(new FieldError("EXISTS_PLAYER", EXISTS_PLAYER)));
        }

        Player player = null;

        Optional<Team> playerByTeam = teamRepository.findById(playerDto.getTeamId());
        Optional<League> playerByLeague = leagueRepository.findById(playerDto.getLeagueId());

        if (Objects.nonNull(playerDto.getId())) {
            player = playerRepository.findById(playerDto.getId()).orElse(null);
        }

        if (Objects.isNull(player)) {

            Player entity = new Player(playerDto);
            entity.setTeamId(playerByTeam.get());
            entity.setLeagueId(playerByLeague.get());
            Player save = playerRepository.save(entity);
            return new PlayerDto(save);
        }

        Player updatePlayer = new Player(player, playerDto);
        updatePlayer.setLeagueId(playerByLeague.get());
        updatePlayer.setTeamId(playerByTeam.get());
        if (!updatePlayer.equals(player)) {
            player = playerRepository.save(updatePlayer);
        }
        log.info("Mentesre kész adatok Service: {}", player);
        return new PlayerDto(player);
    }


    public PlayerDto searchPlayer(String firstName, String lastName, String nationality, String position, String teamName, String leagueName) {
        League searchLeague = leagueRepository.findLeagueByName(leagueName).orElse(null);
        Team searchTeam = teamRepository.findTeamByTeamName(teamName).orElse(null);

        Player searchPlayer = searchPlayer(firstName, lastName, nationality, position, searchTeam, searchLeague);

        return new PlayerDto(searchPlayer);

    }

    public Player searchPlayer(String firstName, String lastName, String nationality, String position, Team team, League league) {

        Optional<Player> searchPlayer = playerRepository.findPlayerByFirstNameAndLastNameAndNationalityAndPositionAndTeamIdAndLeagueId(
                firstName,
                lastName,
                nationality,
                position,
                team,
                league
        );

        if (searchPlayer.isEmpty()) {
            return null;
        } else {
            return searchPlayer.get();
        }

    }

}
