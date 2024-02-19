package hu.football.models.entities.football;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.football.models.dto.football.LeagueDto;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String numberOfTeams;

    private String players;

    private String nationality;

    private String leagueLevel;

    private String reigningChampion;

    private String recordHoldingChampions;

    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public League(String name, String nationality, String numberOfTeams) {
        this.name = name;
        this.nationality = nationality;
        this.numberOfTeams = numberOfTeams;
    }

    public League(LeagueDto leagueDto) {
        this.id = leagueDto.getId();
        this.numberOfTeams = leagueDto.getNumberOfTeams();
        this.players = leagueDto.getPlayers();
        this.nationality = leagueDto.getNationality();
        this.leagueLevel = leagueDto.getLeagueLevel();
        this.reigningChampion = leagueDto.getReigningChampion();
        this.recordHoldingChampions = leagueDto.getRecordHoldingChampions();
        this.name = leagueDto.getName();
        this.createdAt = leagueDto.getCreatedAt();
        this.updatedAt = leagueDto.getUpdatedAt();
    }

    public League(League league, LeagueDto leagueDto) {
        this.id = league.getId();
        this.numberOfTeams = leagueDto.getNumberOfTeams() == null ? league.getNumberOfTeams() : leagueDto.getNumberOfTeams();
        this.players = leagueDto.getPlayers() == null ? league.getPlayers() : leagueDto.getPlayers();
        this.nationality = leagueDto.getNationality() == null ? league.getNationality() : leagueDto.getNationality();
        this.leagueLevel = leagueDto.getLeagueLevel() == null ? league.getLeagueLevel() : leagueDto.getLeagueLevel();
        this.reigningChampion = leagueDto.getReigningChampion() == null ? league.getReigningChampion() : leagueDto.getReigningChampion();
        this.recordHoldingChampions = leagueDto.getRecordHoldingChampions() == null ? league.getRecordHoldingChampions() : leagueDto.getRecordHoldingChampions();
        this.name = leagueDto.getName() == null ? league.getName() : leagueDto.getName();
        this.createdAt = leagueDto.getCreatedAt() == null ? league.getCreatedAt() : leagueDto.getCreatedAt();
        this.updatedAt = leagueDto.getUpdatedAt() == null ? league.getUpdatedAt() : leagueDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return checkEquals(id, league.id) &&
                checkEquals(numberOfTeams, league.numberOfTeams) &&
                checkEquals(players, league.players) &&
                checkEquals(nationality, league.nationality) &&
                checkEquals(leagueLevel, league.leagueLevel) &&
                checkEquals(reigningChampion, league.reigningChampion) &&
                checkEquals(recordHoldingChampions, league.recordHoldingChampions) &&
                checkEquals(name, league.name) &&
                checkEquals(createdAt, league.createdAt) &&
                checkEquals(updatedAt, league.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfTeams, players, nationality, leagueLevel, reigningChampion, recordHoldingChampions, name, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }
}
