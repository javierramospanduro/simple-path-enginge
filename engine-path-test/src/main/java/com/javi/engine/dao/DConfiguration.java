package com.javi.engine.dao;

import org.apache.commons.lang3.StringUtils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DConfiguration {
	String serverUrl;
	MongoClient mongoClient;
	MongoDatabase mongoDatabase;
	
	public DConfiguration(String url) throws Exception {
		if (StringUtils.isBlank(url)) {
			throw new Exception("Url is blank");
		}
		mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase(url);
	}

}
