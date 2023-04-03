package hu.football.model.dto;

import hu.football.model.entities.Team;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private String firstName;

    private String lastName;

    private String nationality;

    private String dateOfBirth;

    private String age;

    private String countryOfBirth;

    private String placeOfBirth;

    private String position;

    private String height;

    private String weight;

    private String foot;

    private String team;
}
