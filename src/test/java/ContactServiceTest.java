import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xware.barter.action.ContactService;
import com.xware.barter.action.ContactServiceImpl;
import com.xware.barter.domain.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
/* @Transactional 
 * */
 
public class ContactServiceTest {
private Contact c ;
@Autowired
private ContactService contactService;



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	//	contactService = new ContactServiceImpl();
		
		c =new Contact();
		
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEditContact() {
		fail("Not yet implemented");
	}

//	@Test
	public void testRemoveContact() {
		contactService.add(c);
		Integer i=c.getId();
		assertTrue(i != null);
		assertTrue(!(contactService.getContact(i).equals(null)));
		contactService.removeContact(c.getId());
		assertTrue(contactService.getContact(i).equals(null));
		assertTrue( c.getId().equals(null));
		
		
		
	}

	@Test
	public void testAdd() {
		contactService.add(c);
		assertTrue(c.getId()!=null);
	}

	@Test
	public void testGetContact() {
		c = contactService.add(c);
		Contact c2= contactService.getContact(c.getId());
		assertTrue(c2 != null);
		assertTrue(c2.getRegion().equals("region"));
	}

	@Test
	public void testEditItem() {
		c = contactService.add(c);
		c.setCountry("UK");
		contactService.editContact(c);
		assertTrue(c.getCountry().equals("UK"));
		Contact c2= contactService.getContact(c.getId());
		assertTrue(c2.getCountry().equals("UK"));
	}

}
