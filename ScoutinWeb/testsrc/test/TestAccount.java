package test;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.struts2.ServletActionContext;
import org.junit.*;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AccountConstants;
import com.scoutin.utilities.EJBUtils;

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
		properties.put("email", "tianhu@qq.com");
		properties.put("password", "tiger");
		properties.put("firstname", "Tianhu");
		properties.put("lastname", "Yang");
		properties.put("sex", 1);
		try {
			Account account = AccountService.signup((Map) properties);
			String info = ReflectionToStringBuilder.toString(account, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			// e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	
	@Test
	public void testAuthenticate() {
		String args[] = new String[2];
		args[0] = "tianhu@qq.com";
		args[1] = "tiger";
		try {
			Account account = AccountService.authenticate(args,
					AccountConstants.AuthenticateTypeEmail);
			String info = ReflectionToStringBuilder.toString(account, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Ignore
	@Test
	public void testConcurrentAuthenticate() {
		final String args[] = new String[2];
		args[0] = "haocai@qq.com";
		args[1] = "password";
		int size = 10 ;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; ++i) {
			threads[i] = new Thread() {
				public void run() {
					try {
						Account account = AccountService.authenticate(args,
								AccountConstants.AuthenticateTypeEmail);
						System.out.println(account);
						//System.out.println(account.getEmail());
						//System.out.println(account.getProfile().getAccountId());
					} catch (ScoutinException e) {
						// e.printStackTrace();
						Assert.assertTrue(false);
					}
				}
			};
			threads[i].start();
		}
		for (int i = 0; i < size; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Assert.assertTrue(true);
	}
	@Ignore
	@Test
	public void testConcurrent()
	{
		int size = 10000 ;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; ++i) {
			threads[i] = new Thread() {
				public void run() {
					try {
						TestAccount.this.testCreateAlbum();
					} catch (RuntimeException e) {
						// e.printStackTrace();
						Assert.assertTrue(false);
					}
				}
			};
			threads[i].start();
		}
		for (int i = 0; i < size; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Assert.assertTrue(true);
	}
	
	@Ignore
	@Test
	public void testCreateAlbum() {
		Map<String, Object> properties = new TreeMap<String, Object>();
		properties.put("accountId", 1);
		properties.put("name", "default");
		try {
			Album album = AccountService.createAlbum(properties);
			String info = ReflectionToStringBuilder.toString(album, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
}
