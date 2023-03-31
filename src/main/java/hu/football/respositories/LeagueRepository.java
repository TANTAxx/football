package hu.football.respositories;

import hu.football.model.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    League findByLeagueNameAndAndNationality(String leagueName, String nationality);

    Optional<League> findByLeagueName(String leagueName);

    League findByNationality(String nationality);

}
