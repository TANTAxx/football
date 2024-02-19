package hu.football.models.dto.users;

import hu.football.models.entities.users.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {

    private Long id;
    private String infoText;
    private String title;
    private String description;
    private Boolean active;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InfoDto(Info info) {
        if (info.getId() == null) {
            return;
        }
        this.id = info.getId();
        this.infoText = info.getInfoText();
        this.title = info.getTitle();
        this.description = info.getDescription();
        this.active = info.getActive();
        this.startAt = info.getStartAt();
        this.endAt = info.getEndAt();
        this.createdAt = info.getCreatedAt();
        this.updatedAt = info.getUpdatedAt();
    }
}
