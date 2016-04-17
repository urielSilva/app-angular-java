package br.com.exemplo.model;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * 
 * @author Uriel
 * Product POJO which represents the product persistent model.
 */
public class Product {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	public Product() {
		
	}
	public Product(Document document) {
		this.id = ((ObjectId) document.get("_id")).toString();
		this.name= document.getString("name");
		this.description = document.getString("description");
		this.price = document.getDouble("price");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
