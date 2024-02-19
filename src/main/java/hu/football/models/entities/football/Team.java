package hu.football.models.entities.football;

import hu.football.models.dto.football.TeamDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.*;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    private String nationality;
    private String teamName;
    private String founded;
    private String address;
    private String stadium;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id")
    private League leagueId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Team(String nationality, String teamName, String founded, String address, String stadium, League leagueId) {
        this.nationality = nationality;
        this.teamName = teamName;
        this.founded = founded;
        this.address = address;
        this.stadium = stadium;
        this.leagueId = leagueId;
    }

    public Team(TeamDto teamDto) {
        this.id = teamDto.getId();
        this.nationality = teamDto.getNationality();
        this.teamName = teamDto.getTeamName();
        this.founded = teamDto.getFounded();
        this.address = teamDto.getAddress();
        this.stadium = teamDto.getStadium();
        this.createdAt = teamDto.getCreatedAt();
        this.updatedAt = teamDto.getUpdatedAt();
    }

    public Team(Team team, TeamDto teamDto) {
        this.id = team.getId();
        this.nationality = teamDto.getNationality() == null ? team.getNationality() : teamDto.getNationality();
        this.teamName = teamDto.getTeamName() == null ? team.getTeamName() : teamDto.getTeamName();
        this.founded = teamDto.getFounded() == null ? team.getFounded() : teamDto.getFounded();
        this.address = teamDto.getAddress() == null ? team.getAddress() : teamDto.getAddress();
        this.stadium = teamDto.getStadium() == null ? team.getStadium() : teamDto.getStadium();
        this.createdAt = teamDto.getCreatedAt() == null ? team.getCreatedAt() : teamDto.getCreatedAt();
        this.updatedAt = teamDto.getUpdatedAt() == null ? team.getUpdatedAt() : teamDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return checkEquals(id, team.id) &&
                checkEquals(nationality, team.nationality) &&
                checkEquals(teamName, team.teamName) &&
                checkEquals(founded, team.founded) &&
                checkEquals(address, team.address) &&
                checkEquals(stadium, team.stadium) &&
                checkEquals(createdAt, team.createdAt) &&
                checkEquals(updatedAt, team.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationality, teamName, founded, address, stadium, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }
}
