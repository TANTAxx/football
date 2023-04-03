package hu.football.respositories;

import hu.football.model.entities.League;
import hu.football.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamName(String teamName);

    Boolean existsByTeamName(String teamName);

}
