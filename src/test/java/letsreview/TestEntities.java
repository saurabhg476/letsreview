package letsreview;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saurabh.letsreview.datamodel.entity.Review;
import com.saurabh.letsreview.datamodel.entity.Topic;
import com.saurabh.letsreview.datamodel.entity.User;
import com.saurabh.letsreview.datamodel.repository.ReviewDAOService;
import com.saurabh.letsreview.datamodel.repository.TopicDAOService;
import com.saurabh.letsreview.datamodel.repository.UserDAOService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext*.xml" })
public class TestEntities {

	@Autowired
	private UserDAOService userDAO;

	@Autowired
	private TopicDAOService topicDAO;

	@Autowired
	private ReviewDAOService reviewDAO;

	private static final Logger LOG = LoggerFactory.getLogger(TestEntities.class);

	@Test
	public void testUserEntity() {
		List<User> users = userDAO.findAll();
		for (User user : users) {
			System.out.println("Name = " + user.getName());
			System.out.println("Email Id: " + user.getEmailId());
		}
	}

	@Test
	public void testTopicEntity() {
		List<Topic> topics = topicDAO.findAll();
		for (Topic topic : topics) {
			System.out.println(topic.getName());
		}
	}

	@Test
	public void reviewDAOService() {
		List<Review> reviews = reviewDAO.findAll();
		for (Review review : reviews) {
			System.out.println(review.getBody());
			System.out.println(review.getCreatedOn().toString());
			System.out.println(review.getUser().getName());
			System.out.println(review.getTopic().getName());
		}
	}
}
