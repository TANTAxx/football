package hu.football.respositories.football;

import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Player;
import hu.football.models.entities.football.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstNameAndLastName(String firstName, String lastName);

    Boolean existsPlayerByFirstNameAndLastNameAndNationality(String firstName, String lastName, String nationality);

    Optional<Player> findPlayerByFirstNameAndLastNameAndNationality(String firstName, String lastName, String nationality);
    Optional<Player> findPlayerByFirstNameAndLastNameAndNationalityAndPositionAndTeamIdAndLeagueId(String firstName, String lastName, String nationality,String position, Team teamId, League leagueId);
}
