package hu.football.respositories.football;

import hu.football.models.entities.football.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {


    Optional<League> findLeagueByName(String leagueName);

    Boolean existsLeagueByNameAndNationality(String leagueName, String nationality);

    Optional<League> findLeagueByNameOrNationality(String leagueName, String nationality);
}
