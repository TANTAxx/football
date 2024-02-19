package hu.football.models.entities.users;

import hu.football.models.dto.users.FeedDto;
import hu.football.models.dto.users.InfoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news_feeds", schema = "admin")
public class Feed {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @NotNull
    private String lang;

    @NotNull
    private String link;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Feed(FeedDto feedDto) {
        this.id = feedDto.getId();
        this.text = feedDto.getText();
        this.lang = feedDto.getLang();
        this.link = feedDto.getLink();
        this.startDate = feedDto.getStartDate();
        this.endDate = feedDto.getEndDate();
        this.createdAt = feedDto.getCreatedAt();
        this.updatedAt = feedDto.getUpdatedAt();
    }

    public Feed(Feed feed, FeedDto feedDto) {
        this.id = feed.getId();
        this.text = feedDto.getText() == null ? feed.getText() : feedDto.getText();
        this.lang = feedDto.getLang() == null ? feed.getLang() : feedDto.getLang();
        this.link = feedDto.getLink() == null ? feed.getLink() : feedDto.getLink();
        this.startDate = feedDto.getStartDate() == null ? feed.getStartDate() : feedDto.getStartDate();
        this.endDate = feedDto.getEndDate() == null ? feed.getEndDate() : feedDto.getEndDate();
        this.createdAt = feedDto.getCreatedAt() == null ? feed.getCreatedAt() : feedDto.getCreatedAt();
        this.updatedAt = feedDto.getUpdatedAt() == null ? feed.getUpdatedAt() : feedDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feed feed = (Feed) o;
        return checkEquals(id, feed.id) &&
                checkEquals(text, feed.text) &&
                checkEquals(link, feed.link) &&
                checkEquals(lang, feed.lang) &&
                checkEquals(startDate, feed.startDate) &&
                checkEquals(endDate, feed.endDate) &&
                checkEquals(createdAt, feed.createdAt) &&
                checkEquals(updatedAt, feed.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, link, lang, startDate, endDate, createdAt, updatedAt);
    }

    private boolean checkEquals(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null) {
            return a.equals(b);
        }
        return false;
    }
}
