package hu.football.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.football.model.entities.Team;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {
    private String firstName;

    private String lastName;

    private String nationality;

    private String dateOfBirth;

    private String age;

    private String countryOfBirth;

    private String placeOfBirth;

    private String team;

}
