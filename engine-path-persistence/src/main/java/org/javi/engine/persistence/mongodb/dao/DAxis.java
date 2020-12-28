package org.javi.engine.persistence.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.javi.engine.core.dto.Axis;
import org.javi.engine.core.utils.Constants;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DAxis {
	
	private static final Log LOG = LogFactory.getLog(DAxis.class);
	
	MongoCollection<Axis> collection;
	
	public boolean insertOne(Axis axis) {
		LOG.info("Inserting axis: " + axis);
		collection.insertOne(axis);
		LOG.info("Done");
		return true;
	}
	public int insertMany(List<Axis> axises) {
		int i = 0;
		LOG.info("Insert many, data: " + axises);
		for (Axis elem:axises) {
			insertOne(elem);
			i++;
		}
		return i;
	}	
	public List<Axis> getAll() {
		LOG.info("Getting al indexes");
		return collection.find().into(new ArrayList<Axis>());
	}
	public Axis getAxisById(String id) {
		LOG.info("Get the index with key: " + id);
		return collection.find(new BasicDBObject(Constants.AXIS_KEY,new BasicDBObject("$eq",id))).first();
	}
	public void deleteAxisById(String id) {
		LOG.info("Delete axis with id: " + id);
		LOG.info("Actual data to drop: " + getAxisById(id));
		collection.deleteOne(new BasicDBObject(Constants.AXIS_KEY,id));		
	}
	public void deleteAll() {
		LOG.info("Delete all");
		collection.deleteMany(new BasicDBObject(Constants.AXIS_KEY,new BasicDBObject("$exists",true)));
	}
	public DAxis(MongoDatabase db) throws Exception {
		super();
		collection = db.getCollection(Constants.AXIS_COLLECTION,Axis.class);
		if (collection == null) {
			LOG.info("Can't get the " + Constants.AXIS_COLLECTION + " collection");
			throw new Exception("cantGetCollection");
		}
	} 
	
}
