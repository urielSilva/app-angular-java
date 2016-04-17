package br.com.exemplo.test;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class ProductTest {

	static MongoCollection<Document> collection;

	@BeforeClass
	public static void setUp() {
		MongoClient client = new MongoClient();
		collection = client.getDatabase("products").getCollection("products");
	}

	@Test
	public void aProductShouldbeCreated() {
		collection.insertOne(new Document("name", "teste").append("description", "teste"));
		assertEquals("teste", collection.find(Filters.eq("name", "teste")).first().get("description"));
	}

	@Test
	public void aProductShouldbeRemoved() {
		collection.insertOne(new Document("name", "teste").append("description", "teste"));
		collection.deleteMany(Filters.eq("name", "teste"));
		assertEquals(null, collection.find(Filters.eq("name", "teste")).first());
	}

}
