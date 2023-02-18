package hunnid.com.blog.entity;

import hunnid.com.blog.util.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(
    uniqueConstraints = {},
    indexes = {
        @Index(name = "fn_index_hidden", columnList = "hidden"),
    })
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;
    private boolean hidden;
    private String coverImage;
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @PrePersist
    public void defaultValue() {
        if (Objects.isNull(createdAt)) {
            createdAt = DateUtils.getLocalDateTimeByTimeZone();
        }
    }

}
