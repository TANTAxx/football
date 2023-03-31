package hu.football.respositories;

import hu.football.model.entities.League;
import hu.football.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByTeamName(String teamName);


}
