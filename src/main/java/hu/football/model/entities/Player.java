package hu.football.model.entities;

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
    private LocalDate dateOfBirth;

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

    @NotNull
    @NotBlank
    @OneToOne
    private Team team;

}
