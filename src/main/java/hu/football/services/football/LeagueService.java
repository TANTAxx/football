package hu.football.services.football;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.mappers.LeagueMapper;
import hu.football.models.dto.other.FieldError;
import hu.football.models.dto.football.LeagueDto;
import hu.football.models.entities.football.League;
import hu.football.respositories.football.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static hu.football.constants.ErrorConstants.EXISTS_LEAGUE;

@Slf4j
@Service
@AllArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    private final LeagueMapper leagueMapper;

    public List<League> findAllLeague() {
        return leagueRepository.findAll();
    }


    public LeagueDto saveLeague(LeagueDto leagueDto) {
        if (leagueRepository.existsLeagueByNameAndNationality(leagueDto.getName(), leagueDto.getNationality())) {
            throw new ValidationException(Collections.singletonList(new FieldError("EXISTS_LEAGUE", EXISTS_LEAGUE)));
        }
        League league = null;

        if (Objects.nonNull(leagueDto.getId())) {
            league = leagueRepository.findById(leagueDto.getId()).orElse(null);
        }

        if (Objects.isNull(league)) {
            return new LeagueDto(leagueRepository.save(new League(leagueDto)));
        }

        League updateLeague = new League(league, leagueDto);
        if (!updateLeague.equals(league)) {
            league = leagueRepository.save(updateLeague);
        }

        //TODO FIELD ERROR és MESSAGE majd bele, hogy normálisan dobja a hibákat!
        return new LeagueDto(league);
    }


    public LeagueDto findLeagueByNameAndNationaly(String leagueName, String nationality) {
        return new LeagueDto(leagueRepository.findLeagueByNameOrNationality(leagueName, nationality).orElse(null));
    }

    public LeagueDto findByName(String name) {
        return new LeagueDto(leagueRepository.findLeagueByName(name).orElse(null));
    }

    public LeagueDto searchLeague(String leagueName, String nationality) {
        try {

            if (Objects.nonNull(leagueName) && Objects.isNull(nationality)) {
                return findByName(leagueName);
            } else {
                return findLeagueByNameAndNationaly(leagueName, nationality);
            }
        } catch (NotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }
        return null;
    }

    public League findById(Long id) {
        log.info("Find by id: {} ", id);
        return leagueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("League not found by id: " + id));
    }

    public void deleteById(Long id) {
        leagueRepository.deleteById(id);
    }


}
