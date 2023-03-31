package hu.football.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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


}
