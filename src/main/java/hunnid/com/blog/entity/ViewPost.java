package hunnid.com.blog.entity;


import com.amazonaws.transform.MapEntry;
import hunnid.com.blog.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "view_post")
public class ViewPost {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    private LocalDateTime viewedAt;

    @PrePersist
    public void defaultValue() {
        if (Objects.isNull(viewedAt)) {
            viewedAt = DateUtils.getLocalDateTimeByTimeZone(DateUtils.TIME_ZONE_HCM);
        }
    }
}
