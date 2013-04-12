package test;

import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
		properties.put("albumId", 1L);
		properties.put("url", "www.baidu.com");
		try {
			Card card = CardService.createCard(properties);
			System.out.println(card.getCardId());
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			 e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
