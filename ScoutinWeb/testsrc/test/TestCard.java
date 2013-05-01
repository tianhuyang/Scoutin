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
import com.scoutin.entities.Cardbody;
import com.scoutin.entities.Comment;
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
		try {
			Card card = new Card();
			Cardbody cardbody = new Cardbody();
			card.setDescription("My new card");
			card = CardService.createCard(1,new Long[]{1l,3L},card,cardbody);
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
		int accountId = 1;
		Long[] albumIds = new Long[]{3L,1L};
		Long cardbodyId = 1L;
		Card card = new Card();
		try {
			card = CardService.repostCard(accountId,albumIds,card,cardbodyId);
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
	public void testEditCard(){
		int accountId = 1;
		Map<String, Object> cardProperties = new TreeMap<String, Object>();
		Map<String, Object> cardbodyProperties = new TreeMap<String, Object>();
		Map<String, Object> properties[] = new TreeMap[1];
//		Card card = new Card();
//		card.setCardId(3L);
//		card.setDescription("card description");
//		card.setVersion(1L);
//		
//		Cardbody cardbody = new Cardbody();
//		cardbody.setCardbodyId(1L);
//		cardbody.setTitle("this is a test");		
//		cardbody.setVersion(6L);
		cardProperties.put("cardId", 3L);
		cardProperties.put("description", "card sucess2");
		cardProperties.put("version", 4L);
		
		cardbodyProperties.put("description", "card description");
		cardbodyProperties.put("cardbodyId", 1L);
		cardbodyProperties.put("title", "title2");
		cardbodyProperties.put("version", 4L);
		try {
			CardService.editCard(accountId, cardProperties, cardbodyProperties,properties);
			String info = ReflectionToStringBuilder.toString(properties[0], ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			System.out.println(properties[0]);
			Assert.assertTrue(false);
		}
		
	}
	
	@Ignore
	@Test
	public void testLikeCard(){
		int accountId = 1;
		Long cardId = 1L;

		try {
			boolean liked = CardService.likeCard(accountId, cardId);
			System.out.println("liked = "+liked);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			 e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Ignore
	@Test
	public void testCommentCard(){
		Comment comment = new Comment();
		comment.setContent("This is a comment");
		Long cardId = 3L;
		Integer accountId = 1;

		try {
			comment = CardService.commentCard(accountId,cardId,comment);
			String info = ReflectionToStringBuilder.toString(comment, ToStringStyle.MULTI_LINE_STYLE);
			System.out.println(info);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
