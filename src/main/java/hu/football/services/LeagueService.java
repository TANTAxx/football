package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.mappers.LeagueMapper;
import hu.football.model.dto.LeagueDto;
import hu.football.model.entities.League;
import hu.football.respositories.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static hu.football.constants.ErrorConstants.INVALID_LEAGUE_NAME_OR_NATIONALITY;

@Slf4j
@Service
@AllArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    private final LeagueMapper leagueMapper;

    public List<League> findAllLeague() {
        return leagueRepository.findAll();
    }

    public League create(League league) {
        return leagueRepository.save(league);
    }

    public League update(League league) {
        if (Objects.isNull(league.getLeagueName()) && Objects.isNull(league.getNationality())) {
            throw new NotFoundException(INVALID_LEAGUE_NAME_OR_NATIONALITY);
        } else {
            return leagueRepository.save(league);
        }
    }

    public League findByLeagueNameAndNationaly(String leagueName, String nationality) {
        log.info("Find by first and last name: {} {} ", leagueName, nationality);
        if (Objects.isNull(leagueName) && Objects.isNull(nationality)) {
            throw new NotFoundException(INVALID_LEAGUE_NAME_OR_NATIONALITY);
        } else {
            return leagueRepository.findByLeagueNameAndAndNationality(leagueName, nationality);
        }
    }

    public League findById(Long id) {
        log.info("Find by id: {} ", id);
        return leagueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("League not found by id: " + id));
    }

    public void deleteById(Long id) {
        leagueRepository.deleteById(id);
    }

    public League getByLeagueName(String leagueName) {
        Optional<League> leagueOptional = leagueRepository.findByLeagueName(leagueName);
        if (leagueOptional.isPresent()) {
            return toEntity(leagueOptional.get());
        } else {
            throw new NotFoundException(INVALID_LEAGUE_NAME_OR_NATIONALITY);
        }
    }

    public LeagueDto entityToDto(League league) {
        return leagueMapper.toDto(league);
    }

    public League toEntity(League league) {
        LeagueDto leagueDto = entityToDto(league);
        return leagueMapper.toEntity(leagueDto);
    }
}
