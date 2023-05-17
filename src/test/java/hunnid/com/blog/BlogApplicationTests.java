package hunnid.com.blog;

import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationString;
import hunnid.com.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private EntityManager entityManager;
	 
	@Autowired
	private PostRepository postRepository;
	
	@Test
	@Transactional
	public void testSearch(){
		List<Post> posts = postRepository.search(new ArrayList<>() , "title" , 0 , 10);
		System.out.println("hung");
	}

}
