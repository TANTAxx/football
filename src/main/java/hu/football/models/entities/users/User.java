package hu.football.models.entities.users;

import hu.football.models.dto.users.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String password;

    @ManyToOne
    private Address address;
    private String favouriteTeam;
    private String birthDay;
    private String age;
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(UserDto userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.lastName = userDTO.getLastName();
        this.firstName = userDTO.getFirstName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.favouriteTeam = userDTO.getFavouriteTeam();
        this.birthDay = userDTO.getBirthDay();
        this.age = userDTO.getAge();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.createdAt = userDTO.getCreatedAt();
        this.updatedAt = userDTO.getUpdatedAt();
    }

    public User(User user, UserDto userDTO) {
        this.id = user.getId();
        this.username = userDTO.getUsername() == null ? user.getUsername() : userDTO.getUsername();
        this.lastName = userDTO.getLastName() == null ? user.getLastName() : userDTO.getLastName();
        this.firstName = userDTO.getFirstName() == null ? user.getFirstName() : userDTO.getFirstName();
        this.email = userDTO.getEmail() == null ? user.getEmail() : userDTO.getEmail();
        this.password = userDTO.getPassword() == null ? user.getPassword() : userDTO.getPassword();
        this.favouriteTeam = userDTO.getFavouriteTeam() == null ? user.getFavouriteTeam() : userDTO.getFavouriteTeam();
        this.birthDay = userDTO.getBirthDay() == null ? user.getBirthDay() : userDTO.getBirthDay();
        this.age = userDTO.getAge() == null ? user.getAge() : userDTO.getAge();
        this.phoneNumber = userDTO.getPhoneNumber() == null ? user.getPhoneNumber() : userDTO.getPhoneNumber();
        this.createdAt = userDTO.getCreatedAt() == null ? user.getCreatedAt() : userDTO.getCreatedAt();
        this.updatedAt = userDTO.getUpdatedAt() == null ? user.getUpdatedAt() : userDTO.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return checkEquals(id, user.id) &&
                checkEquals(username, user.username) &&
                checkEquals(lastName, user.lastName) &&
                checkEquals(firstName, user.firstName) &&
                checkEquals(email, user.email) &&
                checkEquals(password, user.password) &&
                checkEquals(favouriteTeam, user.favouriteTeam) &&
                checkEquals(birthDay, user.birthDay) &&
                checkEquals(age, user.age) &&
                checkEquals(phoneNumber, user.phoneNumber) &&
                checkEquals(createdAt, user.createdAt) &&
                checkEquals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastName, firstName, email, password, favouriteTeam, birthDay, age, phoneNumber, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }
}
