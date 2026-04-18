package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest{
	
	private String tokenId;
	
	@BeforeMethod
	
	public void getToken() {
		
	ContactsCredentials creds=	ContactsCredentials.builder()
		.email("salmashaik.csit@gmail.com")
		.password("Salma@1234")
		.build();
		
	Response response =	restClient.post(BASE_URL_CONTACTS, CONTCTS_LOGIN_ENDPOINT, creds, null, null,AuthType.NO_AUTH, ContentType.JSON);
		
	tokenId = response.jsonPath().getString("token");
	
	System.out.println("contacts login JWT token ----->"+ tokenId);
	ConfigManager.set("bearertoken", tokenId);
	
	}

	@Test
	
	public void getAllContacts() {
		
		Response responseGet =	restClient.get(BASE_URL_CONTACTS, CONTCTS_ENDPOINT, null, null,AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(responseGet.statusCode(), 200);
	}
}
