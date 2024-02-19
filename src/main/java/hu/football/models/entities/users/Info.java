package hu.football.models.entities.users;

import hu.football.models.dto.users.InfoDto;
import hu.football.models.dto.users.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "infos", schema = "admin")
public class Info {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String infoText;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Boolean active;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //    public Info(String infoText, String title, String description, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt) {
    //        this.infoText = infoText;
    //        this.title = title;
    //        this.description = description;
    //        this.startAt = startAt;
    //        this.endAt = endAt;
    //        this.createdAt = createdAt;
    //    }

    public Info(InfoDto infoDto) {
        this.id = infoDto.getId();
        this.infoText = infoDto.getInfoText();
        this.title = infoDto.getTitle();
        this.description = infoDto.getDescription();
        this.active = infoDto.getActive();
        this.startAt = infoDto.getStartAt();
        this.endAt = infoDto.getEndAt();
        this.createdAt = infoDto.getCreatedAt();
        this.updatedAt = infoDto.getUpdatedAt();
    }

    public Info(Info info, InfoDto infoDto) {
        this.id = info.getId();
        this.infoText = infoDto.getInfoText() == null ? info.getInfoText() : infoDto.getInfoText();
        this.title = infoDto.getTitle() == null ? info.getInfoText() : infoDto.getInfoText();
        this.description = infoDto.getDescription() == null ? info.getInfoText() : infoDto.getInfoText();
        this.active = infoDto.getActive() == null ? info.getActive() : infoDto.getActive();
        this.startAt = infoDto.getStartAt() == null ? info.getStartAt() : infoDto.getStartAt();
        this.endAt = infoDto.getEndAt() == null ? info.getEndAt() : infoDto.getEndAt();
        this.createdAt = infoDto.getCreatedAt() == null ? info.getCreatedAt() : infoDto.getCreatedAt();
        this.updatedAt = infoDto.getUpdatedAt() == null ? info.getUpdatedAt() : infoDto.getUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return checkEquals(id, info.id) &&
                checkEquals(infoText, info.infoText) &&
                checkEquals(title, info.title) &&
                checkEquals(description, info.description) &&
                checkEquals(active, info.active) &&
                checkEquals(startAt, info.startAt) &&
                checkEquals(endAt, info.endAt) &&
                checkEquals(createdAt, info.createdAt) &&
                checkEquals(updatedAt, info.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infoText, title, description, active, startAt, endAt, createdAt, updatedAt);
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
