package hu.football.models.entities.football;


import hu.football.models.dto.football.CoachDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "coachs")
public class Coach {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String dateOfBirth;
    private String age;
    private String countryOfBirth;
    private String placeOfBirth;
    private String position;
    private String height;
    private String weight;

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


    public Coach(CoachDto coachDto) {
        this.id = coachDto.getId();
        this.firstName = coachDto.getFirstName();
        this.lastName = coachDto.getLastName();
        this.nationality = coachDto.getNationality();
        this.dateOfBirth = coachDto.getDateOfBirth();
        this.age = coachDto.getAge();
        this.countryOfBirth = coachDto.getCountryOfBirth();
        this.placeOfBirth = coachDto.getPlaceOfBirth();
        this.position = coachDto.getPosition();
        this.height = coachDto.getHeight();
        this.weight = coachDto.getWeight();
        this.createdAt = coachDto.getCreatedAt();
        this.updatedAt = coachDto.getUpdatedAt();
    }

    public Coach(Coach coach, CoachDto coachDto) {
        this.id = coach.getId();
        this.firstName = coachDto.getFirstName() == null ? coach.getFirstName() : coachDto.getFirstName();
        this.lastName = coachDto.getLastName() == null ? coach.getLastName() : coachDto.getLastName();
        this.nationality = coachDto.getNationality() == null ? coach.getNationality() : coachDto.getNationality();
        this.dateOfBirth = coachDto.getDateOfBirth() == null ? coach.getDateOfBirth() : coachDto.getDateOfBirth();
        this.age = coachDto.getAge() == null ? coach.getAge() : coachDto.getAge();
        this.countryOfBirth = coachDto.getCountryOfBirth() == null ? coach.getCountryOfBirth() : coachDto.getCountryOfBirth();
        this.placeOfBirth = coachDto.getPlaceOfBirth() == null ? coach.getPlaceOfBirth() : coachDto.getPlaceOfBirth();
        this.position = coachDto.getPosition() == null ? coach.getPosition() : coachDto.getPosition();
        this.height = coachDto.getHeight() == null ? coach.getHeight() : coachDto.getHeight();
        this.weight = coachDto.getWeight() == null ? coach.getWeight() : coachDto.getWeight();
        this.createdAt = coachDto.getCreatedAt() == null ? coach.getCreatedAt() : coachDto.getCreatedAt();
        this.updatedAt = coachDto.getUpdatedAt() == null ? coach.getUpdatedAt() : coachDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return checkEquals(id, coach.id) &&
                checkEquals(firstName, coach.firstName) &&
                checkEquals(lastName, coach.lastName) &&
                checkEquals(nationality, coach.nationality) &&
                checkEquals(dateOfBirth, coach.dateOfBirth) &&
                checkEquals(age, coach.age) &&
                checkEquals(countryOfBirth, coach.countryOfBirth) &&
                checkEquals(placeOfBirth, coach.placeOfBirth) &&
                checkEquals(position, coach.position) &&
                checkEquals(height, coach.height) &&
                checkEquals(weight, coach.weight) &&
                checkEquals(createdAt, coach.createdAt) &&
                checkEquals(updatedAt, coach.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationality, firstName, lastName, nationality, dateOfBirth, age, countryOfBirth, placeOfBirth, position, height, weight, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }
}
