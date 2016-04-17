package br.com.exemplo.api;

import static spark.Spark.*;

import br.com.exemplo.service.ProductService;
/**
 * 
 * @author Uriel
 * REST endpoint for products resource. Created using Java Spark
 */
public class ProductEndpoint {
	private static final String API_PREFIX = "/api/";

	private ProductService service;

	public ProductEndpoint(ProductService service) {
		this.service = service;
		setupEndpoints();
	}

	private void setupEndpoints() {
		post(API_PREFIX + "/products", "application/json", (request, response) -> {
			service.create(request.body());
			response.status(201);
			return response;
		}, new JsonTransformer());

		get(API_PREFIX + "/products/:id", "application/json", (request, response)
		-> service.find(request.params(":id")), new JsonTransformer());

		get(API_PREFIX + "/products", "application/json", (request, response)
		-> service.findAll(), new JsonTransformer());

		put(API_PREFIX + "/products/:id", "application/json", (request, response) -> { 
			service.update(request.params(":id"), request.body());
			response.status(201);
			return response;
		}
		, new JsonTransformer());

		delete(API_PREFIX + "/products/:id", "application/json", (request, response) -> {
			service.delete(request.params(":id"));
			response.status(200);
			return response;
		}, new JsonTransformer());
	}

}
