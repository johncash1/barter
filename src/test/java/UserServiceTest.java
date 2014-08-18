import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xware.barter.*;
import com.xware.barter.action.UserService;
import com.xware.barter.domain.Contact;
import com.xware.barter.domain.Item;
import com.xware.barter.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class UserServiceTest {
    private User u;
    @Autowired
    UserService userService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String type ="offered" ;
		u= new User();
		Item i = new Item("some crap");
	//	i.setId(1);
		i.setUser(u);
		i.setType("wanted");
		i.setValue("value");
		Item item = new Item();
		
		item.setType(type);
		item.setUser(u);
		item.setValue(" Item 1 value");
		type ="wanted";
		Item item2 = new Item();
		
		item2.setType(type);
		item2.setUser(u);
		item2.setValue(" Item 2 value");
		
		List<Item> il = new ArrayList<Item>();
		il.add(i);
		il.add(item);
		il.add(item2);
		Contact c = new Contact();
	//	c.setId(1);
		c.setCountry("us");
		c.setEmail("testeami@email.com");
		c.setFacebook("faceooktest");
		c.setGoogleplus("googleplus");
		c.setPhone("415-444-4444");
		c.setPhone2(c.getPhone());
		c.setPostcode("1234");
		c.setStreet1("street1");
		c.setStreet2("street2");
		c.setRegion("region");

		u.setUname("test name");
		u.setContact(c);
		u.setItemList(il);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateUser() {
		if (u.getId()==null)
		userService.add(u);
		 assertTrue(u.getId() != null);
		assertEquals( u.getUname(),"test name");
		 u.setUname("bob");
		userService.updateUser(u);
		assertEquals(userService.getUser(u.getId()).getUname(),"bob");
		assertTrue(!(userService.getUser(u.getId()).getUname().equals("test name")));
	}

	@Test
	public void testRemoveUser() {
		if (u.getId()==null)
		userService.add(u);
		assertFalse(u.getId().equals(null));
		boolean b=userService.removeUser(u.getId());
		assertTrue(b);
	}

	@Test
	public void testAdd() {
		assertTrue(u.getId() ==null);
		userService.add(u);
		assertFalse(u.getId().equals(null));
	}

	@Test
	public void testGetUser() {
		if (u.getId()==null)
		userService.add(u);
		assertFalse(u.getId().equals(null));
		
		User u2 = userService.getUser(u.getId());
		assertTrue(u2 != null);
	}

}
