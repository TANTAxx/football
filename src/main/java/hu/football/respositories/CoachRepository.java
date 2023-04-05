package hu.football.respositories;

import hu.football.model.entities.Coach;
import hu.football.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    List<Coach> findCoachByFirstNameAndLastName(String firstName, String lastName);

    Boolean existsByFirstNameAndLastNameAndTeam(String firstName, String lastName, Team teamName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
