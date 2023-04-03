package hu.football.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "nationality")
    @OneToOne(cascade = CascadeType.ALL)
    private League nationality;

    private String teamName;

    private String founded;

    @JsonIgnore
    private String address;

    private String stadium;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_name")
    private League league;


    public Team(League nationality, String teamName, String founded, String address, String stadium, League league) {
        this.nationality = nationality;
        this.teamName = teamName;
        this.founded = founded;
        this.address = address;
        this.stadium = stadium;
        this.league = league;
    }


}
