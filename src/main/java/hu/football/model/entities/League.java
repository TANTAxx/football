package hu.football.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "league_name")
    private String leagueName;

    public League(String leagueName, String nationality, String numberOfTeams) {
        this.leagueName = leagueName;
        this.nationality = nationality;
        this.numberOfTeams = numberOfTeams;
    }
}
