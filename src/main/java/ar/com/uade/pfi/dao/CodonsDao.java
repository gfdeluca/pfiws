package ar.com.uade.pfi.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;

public class CodonsDao {
	
	public static List<CodonEntity> getAll() {
		final MongoDatabase db = DBManager.getInstance().getConnection();
		MongoCollection<Document> collection = db.getCollection("codones");
		
		List<CodonEntity> arrCE = new ArrayList<CodonEntity>();
		try (MongoCursor<Document> cur = collection.find().iterator()) {
			while (cur.hasNext()) {
				Document doc = cur.next();
	                
				CodonEntity ce = new CodonEntity();
				ce.setId(doc.getInteger("id"));
				ce.setName(doc.getString("nombre"));
				ce.setAminoacid(doc.getString("aminoacido"));
				ce.setAminoacidLetter(doc.getString("aminoacido_acorto"));  
						
				arrCE.add(ce);
			}
		}
		
		return arrCE;
	}
}
