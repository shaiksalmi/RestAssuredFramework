package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserAPISchemaTest extends BaseTest{
	
	
	
	@Test
	public void getUsersAPISchemaTest() {	
		ConfigManager.set("bearertoken", "e3dddb0193bf0fd35d3c577d371b0c11200ea36e1c635946e8111d9d8b1e39fa");
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/getuserschema.json"));
	
	}
	
	
	@Test
	public void createUserAPISchemaTest() {	
		ConfigManager.set("bearertoken", "e3dddb0193bf0fd35d3c577d371b0c11200ea36e1c635946e8111d9d8b1e39fa");
		
		User user = User.builder()
		.name("api")
		.status("active")
		.email(StringUtils.getRandomEmailId())
		.gender("female")
		.build();
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/createuserschema.json"));
	
	}
	
	

}