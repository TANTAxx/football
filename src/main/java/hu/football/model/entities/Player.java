package hu.football.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String nationality;

    @NotNull
    @NotBlank
    private String dateOfBirth;

    @NotNull
    @NotBlank
    private String age;

    @NotNull
    @NotBlank
    private String countryOfBirth;

    @NotNull
    @NotBlank
    private String placeOfBirth;

    @NotNull
    @NotBlank
    private String position;

    @NotNull
    @NotBlank
    private String height;

    @NotNull
    @NotBlank
    private String weight;

    @NotNull
    @NotBlank
    private String foot;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamName")
    private Team team;

    public Player(String firstName,
                  String lastName,
                  String nationality,
                  String dateOfBirth,
                  String age,
                  String countryOfBirth,
                  String placeOfBirth,
                  String position,
                  String height,
                  String weight,
                  String foot,
                  Team team)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.countryOfBirth = countryOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.foot = foot;
        this.team = team;
    }

}
