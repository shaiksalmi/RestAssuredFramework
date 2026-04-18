package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.File;
import org.testng.annotations.DataProvider;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUsersTest extends BaseTest {
	
	private String tokenId;
	@BeforeClass
	public void setUpToken() {
		
		tokenId = "aaec952cf835eade8e9d668cdf40bb8560cf7f19610957ff4af0fee4aa7e155f";
		
		ConfigManager.set(tokenId, tokenId);
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Priyanka", "female", "active"},
			{"Ranjit", "male", "inactive"},
			{"Elmar", "male", "active"}
		};
	}
	
	
	@Test
	
	public void createUserTest() {
		
		
		User user = new User(null,"dev",StringUtils.getRandomEmailId(),"female","active");
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertEquals(response.jsonPath().getString("name"), "dev");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
	}
	  @Test
	  
	  public void createAuserWithJsonString() {
		  
		  String emailId = StringUtils.getRandomEmailId();
		  
		  String userJson = "{\n"
		  		+ "\"name\": \"Priya\",\n"
		  		+ "\"email\": \""+emailId+"\",\n"
		  		+ "\"gender\": \"female\",\n"
		  		+ "\"status\": \"active\"\n"
		  		+ "}";
		  
		  Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		  
		  AssertJUnit.assertEquals(response.jsonPath().getString("name"),"Priya");
		  
		  Assert.assertNotNull(response.jsonPath().getString("id"));
	  }
	  
	  @Test
	  
	  public void createAUserTestWithJsonfile() {

			File userFile = new File("./src/test/resources/jsons/user.json");
			Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			AssertJUnit.assertEquals(response.jsonPath().getString("name"), "Sunny");
			Assert.assertNotNull(response.jsonPath().getString("id"));	
		}
	
	
	}
	


