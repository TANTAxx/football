package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.model.dto.FieldError;
import hu.football.model.dto.PlayerDto;
import hu.football.model.entities.Player;
import hu.football.model.entities.Team;
import hu.football.respositories.PlayerRepository;
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
        if (!playerRepository.existsByFirstNameAndLastNameAndTeam(playerDto.getFirstName(), playerDto.getLastName(), playerDto.getTeam())) {
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

        } else {
            throw new ValidationException(Collections.singletonList(new FieldError(INVALID_PLAYER, INVALID_PLAYER)));
        }
    }

    public Player update(Player player) {
        if (Objects.isNull(findPlayerByFirstAndLastName(player.getFirstName(), player.getLastName()))) {
            throw new ValidationException(Collections.singletonList(new FieldError("FIRST_OR_LAST_NAME_NULL", FIRST_OR_LAST_NAME_NULL)));
        } else {
            return playerRepository.save(player);
        }
    }

    public Player findPlayerByFirstAndLastName(String firstName, String lastName) {
        try {
            if (!playerRepository.existsByFirstNameAndLastName(firstName, lastName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("Not Exists Name", NOT_EXISTS_NAME)));
            } else {
                return playerRepository.findByFirstNameAndLastName(firstName, lastName);
            }

        } catch (RuntimeException e) {
            if (Objects.isNull(firstName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_FIRST_NAME", INVALID_FIRST_NAME)));
            } else if (Objects.isNull(lastName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_FIRST_NAME", INVALID_LAST_NAME)));
            } else if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_FIRST_NAME_AND_LAST_NAME", INVALID_FIRST_NAME_AND_LAST_NAME)));
            }
        }
        return null;
    }

    public Player searchPlayer(
            String firstName,
            String lastName,
            String nationality,
            String position,
            Team team
    ) {

        try {
            if (!playerRepository.existsByFirstNameAndLastName(firstName, lastName) &&
                    (Objects.isNull(nationality) && Objects.isNull(position) && Objects.isNull(team.getTeamName()))) {
                throw new ValidationException(Collections.singletonList(new FieldError("Not Exists Name", NOT_EXISTS_NAME)));
            } else if (Objects.isNull(findPlayerByFirstAndLastName(firstName, lastName)) &&
                    (Objects.nonNull(nationality) && Objects.nonNull(position) && Objects.nonNull(team.getTeamName()))) {
                return playerRepository.findPlayerByNationalityAndPositionAndTeam(nationality, position, team.getTeamName());
            } else {
                if (Objects.nonNull(findPlayerByFirstAndLastName(firstName, lastName)) &&
                        (Objects.isNull(nationality) && Objects.isNull(position) && Objects.isNull(team.getTeamName()))) {
                    return playerRepository.findByFirstNameAndLastName(firstName, lastName);
                } else {
                    return playerRepository.findByFirstNameAndLastNameAndNationalityAndPositionAndTeam
                            (firstName, lastName, nationality, position, team);
                }
            }
        } catch (RuntimeException e) {
            if (Objects.isNull(findPlayerByFirstAndLastName(firstName, lastName)) &&
                    (Objects.isNull(nationality) && Objects.isNull(position) && Objects.isNull(team.getTeamName()))) {
                throw new ValidationException(Collections.singletonList(new FieldError("ALL_OBJECTS_IS_NULL", ALL_OBJECTS_IS_NULL)));
            } else if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_FIRST_NAME_AND_LAST_NAME", INVALID_FIRST_NAME_AND_LAST_NAME)));
            }
        }
        return null;
    }


    public List<Player> getAllPlayerByTeam(Team team) {
        return playerRepository.findAllPlayerByTeam(team);
    }
}
