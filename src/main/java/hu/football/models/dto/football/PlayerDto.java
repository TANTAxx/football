package hu.football.models.dto.football;

import hu.football.enums.PlayerFootEnum;
import hu.football.enums.PlayerPositionEnum;
import hu.football.models.entities.football.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

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
    private Long teamId;
    private Long leagueId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlayerDto(Player player){
        if(player.getId() == null) return;
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.nationality = player.getNationality();
        this.dateOfBirth = player.getDateOfBirth();
        this.age = player.getAge();
        this.countryOfBirth = player.getCountryOfBirth();
        this.placeOfBirth = player.getPlaceOfBirth();
        this.position = player.getPosition();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.foot = player.getFoot();
        this.teamId = player.getTeamId().getId();
        this.leagueId = player.getLeagueId().getId();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
    }
}
