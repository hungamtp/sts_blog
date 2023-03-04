package hunnid.com.blog;

import hunnid.com.blog.entity.TranslationString;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.scope.SearchScope;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private EntityManager entityManager;

	@Test
	@Transactional
	void contextLoads() throws InterruptedException {
		SearchSession searchSession = Search.session( entityManager );

		MassIndexer indexer = searchSession.massIndexer( TranslationString.class )
				.threadsToLoadObjects( 7 );

		indexer.startAndWait();

		SearchScope<TranslationString> scope = searchSession.scope( TranslationString.class );

		SearchResult<TranslationString> result = searchSession.search( scope )
				.where( scope.predicate().match()
						.fields( "translatedString")
						.matching( "string" )
						.toPredicate() )
				.fetch( 20 );

		long totalHitCount = result.total().hitCount();
		List<TranslationString> hits = result.hits();
		System.out.println(totalHitCount);
	}

}
