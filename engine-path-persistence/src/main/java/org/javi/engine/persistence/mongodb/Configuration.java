package org.javi.engine.persistence.mongodb;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.javi.engine.core.dto.Signal;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Configuration implements Serializable {
	private static final long serialVersionUID = -9205267262022172168L;
	private static final Log LOG = LogFactory.getLog(Signal.class);
	
	com.mongodb.client.MongoClient mongoClient;
	MongoDatabase mongoDatabase;
	MongoClientSettings mongoClientSettings;
	CodecRegistry codecRegistry;
	
	Properties properties;
	String databaseUrl;
	int databasePort;
	String databaseUser;
	String databasePassword;
	String databaseName;
	String axisCollectionName;
	String parametryCollectionName;
	
	public Configuration() throws Exception {
		super();
		properties = new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		LOG.info("Properties loaded!");
		LOG.info("Properties content: " + properties);
		databaseName=properties.getProperty("db.engine.name");
		databaseUrl=properties.getProperty("db.url");
		databasePort=Integer.valueOf(properties.getProperty("db.port"));
		databaseUser=properties.getProperty("db.engine.user");
		databasePassword=properties.getProperty("db.engine.password");
		axisCollectionName=properties.getProperty("db.engine.collections.axis");
		parametryCollectionName=properties.getProperty("db.engine.collections.parametry");
		LOG.info("My data:" + this);
		LOG.info("Open database");
		codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		mongoClientSettings = MongoClientSettings.builder().codecRegistry(codecRegistry).build();
		
		mongoClient = MongoClients.create(mongoClientSettings);
		mongoDatabase = mongoClient.getDatabase(databaseName);
		LOG.info("Check connection");
		ListCollectionsIterable<Document> a = mongoDatabase.listCollections();
		if (a==null) {throw new Exception("Error in connection!");}
	}

}
