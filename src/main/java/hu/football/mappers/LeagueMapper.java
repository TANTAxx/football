package hu.football.mappers;

import hu.football.model.dto.LeagueDto;
import hu.football.model.entities.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LeagueMapper {


    @Mapping(target = "leagueNationality", source = "nationality")
    LeagueDto toDto(League league);

    @Mapping(target = "nationality",source = "leagueNationality")
    League toEntity(LeagueDto leagueDto);
}
