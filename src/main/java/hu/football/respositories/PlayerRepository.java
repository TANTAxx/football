package hu.football.respositories;

import hu.football.model.entities.Player;
import hu.football.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstNameAndLastName(String firstName, String lastName);

    Player findByFirstNameAndLastNameAndNationalityAndPositionAndTeam(
            String firstName, String lastName, String nationaly,
            String position, Team team
    );

    List<Player> findAllPlayerByTeam(Team team);
}
