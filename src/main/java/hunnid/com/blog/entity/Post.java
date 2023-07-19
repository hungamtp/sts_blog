package hunnid.com.blog.entity;

import hunnid.com.blog.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;

import javax.persistence.Index;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(
    name = "post",
    uniqueConstraints = {},
    indexes = {
        @Index(name = "fn_index_hidden", columnList = "hidden"),
    })
@Indexed
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Field(index= org.hibernate.search.annotations.Index.YES, store=Store.NO)
    @SortableField
    private boolean hidden;
    private String coverImage;
    
    @Field(index= org.hibernate.search.annotations.Index.YES, store=Store.NO)
    @SortableField
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @IndexedEmbedded(includePaths = {"translatedString","language.name"})
    private Set<TranslationString> translatedStrings;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @IndexedEmbedded(includePaths = {"id"})
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id" , referencedColumnName = "id")
    private List<ViewPost> views;
    
    @PrePersist
    public void defaultValue() {
        if (Objects.isNull(createdAt)) {
            createdAt = DateUtils.getLocalDateTimeByTimeZone(DateUtils.TIME_ZONE_HCM);
        }
    }

}
