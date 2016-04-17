package br.com.exemplo;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import br.com.exemplo.api.ProductEndpoint;
import br.com.exemplo.service.ProductService;

public class Main {
 
	public static void main(String[] args) throws Exception{
		staticFileLocation("/public");
		port(getHerokuAssignedPort());
        new ProductEndpoint(new ProductService(initDb()));
	}

    private static MongoDatabase initDb() throws Exception {
        MongoClientURI uri;
        MongoClient client;
        if(System.getenv("MONGODB_URI") != null) {
        	uri = new MongoClientURI(System.getenv("MONGODB_URI"));
            client = new MongoClient(uri);
            return  client.getDatabase(uri.getDatabase());
        } else {
        	client = new MongoClient();
        	return  client.getDatabase("products");
        }
    	
        
    }
    
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku port is not set
    }
}

