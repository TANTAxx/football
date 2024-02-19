package hu.football.models.dto.users;

import hu.football.models.entities.users.Feed;
import hu.football.models.entities.users.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {

    private Long id;
    private String text;
    private String lang;
    private String link;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedDto(Feed feed) {
        if (feed.getId() == null) {
            return;
        }
        this.id = feed.getId();
        this.text = feed.getText();
        this.lang = feed.getLang();
        this.link = feed.getLink();
        this.startDate = feed.getStartDate();
        this.endDate = feed.getEndDate();
        this.createdAt = feed.getCreatedAt();
        this.updatedAt = feed.getUpdatedAt();
    }
}
