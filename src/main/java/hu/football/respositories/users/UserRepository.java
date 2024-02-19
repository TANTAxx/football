package hu.football.respositories.users;

import hu.football.models.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByEmail(String email);
}
