package test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;

import com.scoutin.entities.Account;
public class TestMaps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String[]> properties = new TreeMap<String,String[]>();
		String[] names = {"hao","tianhu"};
		properties.put("email", names);
		Account account = new Account();
		try {
			BeanUtils.populate(account, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(account.getEmail());
	}

}
