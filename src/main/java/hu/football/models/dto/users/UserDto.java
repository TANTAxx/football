package hu.football.models.dto.users;

import hu.football.models.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String address;
    private String favouriteTeam;
    private String birthDay;
    private String age;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDto(User user){
        if(user.getId() == null) return;
        this.id = user.getId();
        this.username = user.getUsername();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = user.getAddress().getAddress();
        this.favouriteTeam = user.getFavouriteTeam();
        this.birthDay = user.getBirthDay();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
