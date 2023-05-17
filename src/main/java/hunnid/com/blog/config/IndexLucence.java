package hunnid.com.blog.config;

import hunnid.com.blog.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.MassIndexerProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class IndexLucence implements MassIndexerProgressMonitor {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @EventListener(ApplicationStartedEvent.class)
    @SneakyThrows
    public void indexingPost() {
        log.info("Rebuild index");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().progressMonitor(this).startAndWait();
        log.info("Rebuilding index finished");
    }

    @SneakyThrows
    @Transactional
    public void reindex(boolean purge) {
        log.info("Trigger reIndex");
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager
                .createIndexer(Post.class)
                .purgeAllOnStart(purge)
                .batchSizeToLoadObjects(25)
                .cacheMode(CacheMode.NORMAL)
                .threadsToLoadObjects(1)
                .idFetchSize(150)
                .transactionTimeout(1800)
                .progressMonitor(this)
                .optimizeAfterPurge(purge)
                .startAndWait();
    }

    @Override
    public void documentsBuilt(int i) {

    }

    @Override
    public void entitiesLoaded(int i) {

    }

    @Override
    public void addToTotalCount(long l) {

    }

    @Override
    public void indexingCompleted() {

    }

    @Override
    public void documentsAdded(long l) {

    }
}
