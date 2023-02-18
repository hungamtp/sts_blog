package hunnid.com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(
    name = "translation_string",
    uniqueConstraints = {},
    indexes = {
        @Index(name = "fk_index_language_id", columnList = "language_id"),
        @Index(name = "fk_index_post_id", columnList = "post_id"),
        @Index(name = "fk_index_type_id", columnList = "type_id")
    })
public class TranslationString {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID id;

    private String translatedString;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TranslationStringType type;
}
