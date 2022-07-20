package sticker.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import sticker.excecoes.TrataExecoes;

public class ClienteHttp {
	
	public String BuscaDados(String url) {				
		
		try {		
			URI uriClient = URI.create(url);		
			HttpClient client = HttpClient.newHttpClient();			
			HttpRequest request = HttpRequest.newBuilder(uriClient).GET().build();	
			
			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			
			return response.body();
		} catch (IOException | InterruptedException e) {	
			throw new TrataExecoes("Algo De Errado Não Está Certo" , e);									
		} 		
	}
}
