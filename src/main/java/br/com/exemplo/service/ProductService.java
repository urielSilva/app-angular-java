package br.com.exemplo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonBoolean;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.exemplo.model.Product;

/**
 * 
 * @author Uriel
 * service created for delegating actions from the chosen api url.
 */
public class ProductService {

	private MongoDatabase db;
	private MongoCollection<Document> collection;

	public ProductService(MongoDatabase db) {
		this.db = db;
		this.collection = db.getCollection("products");
	}

	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		FindIterable<Document> cursor = collection.find();
		cursor.forEach((Block<Document>) p -> products.add(new Product(p)));
		return products;
	}

	public void create(String json) {
		Product product = parseJson(json);
		collection.insertOne(new Document("name", product.getName()).append("description", product.getDescription())
				.append("price", product.getPrice()));
	}

	public Product find(String id) {
		return new Product(collection.find(Filters.eq("_id", new ObjectId(id))).first());
	}

	public Product update(String productId, String json) {
		Product product = parseJson(json);
		collection.replaceOne(Filters.eq("_id", new ObjectId(productId)),
				new Document("name", product.getName())
						.append("description", product.getDescription()).append("price", product.getPrice()));
		return find(productId);
	}
	
	public void delete(String id) {
		collection.deleteOne(new Document("_id", new ObjectId(id)));
	}

	private Product parseJson(String json) {
		Product product = null;
		product = new Gson().fromJson(json, Product.class);
		return product;
	}
}
