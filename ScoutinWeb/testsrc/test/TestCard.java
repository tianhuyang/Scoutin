package test;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.scoutin.entities.Album;
import com.scoutin.entities.Card;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.AccountService;
import com.scoutin.logic.CardService;

public class TestCard {

	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}
	
	@Test
	public void testCreateCard(){
		Map<String, Object> properties = new TreeMap<String, Object>();
		properties.put("accountId", 1);
		properties.put("albumIds", new long[]{1L});
		properties.put("url", "www.baidu.com");
		try {
			Card card = CardService.createCard(properties);
			String info = ReflectionToStringBuilder.toString(card, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			 e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Ignore
	@Test
	public void testRepostCard(){
		Map<String, Object> properties = new TreeMap<String, Object>();
		properties.put("albumIds", new long[]{1L,2L});
		properties.put("cardbodyId", 1L);
		properties.put("accountId", 1);
		try {
			Card card = CardService.repostCard(properties);
			String info = ReflectionToStringBuilder.toString(card, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			 e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
