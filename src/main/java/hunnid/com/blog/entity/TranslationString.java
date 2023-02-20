package hunnid.com.blog.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(
    name = "translation_string",
    uniqueConstraints = {},
    indexes = {
        @Index(name = "fk_index_language_id", columnList = "language_id"),
        @Index(name = "fk_index_type_id", columnList = "type_id")
    })
public class TranslationString {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    private String translatedString;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TranslationStringType type;
}
