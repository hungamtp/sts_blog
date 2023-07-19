package hunnid.com.blog.repository.custom;

import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.Post_;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{
    private final EntityManager entityManager;
    @Override
    public List<Post> search(List<UUID> tags, String searchKeyWord, int page , int size,String language) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Post.class)
                .get();
        BooleanJunction finalQuery = queryBuilder.bool();
        finalQuery.must(
                        queryBuilder.keyword()
                                .onField(Post_.HIDDEN)
                                .matching("false")
                                .createQuery()
                );

        if (Strings.isNotBlank(searchKeyWord)) {
            finalQuery.must(queryBuilder.keyword()
                    .onField("translatedStrings.translatedString")
                    .matching(searchKeyWord)
                    .createQuery());
        }

        finalQuery.must(queryBuilder.keyword()
                .onField("translatedStrings.language.name")
                .matching(language)
                .createQuery());

        if (CollectionUtils.isNotEmpty(tags)) {
            BooleanJunction creatorQueries = queryBuilder.bool();
            for (var creatorId : tags) {
                creatorQueries.should(queryBuilder
                        .keyword()
                        .onField("tags.id")
                        .matching(creatorId)
                        .createQuery());
            }
            finalQuery.must(creatorQueries.createQuery());
        }


        return fullTextEntityManager.createFullTextQuery(finalQuery.createQuery(), Post.class)
                .setMaxResults(size)
                .setFirstResult(page * size)
                .getResultList();
    }

    @Override
    public Integer searchCount(List<UUID> tags, String searchKeyWord, int page, int size) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Post.class)
                .get();
        BooleanJunction finalQuery = queryBuilder.bool();
        finalQuery.must(
                queryBuilder.keyword()
                        .onField(Post_.HIDDEN)
                        .matching("false")
                        .createQuery()
        );

        if (Strings.isNotBlank(searchKeyWord)) {
            finalQuery.must(queryBuilder.keyword()
                    .onField("translatedStrings.translatedString")
                    .matching(searchKeyWord)
                    .createQuery());
        }

        if (CollectionUtils.isNotEmpty(tags)) {
            BooleanJunction creatorQueries = queryBuilder.bool();
            for (var creatorId : tags) {
                creatorQueries.should(queryBuilder
                        .keyword()
                        .onField("tags.id")
                        .matching(creatorId)
                        .createQuery());
            }
            finalQuery.must(creatorQueries.createQuery());
        }


        return fullTextEntityManager.createFullTextQuery(finalQuery.createQuery(), Post.class)
                .getResultSize();
    }
}
