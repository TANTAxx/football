package hu.football.models.dto.football;

import hu.football.models.entities.football.Coach;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {

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
    private Long teamId;
    private Long leagueId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public CoachDto(Coach coach) {
        if(coach.getId() == null) return;
        this.id = coach.getId();
        this.firstName = coach.getFirstName();
        this.lastName = coach.getLastName();
        this.nationality = coach.getNationality();
        this.dateOfBirth = coach.getDateOfBirth();
        this.age = coach.getAge();
        this.countryOfBirth = coach.getCountryOfBirth();
        this.placeOfBirth = coach.getPlaceOfBirth();
        this.position = coach.getPosition();
        this.height = coach.getHeight();
        this.weight = coach.getWeight();
        this.teamId = coach.getTeamId().getId();
        this.leagueId = coach.getLeagueId().getId();
        this.createdAt = coach.getCreatedAt();
        this.updatedAt = coach.getUpdatedAt();
    }
}
