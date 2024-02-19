package hu.football.models.dto.football;

import hu.football.models.entities.football.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
    private String nationality;
    private String teamName;
    private String founded;
    private String address;
    private String stadium;
    private Long leagueId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TeamDto(Team team) {
        if (team.getId() == null) return;
        this.id = team.getId();
        this.nationality = team.getNationality();
        this.teamName = team.getTeamName();
        this.founded = team.getFounded();
        this.address = team.getAddress();
        this.stadium = team.getStadium();
        this.leagueId = team.getLeagueId().getId();
        this.createdAt = team.getCreatedAt();
        this.updatedAt = team.getUpdatedAt();
    }
}
