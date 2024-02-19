package hu.football.models.entities.football;

import hu.football.enums.PlayerFootEnum;
import hu.football.enums.PlayerPositionEnum;
import hu.football.models.dto.football.PlayerDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private String age;
    private String countryOfBirth;
    private String placeOfBirth;
    private PlayerPositionEnum position;
    private String height;
    private String weight;
    private PlayerFootEnum foot;
    private PlayerPositionEnum otherPosition;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamId;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League leagueId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Player(PlayerDto playerDto) {
        this.id = playerDto.getId();
        this.firstName = playerDto.getFirstName();
        this.lastName = playerDto.getLastName();
        this.nationality = playerDto.getNationality();
        this.dateOfBirth = playerDto.getDateOfBirth();
        this.age = playerDto.getAge();
        this.countryOfBirth = playerDto.getCountryOfBirth();
        this.placeOfBirth = playerDto.getPlaceOfBirth();
        this.position = playerDto.getPosition();
        this.height = playerDto.getHeight();
        this.weight = playerDto.getWeight();
        this.foot = playerDto.getFoot();
        this.createdAt = playerDto.getCreatedAt();
        this.updatedAt = playerDto.getUpdatedAt();
    }

    public Player(Player player, PlayerDto playerDto) {
        this.id = player.getId();
        this.firstName = playerDto.getFirstName() == null ? player.getFirstName() : playerDto.getFirstName();
        this.lastName = playerDto.getLastName() == null ? player.getLastName() : playerDto.getLastName();
        this.nationality = playerDto.getNationality() == null ? player.getNationality() : playerDto.getNationality();
        this.dateOfBirth = playerDto.getDateOfBirth() == null ? player.getDateOfBirth() : playerDto.getDateOfBirth();
        this.age = playerDto.getAge() == null ? player.getAge() : playerDto.getAge();
        this.countryOfBirth = playerDto.getCountryOfBirth() == null ? player.getCountryOfBirth() : playerDto.getCountryOfBirth();
        this.placeOfBirth = playerDto.getPlaceOfBirth() == null ? player.getPlaceOfBirth() : playerDto.getPlaceOfBirth();
        this.position = playerDto.getPosition() == null ? player.getPosition() : playerDto.getPosition();
        this.height = playerDto.getHeight() == null ? player.getHeight() : playerDto.getHeight();
        this.weight = playerDto.getWeight() == null ? player.getWeight() : playerDto.getWeight();
        this.foot = playerDto.getFoot() == null ? player.getFoot() : playerDto.getFoot();
        this.createdAt = playerDto.getCreatedAt() == null ? player.getCreatedAt() : playerDto.getCreatedAt();
        this.updatedAt = playerDto.getUpdatedAt() == null ? player.getUpdatedAt() : playerDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return checkEquals(id, player.id) &&
                checkEquals(firstName, player.firstName) &&
                checkEquals(lastName, player.lastName) &&
                checkEquals(nationality, player.nationality) &&
                checkEquals(dateOfBirth, player.dateOfBirth) &&
                checkEquals(age, player.age) &&
                checkEquals(countryOfBirth, player.countryOfBirth) &&
                checkEquals(placeOfBirth, player.placeOfBirth) &&
                checkEquals(position, player.position) &&
                checkEquals(height, player.height) &&
                checkEquals(weight, player.weight) &&
                checkEquals(foot, player.foot) &&
                checkEquals(createdAt, player.createdAt) &&
                checkEquals(updatedAt, player.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationality, firstName, lastName, nationality, dateOfBirth, age, countryOfBirth, placeOfBirth, position, height, weight, foot, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }
}
