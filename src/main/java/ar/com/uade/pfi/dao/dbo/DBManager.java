package ar.com.uade.pfi.dao.dbo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	private static DBManager _instance = null;
	private MongoClient _ds = null;
	
	private DBManager() {
//		_ds = new MongoClient(new ServerAddress("localhost", 27017));
		_ds = new MongoClient(new ServerAddress("5.135.153.157", 27017));
	}
	
	public static DBManager getInstance() {
		if (_instance == null) {
			_instance = new DBManager();
		}
		
		return _instance;
	}
	
	public MongoDatabase getConnection() {
		return _ds.getDatabase("PFI");
	}
}
