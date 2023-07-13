package hunnid.com.blog.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ViewPost.class)
public abstract class ViewPost_ {

	public static volatile SingularAttribute<ViewPost, LocalDateTime> viewedAt;
	public static volatile SingularAttribute<ViewPost, UUID> id;

	public static final String VIEWED_AT = "viewedAt";
	public static final String ID = "id";

}

