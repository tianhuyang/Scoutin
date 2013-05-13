package test;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.scoutin.entities.Card;
import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.RecommendService;

public class TestRecommendation {
	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	@Ignore
	@Test
	public void testRecommendCard() {
		Integer accountId = 1;
		Long cardId = 1L;
		Integer[] accountIds = {};
		Long[] clusterIds = { 2L, 8L };
		try {
			RecommendService.recommendCard(accountId, cardId, accountIds,
					clusterIds);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Ignore
	@Test
	public void testGetNewsCount() {
		Integer accountId = 1;
		try {
			long count = RecommendService.getNewsCount(accountId);
			System.out.println("News Count = " + count);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testGetNews() {
		Integer accountId = 1;
		long date = System.currentTimeMillis();
		boolean forward = false;
		int limit = 1;
		try {
			List<Card> cards = RecommendService.getNews(accountId, date,
					forward, limit);
			Iterator<Card> iter = cards.iterator();
			while (iter.hasNext()) {
				String info = ReflectionToStringBuilder.toString(iter.next(),
						ToStringStyle.MULTI_LINE_STYLE);
				System.out.println(info);
			}
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
