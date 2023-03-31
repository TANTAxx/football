package hu.football.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private String nationality;

    private String teamName;

    private String founded;

    private String address;

    private String stadium;

    private String league;

    private LeagueDto leagueDto;

}
