package hu.football.services;

import hu.football.exceptions.NotFoundException;
import hu.football.exceptions.ValidationException;
import hu.football.mappers.LeagueMapper;
import hu.football.model.dto.FieldError;
import hu.football.model.dto.LeagueDto;
import hu.football.model.entities.League;
import hu.football.respositories.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static hu.football.constants.ErrorConstants.*;

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
        if (!leagueRepository.existsByLeagueName(league.getLeagueName())) {
            return leagueRepository.save(league);
        } else {
            throw new ValidationException(Collections.singletonList(new FieldError("LEAGUE_NAME", DUPLICATED_LEAGUE_NAME)));
        }
    }

    public League update(League league) {
        if (Objects.isNull(league.getLeagueName()) && Objects.isNull(league.getNationality())) {
            throw new NotFoundException(INVALID_LEAGUE_NAME_AND_NATIONALITY);
        } else {
            return leagueRepository.save(league);
        }
    }

    public League findByLeagueNameAndNationaly(String leagueName, String nationality) {

        try {
            return leagueRepository.findByLeagueNameAndAndNationality(leagueName, nationality);

        } catch (RuntimeException e) {
            if (Objects.isNull(leagueName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_LEAGUE_NAME", INVALID_LEAGUE_NAME)));
            } else if (Objects.isNull(nationality)) {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_NATIONALITY", INVALID_NATIONALITY)));
            } else {
                throw new ValidationException(Collections.singletonList(new FieldError("INVALID_LEAGUE_NAME_AND_NATIONALITY", INVALID_LEAGUE_NAME_AND_NATIONALITY)));
            }
        }
    }

    public void deleteById(Long id) {
        leagueRepository.deleteById(id);
    }

    public League getByLeagueName(String leagueName) {
        Optional<League> leagueNameOp = leagueRepository.findByLeagueName(leagueName);

        try {
            return leagueNameOp.get();
        } catch (RuntimeException e) {
            if (!leagueRepository.existsByLeagueName(leagueName)) {
                throw new ValidationException(Collections.singletonList(new FieldError("NOT_EXISTS_LEAGUE_NAME", NOT_EXISTS_LEAGUE_NAME)));
            } else if (!leagueNameOp.isPresent()) {
                throw new ValidationException(Collections.singletonList(new FieldError("NOT_FOUND_LEAGUE_NAME", NOT_FOUND_LEAGUE_NAME)));
            }
        }
        return null;
    }

    public League getByLeagueNationality(String nationality) {
        Optional<League> leagueOptional = leagueRepository.findByNationality(nationality);

        try {
            return leagueOptional.get();
        } catch (RuntimeException e) {
            if (!leagueOptional.isPresent()) {
                throw new ValidationException(Collections.singletonList(new FieldError("NOT_FOUND_NATIONALITY", NOT_FOUND_NATIONALITY)));
            } else if (!leagueRepository.existsByNationality(nationality)) {
                throw new ValidationException(Collections.singletonList(new FieldError("NOT_EXISTS_NATIONALITY", NOT_EXISTS_NATIONALITY)));
            }
        }
        return null;
    }

    private LeagueDto entityToDto(League league) {
        return leagueMapper.toDto(league);
    }

    private League toEntity(League league) {
        LeagueDto leagueDto = entityToDto(league);
        return leagueMapper.toEntity(leagueDto);
    }

}


