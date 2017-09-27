package ar.com.uade.pfi.dao;

import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ar.com.uade.pfi.dao.dbo.DBManager;
import ar.com.uade.pfi.dao.dbo.entities.AppConfigurationEntity;

public class AppConfigurationDao {
	
	public static AppConfigurationEntity get (String clave) throws SQLException {
		final MongoDatabase db = DBManager.getInstance().getConnection();
		MongoCollection<Document> collection = db.getCollection("appConfiguraciones");
		Document result = collection.find(Filters.eq("param", clave)).first();
		AppConfigurationEntity ace = new AppConfigurationEntity(result.getString("param"), result.getString("valor"));	
		return ace;
	}
}
