package hu.football.respositories.football;

import hu.football.models.entities.football.Coach;
import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    List<Coach> findCoachByFirstNameAndLastName(String firstName, String lastName);

    Optional<Coach> findByFirstNameAndLastNameAndTeamIdAndLeagueId(String firstName, String lastName, Team team, League league);

    Boolean existsCoachByFirstNameAndLastNameAndNationality(String firstName, String lastName, String nationality);
}
