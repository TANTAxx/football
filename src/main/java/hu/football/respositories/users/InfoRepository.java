package hu.football.respositories.users;

import hu.football.models.entities.users.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    @Query("SELECT p FROM Info p WHERE "
            + "(p.startAt <= :today AND (p.endAt > :today OR p.endAt IS NULL)) AND p.active = true")
    List<Info> findAllActivePopUps(LocalDateTime today);

    @Query("SELECT p FROM Info p WHERE "
            + "((p.startAt <= :today AND (p.endAt > :today OR p.endAt IS NULL)) OR p.active = false)")
    List<Info> findAllPopUps(LocalDateTime today);
}
