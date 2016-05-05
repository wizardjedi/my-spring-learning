package learn.sprofile;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
@ActiveProfiles(profiles = {"test","mysql"})
public class AppTest extends TestCase {

	@Autowired
	public Service service;

	@Test
	public void testData() {
		assertEquals(MySQLDAO.class.getName(), service.getData());
	}

	@Test
	public void testRequest() {
		assertEquals(HttpFakeRequester.class.getName(), service.serve());
	}
}
