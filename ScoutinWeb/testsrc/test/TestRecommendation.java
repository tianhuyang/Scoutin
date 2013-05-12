package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.scoutin.exception.ScoutinException;
import com.scoutin.logic.RecommendService;

public class TestRecommendation {
	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}	
	
	@Test
	public void testRecommendCard(){
		Integer accountId = 1;
		Long cardId = 1L;
		Integer[] accountIds = {};
		Long[] clusterIds = { 2L,8L};
		try {
			RecommendService.recommendCard(accountId, cardId, accountIds, clusterIds);
			Assert.assertTrue(true);
		} catch (ScoutinException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
