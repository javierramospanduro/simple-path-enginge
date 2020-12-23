package com.javi.engine.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.javi.engine.dto.Axis;
import org.javi.engine.types.AxisType;

import com.javi.engine.utils.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class DAxis {
	private static final Log LOG = LogFactory.getLog(DAxis.class);

	DConfiguration configuration;
	public void insertOne(Axis axis) {
		LOG.info("Inserting one axis: " + axis);
		configuration.getMongoDatabase().getCollection(Constants.AXIS_COLLECTION).insertOne(mapToDocument(axis));
		LOG.info("Inserted");
	}
	public Axis getAxis(String id) {
		LOG.info("Getting de axis with id: " + id);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		FindIterable<Document> iterable = configuration.getMongoDatabase().getCollection(Constants.AXIS_COLLECTION).find(whereQuery);
		return mapFromDocument(iterable.first());
	}
	public List<Axis> getAllAxis() {
		List<Axis> list = new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		FindIterable<Document> iterable = configuration.getMongoDatabase().getCollection(Constants.AXIS_COLLECTION).find(whereQuery);
		return mapFromDocument(iterable.first());
	}
	private Document mapToDocument(Axis axis) {
		Document md = new Document();
		md.append("id", axis.getId());
		md.append("description", axis.getDescription());
		md.append("type", axis.getType().toString());
		return md;
	}
	private Axis mapFromDocument(Document document) {
		Axis axis = new Axis();
		axis.setId(document.getString("id"));
		axis.setDescription(document.getString("description"));
		axis.setType(AxisType.valueOf(document.getString("type")));
		return axis;
	}
	

}
