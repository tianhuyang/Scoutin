package test;

import java.util.Map;
import java.util.TreeMap;

import org.junit.*;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AccountConstants;

public class TestAccount {

	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	@Ignore
	@Test
	public void testSignupWithEmail() {
		Map<String, Object> properties = new TreeMap<String, Object>();
		properties.put("email", "tianhuyang@hotmail.com");
		properties.put("password", "password");
		properties.put("firstname", "Tianhu");
		properties.put("lastname", "Yang");
		properties.put("sex", 1);
		try {
			Account account = AccountService.signup(properties);
			System.out.println(account.getAccountId());
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			// e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	@Ignore
	@Test
	public void testAuthenticate() {
		String args[] = new String[2];
		args[0] = "tianhuyang@hotmail.com";
		args[1] = "password";
		try {
			System.out.println(AccountService.authenticate(args,
					AccountConstants.AuthenticateTypeEmail));
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			// e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testCreateAlbum() {
		Map<String, Object> properties = new TreeMap<String, Object>();
		properties.put("accountId", 1);
		properties.put("name", "default");
		try {
			Album album = AccountService.createAlbum(properties);
			System.out.println(album.getAccount());
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			 e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
}
