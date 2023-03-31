package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.mappers.LeagueMapper;
import hu.football.model.dto.LeagueDto;
import hu.football.model.entities.League;
import hu.football.respositories.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        try {
            log.info("idebe");
            List<League> leagues = findAllLeague();
            log.info("idebe" + leagues);
            for (int i = leagues.size() - 1; i >= 0; i--) {
                log.info("idebe2");
                if (league.getLeagueName().equals(leagues.get(i).getLeagueName())) {
                    log.info("League name reserved!");
                    return null;
                } else {
                    log.info("New league save -> {} ", league);
                    return leagueRepository.save(league);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            throw new NotFoundException("");
        }
        return null;
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

    public void deleteById(Long id) {
        leagueRepository.deleteById(id);
    }

    public League getByLeagueName(String leagueName) {
        League leagueOptional = leagueRepository.findByLeagueName(leagueName)
                .orElseThrow(() -> new NotFoundException("Not found league name " + leagueName));
        return toEntity(leagueOptional);
    }

    public League getByName(String leagueName) {
        return leagueRepository.findByLeagueName(leagueName)
                .orElseThrow(() -> new NotFoundException("Not found league name " + leagueName));
    }


    private LeagueDto entityToDto(League league) {
        return leagueMapper.toDto(league);
    }

    private League toEntity(League league) {
        LeagueDto leagueDto = entityToDto(league);
        return leagueMapper.toEntity(leagueDto);
    }

    public League getByLeagueNationality(String nationality) {
        return leagueRepository.findByNationality(nationality);
    }
}
