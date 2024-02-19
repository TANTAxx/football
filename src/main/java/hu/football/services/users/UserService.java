package hu.football.services.users;

import hu.football.exceptions.ValidationException;
import hu.football.models.dto.other.FieldError;
import hu.football.models.dto.users.UserDto;
import hu.football.models.entities.users.User;
import hu.football.respositories.users.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static hu.football.constants.ErrorConstants.EXISTS_EMAIL;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public UserDto saveUser(UserDto userDTO) {
        if (userRepository.existsUserByEmail(userDTO.getEmail())) {
            throw new ValidationException(Collections.singletonList(new FieldError("EXISTS_EMAIL", EXISTS_EMAIL)));
        }
        User user = null;

        if (Objects.nonNull(userDTO.getId())) {
            user = userRepository.findById(userDTO.getId()).orElse(null);
        }

        if (Objects.isNull(user)) {

            User entity = new User(userDTO);
            User save = userRepository.save(entity);
            return new UserDto(save);
        }

        User updateUser = new User(user, userDTO);
        if (!updateUser.equals(user)) {
            user = userRepository.save(updateUser);
        }

        return new UserDto(user);
    }
}
