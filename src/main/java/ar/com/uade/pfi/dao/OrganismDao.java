package ar.com.uade.pfi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.ExperimentalCodonPoblationEntity;
import ar.com.uade.pfi.dao.dbo.entities.OrganismEntity;

public class OrganismDao {
	
	public static List<OrganismEntity> getAll() throws SQLException {
		final MongoDatabase db = DBManager.getInstance().getConnection();
		MongoCollection<Document> collection = db.getCollection("organismos");
		
		List<OrganismEntity> arrOE = new ArrayList<OrganismEntity>();
		try (MongoCursor<Document> cur = collection.find().iterator()) {
			while (cur.hasNext()) {
				Document doc = cur.next();
	                
				List<ExperimentalCodonPoblationEntity> arrEcpe = new ArrayList<ExperimentalCodonPoblationEntity>();

				@SuppressWarnings("unchecked")
				List<Document> datosExperimentales = (List<Document>) doc.get("datos_experimentales");
				for (Document datos : datosExperimentales) {
					ExperimentalCodonPoblationEntity ecpe = new ExperimentalCodonPoblationEntity();
					ecpe.setCodon(datos.getString("codon"));
					ecpe.setPoblation(datos.getDouble("poblation"));
					ecpe.setPoblationLn(datos.getDouble("poblationLn"));
					arrEcpe.add(ecpe);
				}

				OrganismEntity oe = new OrganismEntity(
						doc.getObjectId("_id"), 
						doc.getString("name"), 
						doc.getDouble("porcGC"), 
						doc.getDouble("bestPearson"), 
						doc.getDate("lastUpdate"),
						arrEcpe);
						
				arrOE.add(oe);
			}
		}
		
		return arrOE;
	}
	
	public static void updateOrganismPoblation(OrganismEntity organism) {
		final MongoDatabase db = DBManager.getInstance().getConnection();
		MongoCollection<Document> collection = db.getCollection("organismos");
		
		BasicDBObject searchQuery = new BasicDBObject("_id", organism.getId());
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", new BasicDBObject().append("datos_calculados", organism.getPoblation()));
		
		collection.updateOne(searchQuery, updateQuery);
	}
}
