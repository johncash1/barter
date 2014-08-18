import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xware.barter.action.ContactService;
import com.xware.barter.action.ItemService;
import com.xware.barter.action.UserService;
import com.xware.barter.domain.Contact;
import com.xware.barter.domain.Item;
import com.xware.barter.domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class ItemServiceTest {
	private Item item ;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private UserService userService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		User user = new User();
		user.setUname("uname 1");
		
		String type ="offered";
		Contact contact = contactService.getContact(1);
		user.setContact(contact);
		
		item = new Item();
		item.setType(type);
		item.setUser(user);
		item.setValue(" Item 1 value");
		type ="wanted";
		Item item2 = new Item();
		item2 = new Item();
		item2.setType(type);
		item2.setUser(user);
		item2.setValue(" Item 2 value");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEditItem() {
		item.setQuantity(1);
		itemService.add(item);
		
		Integer i =item.getId();
		assertTrue( i !=null) ;
		assertTrue(item.getQuantity().equals(1));
		item.setQuantity(5);
		Item item2 = itemService.getItem(i);
		assertTrue(item2.getQuantity().equals(1));
		
		
	}

	@Test
	public void testRemoveItem() {
		if (item.getId() == null)
			itemService.add(item);;
		//Integer i =itemService.getItem(item.getId())
		itemService.removeItem(item.getId());
		Integer i =item.getId();
		assertEquals(itemService.getItem(i),null);
	}

	@Test
	public void testAdd() {
		itemService.add(item);
		assertTrue(item.getId()!=null) ;
	}

	@Test
	public void testGetItem() {
		if (item.getId() == null)
			itemService.add(item);
		assertTrue(itemService.getItem(item.getId()) != null);
	}

}
