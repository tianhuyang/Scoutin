package test;
import org.junit.*;

import com.scoutin.entities.Account;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.AuthenticateType;
public class TestAccount {

	@Before public  void init(){
		
	}
	
	@After public void destroy(){
		
	}
	
	
	@Test
	public  void testSignup(){
		String[] args={"tianhuyang@hotmail.com","password","Tianhu","Yang"};
		try {
			Account account = AccountService.signup(args, AuthenticateType.AuthenticateTypeEmail);
			System.out.println(account.getAccountId());
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	@Test
	public void testAuthenticate(){
		String args[] = new String[2];
		args[0] = "tianhuyang@hotmail.com";
		args[1] = "password";
		try {
			System.out.println(AccountService.authenticate(args, AuthenticateType.AuthenticateTypeEmail));
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
