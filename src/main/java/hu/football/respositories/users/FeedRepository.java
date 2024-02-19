package hu.football.respositories.users;

import hu.football.models.entities.users.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> { //707038485 bolcsodek@gyermekvedok.hu
}
