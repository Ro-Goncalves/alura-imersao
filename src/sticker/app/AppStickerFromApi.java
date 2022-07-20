package sticker.app;

import java.io.InputStream;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import gerador.figuras.GeradorDeFigurinhas;
import sticker.common.util.Extratores;
import sticker.common.util.UtilProperties;
import sticker.http.ClienteHttp;
import sticker.model.Conteudo;

public class AppStickerFromApi {
	public static void main(String[] args) throws ParseException {		
		UtilProperties properties = new UtilProperties();
		properties.setApiKey("themoviedb_key");
		
		String url = "https://imdb-api.com/en/API/Top250Movies/" + properties.getApiKey();		
		ClienteHttp cliente = new ClienteHttp();
		
		String json = cliente.BuscaDados(url);	
		
		Extratores extratores = new Extratores();
		List<Conteudo> conteudos = extratores.extraiConteudosNasa(json);
		
		GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
		
		for( int i = 0; i < 3; i++) {
			Conteudo conteudo = conteudos.get(i);
			
			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
			
			geradora.criar(inputStream, nomeArquivo, "");
			
			System.out.println(conteudo.getTitulo());
		}		
	}
}
