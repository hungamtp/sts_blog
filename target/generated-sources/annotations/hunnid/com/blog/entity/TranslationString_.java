package hunnid.com.blog.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TranslationString.class)
public abstract class TranslationString_ {

	public static volatile SingularAttribute<TranslationString, String> translatedString;
	public static volatile SingularAttribute<TranslationString, Language> language;
	public static volatile SingularAttribute<TranslationString, UUID> id;
	public static volatile SingularAttribute<TranslationString, TranslationStringType> type;

	public static final String TRANSLATED_STRING = "translatedString";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

