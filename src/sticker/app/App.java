package sticker.app;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import sticker.common.util.JsonParser;
import sticker.common.util.UtilProperties;

public class App {
	public static void main(String[] args) {		
		UtilProperties properties = new UtilProperties();
		properties.setApiKey("imdb_key");		
		
		String url = "https://imdb-api.com/en/API/Top250Movies/" + properties.getApiKey();
		
		System.out.println(url);
		URI uriClient = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		
		//Fazendo a chamada
		HttpRequest request = HttpRequest.newBuilder(uriClient).GET().build();
		
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String responseBody = response.body();
			JsonParser parser = new JsonParser();
			List<Map<String, String>> filmes = parser.parse(responseBody);
			
			for (Map<String, String> entry : filmes) {
				String title = entry.get("title");
				String image = entry.get("image");
				String rating = entry.get("imDbRating");
				
				System.out.println("Titulo: " + title + " Rating: " + rating);
				System.out.println(" ");
				
			}
		} catch (IOException e) {				
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		
		
	}
}
