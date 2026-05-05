package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties properties = new Properties();
	
	static {
		
		//String env = System.getProperty("env","qa");
		//String path = "config_" + env + ".properties";
		
		//System.out.println("=======path====="+ path);
		
		//mvn clean install -Denv=qa/stage/dev/uat/prod
		//mvn clean install -- if env is not given, then run test cases on QA env by default.
		//env -- environmment variable(system)
		
		String envName = System.getProperty("env","qa");
		
		System.out.println("running tests on env: "+ envName);
		String fileName = "config_" + envName +".properties"; //config_qa.properties
		
		
	InputStream input =	ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
	
	if(input!= null) {
		try {
		properties.load(input);
		System.out.println("properties======>" + properties);
	}catch(IOException e) {
		
		e.printStackTrace();
	}
		
	}
	}

public static String get(String key) {
	
	return properties.getProperty(key).trim();
}

public static void set(String key, String value) {
	
	properties.setProperty(key, value);
}

}