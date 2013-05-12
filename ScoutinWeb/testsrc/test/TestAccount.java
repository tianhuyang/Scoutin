package test;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.*;

import com.scoutin.entities.Account;
import com.scoutin.entities.Album;
import com.scoutin.exception.ScoutinException;
import com.scoutin.application.interfaces.AccountConstants;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AlbumService;

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
		Account account = new Account();
		account.setEmail("haocai2@usc.edu");
		account.setPassword("password");
		account.setFullName("Tianhu Yang");
		account.setSex((short)AccountConstants.SexType_Male);
		try {
			account = AccountService.signup( account);
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
	public void testAuthenticate() {
		String args[] = new String[2];
		args[0] = "haocai@usc.edu";
		args[1] = "password";
		try {
			Account account = AccountService.authenticate(args,
					AccountConstants.AuthenticateTypeEmail);
			//System.out.println(account.getAlbums().getClass().getName());
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
		args[0] = "haocai@usc.edu";
		args[1] = "password";
		int size = 1000 ;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; ++i) {
			threads[i] = new Thread() {
				public void run() {
					try {
						Account account = AccountService.authenticate(args,
								AccountConstants.AuthenticateTypeEmail);
						String info = ReflectionToStringBuilder.toString(account, ToStringStyle.MULTI_LINE_STYLE);
						System.out.println(info);
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
		int size = 10;
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
		int accountId = 1;
		Album album = new Album();
		album.setName("default");
		try {
			album = AlbumService.createAlbum(accountId,album);
			String info = ReflectionToStringBuilder.toString(album, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
	
	@Ignore
	@Test
	public void testBlockAlbum() {
		int followingAccountId = 1;
		long followedAlbumId = 2L;
		try {
			boolean followed = AlbumService.blockAlbum(followingAccountId, followedAlbumId);
			System.out.println("BlockAlbum followed: "+followed);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
	
	
	@Test
	public void testFollowAccount() {
		int followingAccountId = 1, followedAccountId = 2;
		boolean followed = false;
		try {
			AccountService.followAccount(followingAccountId, followedAccountId, followed);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}
}
