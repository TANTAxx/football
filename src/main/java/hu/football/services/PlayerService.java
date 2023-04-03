package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.model.dto.PlayerDto;
import hu.football.model.entities.Player;
import hu.football.model.entities.Team;
import hu.football.respositories.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static hu.football.constants.ErrorConstants.INVALID_FIRST_NAME_OR_LAST_NAME;

@Slf4j
@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

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

    public Player create(PlayerDto playerDto) {

        Team teamName = teamService.getByTeamName(playerDto.getTeam());
        Player player = new Player(
                playerDto.getFirstName(),
                playerDto.getLastName(),
                playerDto.getNationality(),
                playerDto.getDateOfBirth(),
                playerDto.getAge(),
                playerDto.getCountryOfBirth(),
                playerDto.getPlaceOfBirth(),
                playerDto.getPosition(),
                playerDto.getHeight(),
                playerDto.getWeight(),
                playerDto.getFoot(),
                teamName
        );

        return playerRepository.save(player);
    }

    public Player update(Player player) {
        if (Objects.isNull(player.getFirstName()) || Objects.isNull(player.getLastName())) {
            throw new NotFoundException(INVALID_FIRST_NAME_OR_LAST_NAME);
        } else {
            return playerRepository.save(player);
        }
    }

    public List<Player> findPlayerByFirstAndLastName(String firstName, String lastName) {
        if (Objects.isNull(firstName) || Objects.isNull(lastName)) {
            throw new NotFoundException(INVALID_FIRST_NAME_OR_LAST_NAME);
        } else {
            return playerRepository.findByFirstNameAndLastName(firstName, lastName);
        }
    }

    public Player searchPlayer(
            String firstName,
            String lastName,
            String nationality,
            String position,
            Team team
    ) {
        return playerRepository.findByFirstNameAndLastNameAndNationalityAndPositionAndTeam
                (
                        firstName,
                        lastName,
                        nationality,
                        position,
                        team
                );
    }

    public List<Player> getAllPlayerByTeam(Team team) {
        return playerRepository.findAllPlayerByTeam(team);
    }
}
