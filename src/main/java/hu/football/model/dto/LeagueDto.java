package hu.football.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {
    private String numberOfTeams;
    private String leagueNationality;
    private String leagueName;
}
