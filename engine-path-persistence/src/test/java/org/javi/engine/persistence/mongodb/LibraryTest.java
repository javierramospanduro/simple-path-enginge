package org.javi.engine.persistence.mongodb;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javi.engine.core.dto.Axis;
import org.javi.engine.core.utils.Constants;
import org.javi.engine.persistence.mongodb.dao.DAxis;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.model.IndexOptions;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class LibraryTest {
	
	private static final Log LOG = LogFactory.getLog(LibraryTest.class);
	PodamFactory factory = new PodamFactoryImpl();
	DAxis dAxis;
	
	Configuration configuration;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("Setting up before class");
		LOG.info("Truncate database test");
		Configuration conf = new Configuration();
		conf.getMongoDatabase().getCollection(Constants.AXIS_COLLECTION).drop();
		conf.getMongoDatabase().createCollection(Constants.AXIS_COLLECTION);
		conf.getMongoDatabase().getCollection(Constants.AXIS_COLLECTION)
			.createIndex(new BasicDBObject(Constants.AXIS_KEY,1),new IndexOptions().unique(true));
		LOG.info("Database truncated");
	}
	@Before
	public void setUpBeforeTest() {
		LOG.info("Setting up before test");		
	}
	
	@Test
	public void testOpenConnection() throws Exception {
		Configuration configuration = new Configuration();
		assertNotNull(configuration);
	}
	@Test
	public void testOperationsOverAxis() throws Exception {
		LOG.info("Testing axis dao");
		Configuration configuration = new Configuration();
		dAxis = new DAxis(configuration.getMongoDatabase());
		List<Axis> axises = factory.manufacturePojo(ArrayList.class, Axis.class);		
		for (Axis elem:axises) {
			LOG.info("Testing insert one axis: " + elem);
			try {
				dAxis.insertOne(elem);
			} catch (MongoWriteException e) {
				e.printStackTrace();
			}
		}
		LOG.info("Done");
		LOG.info("Testing insert many");
		int i = dAxis.insertMany(factory.manufacturePojo(ArrayList.class, Axis.class));
		LOG.info("Inserted " + i + " records");
		LOG.info("Testing get all");
		List<Axis> allData = dAxis.getAll();
		LOG.info("All data: " + allData);
		LOG.info("Testing get axis by id");
		Axis response = dAxis.getAxisById(axises.get(0).getAxisId());
		LOG.info("Response: " + response);
		LOG.info("Testing delete");
		dAxis.deleteAxisById(axises.get(0).getAxisId());
		LOG.info("Delete done, exists in collection? -> " + dAxis.getAxisById(axises.get(0).getAxisId()));
		LOG.info("Testing delete all");
		dAxis.deleteAll();
		LOG.info("Are records? -> " + dAxis.getAll());
		
	}

}
