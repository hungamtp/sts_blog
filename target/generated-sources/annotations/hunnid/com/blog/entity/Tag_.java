package hunnid.com.blog.entity;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ {

	public static volatile SingularAttribute<Tag, UUID> id;
	public static volatile SingularAttribute<Tag, String> tag;

	public static final String ID = "id";
	public static final String TAG = "tag";

}

