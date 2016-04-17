package br.com.exemplo.api;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import spark.Response;
import spark.ResponseTransformer;


// implements a simple transformer used in the http responses
// had to use a little workaround in order to use mongodb with spark
public class JsonTransformer implements ResponseTransformer {
	
	public String render(Object model) {
		if (model instanceof Response) {
			Gson gson = new GsonBuilder().registerTypeAdapter(Response.class, new MyTypeAdapter<Response>()).create();
			return gson.toJson(model);
		} else {
			Gson gsonNormal = new Gson();
			return gsonNormal.toJson(model);
		}

	}

	class MyTypeAdapter<T> extends TypeAdapter<T> {
		public T read(JsonReader reader) throws IOException {
			return null;
		}

		public void write(JsonWriter writer, T obj) throws IOException {
			if (obj == null) {
				writer.nullValue();
				return;
			}
			writer.value(obj.toString());
		}
	}
}
