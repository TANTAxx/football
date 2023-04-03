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
@Table(name = "coachs")
public class Coach {
    @Id
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
    private String position = "Not Empty";

    @JsonIgnore
    private String height;

    @JsonIgnore
    private String weight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamName")
    private Team team;


    public Coach(String firstName,
                 String lastName,
                 String nationality,
                 String age,
                 String placeOfBirth,
                 String countryOfBirth,
                 String dateOfBirth,
                 Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
    }
}
