package hu.football.respositories.football;

import hu.football.models.entities.football.League;
import hu.football.models.entities.football.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Boolean existsTeamByTeamName(String teamName);

    Optional<Team> findTeamByTeamNameAndNationality(String teamName, String nationality);
    Optional<Team> findTeamByTeamName(String teamName);
    Team findTeamByTeamNameAndNationalityAndLeagueId(String teamName, String nationality, League leagueId);
}
