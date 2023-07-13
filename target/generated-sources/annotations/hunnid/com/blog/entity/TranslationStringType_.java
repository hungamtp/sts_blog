package hunnid.com.blog.entity;

import hunnid.com.blog.enums.TranslationStringTypeEnum;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TranslationStringType.class)
public abstract class TranslationStringType_ {

	public static volatile SingularAttribute<TranslationStringType, UUID> id;
	public static volatile SingularAttribute<TranslationStringType, TranslationStringTypeEnum> type;

	public static final String ID = "id";
	public static final String TYPE = "type";

}

