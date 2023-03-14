package hunnid.com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

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
@NamedEntityGraph(name = "TranslationString.type",
        attributeNodes = {
                @NamedAttributeNode("type")
        }
)
@Indexed
public class TranslationString {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    
    @FullTextField
    @Type(type="text")
    private String translatedString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TranslationStringType type;
}
