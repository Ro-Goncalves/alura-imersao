package sticker.app;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sticker.common.util.UtilProperties;

public class App {
	public static void main(String[] args) throws ParseException {		
		UtilProperties properties = new UtilProperties();
		properties.setApiKey("themoviedb_key");		
		
		//String url = "https://imdb-api.com/en/API/Top250Movies/" + properties.getApiKey();		
		String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";		
		System.out.println(url);
		URI uriClient = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		
		//Fazendo a chamada
		HttpRequest request = HttpRequest.newBuilder(uriClient).GET().build();
		
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String responseBody = response.body();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(responseBody);
			Object filmes = json.get("results");
			System.out.println(filmes);
			//System.out.println(json.get("poster_path"));
			//System.out.println(json.get("vote_average"));
			
//			for (Map<String, String> entry : filmes) {
//				String title = entry.get("title");
//				String image = entry.get("image");
//				String rating = entry.get("imDbRating");
//				
//				System.out.println("Titulo: " + title + " Rating: " + rating);
//				System.out.println(" ");
				
			//}
		} catch (IOException e) {				
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		
		
	}
}
