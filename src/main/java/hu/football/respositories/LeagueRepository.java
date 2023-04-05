package hu.football.respositories;

import hu.football.model.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    Boolean existsByLeagueName(String leagueName);

    League findByLeagueNameAndAndNationality(String leagueName, String nationality);

    Optional<League> findByLeagueName(String leagueName);

    Optional<League> findByNationality(String nationality);

    Boolean existsByNationality(String nationality);

}
