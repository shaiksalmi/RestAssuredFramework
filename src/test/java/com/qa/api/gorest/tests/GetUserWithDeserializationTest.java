package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonPathUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserWithDeserializationTest extends BaseTest{
	
	private String tokenId;
	@BeforeClass
	public void setUpToken() {
		
		tokenId = "aaec952cf835eade8e9d668cdf40bb8560cf7f19610957ff4af0fee4aa7e155f";
		
		ConfigManager.set("bearertoken",tokenId);
		
		
	}
	
	@Test
	
	public void createUserTest() {

		User user = new User(null,"Jhon",StringUtils.getRandomEmailId(),"female","active");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Jhon");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
		
		
		String userID = response.jsonPath().getString("id");
		
		
		//2.GET: fetch the user using the same userId:
		
				Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
				
				Assert.assertTrue(responseGet.statusLine().contains("OK"));
				
				Assert.assertEquals(responseGet.jsonPath().getString("id"), userID);
				
				User userResponse =JsonPathUtil.deserialize(response, User.class);
				
				Assert.assertEquals(userResponse.getName(),user.getName());
				
	}
	

}
