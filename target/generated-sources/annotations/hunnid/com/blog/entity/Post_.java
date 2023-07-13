package hunnid.com.blog.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, LocalDateTime> createdAt;
	public static volatile SetAttribute<Post, TranslationString> translatedStrings;
	public static volatile SingularAttribute<Post, Boolean> hidden;
	public static volatile SingularAttribute<Post, String> coverImage;
	public static volatile SingularAttribute<Post, UUID> id;
	public static volatile ListAttribute<Post, ViewPost> views;
	public static volatile SetAttribute<Post, Tag> tags;

	public static final String CREATED_AT = "createdAt";
	public static final String TRANSLATED_STRINGS = "translatedStrings";
	public static final String HIDDEN = "hidden";
	public static final String COVER_IMAGE = "coverImage";
	public static final String ID = "id";
	public static final String VIEWS = "views";
	public static final String TAGS = "tags";

}

