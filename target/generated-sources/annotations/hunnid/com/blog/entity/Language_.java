package hunnid.com.blog.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Language.class)
public abstract class Language_ {

	public static volatile SingularAttribute<Language, String> name;
	public static volatile SingularAttribute<Language, UUID> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}
