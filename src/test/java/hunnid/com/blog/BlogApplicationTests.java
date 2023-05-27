package hunnid.com.blog;

import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationString;
import hunnid.com.blog.repository.PostRepository;
import hunnid.com.blog.service.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private EntityManager entityManager;
	 
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Test
	@Transactional
	public void testSearch(){
	}

}
