package hunnid.com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.persistence.Index;
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
@AnalyzerDef(name = "titleAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class)
        })
public class TranslationString {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    

    @Type(type="text")
    @Field(index= org.hibernate.search.annotations.Index.YES, store=Store.NO)
    @Analyzer(definition = "titleAnalyzer")
    private String translatedString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TranslationStringType type;
}
