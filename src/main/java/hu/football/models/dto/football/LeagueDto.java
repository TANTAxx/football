package hu.football.models.dto.football;

import hu.football.models.entities.football.League;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {
    private Long id;
    private String numberOfTeams;

    private String players;

    private String nationality;

    private String leagueLevel;

    private String reigningChampion;

    private String recordHoldingChampions;

    private String name;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    public LeagueDto(League league) {
        if (league.getId() == null) return;
        this.id = league.getId();
        this.numberOfTeams = league.getNumberOfTeams();
        this.players = league.getPlayers();
        this.nationality = league.getNationality();
        this.leagueLevel = league.getLeagueLevel();
        this.reigningChampion = league.getReigningChampion();
        this.recordHoldingChampions = league.getRecordHoldingChampions();
        this.name = league.getName();
        this.createdAt = league.getCreatedAt();
        this.updatedAt = league.getUpdatedAt();
    }

}
