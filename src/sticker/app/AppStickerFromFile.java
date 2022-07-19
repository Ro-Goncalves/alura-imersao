package sticker.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import gerador.figuras.GeradorDeFigurinhas;
import sticker.common.util.UtilJson;

public class AppStickerFromFile {	
	
	public static void main(String[] args) throws ParseException, MalformedURLException, IOException {	
		UtilJson utilJson = new UtilJson();
		JSONArray topRated = utilJson.getArquivoJson("themoviedb-topRated");
		GeradorDeFigurinhas figuras = new GeradorDeFigurinhas();
		
		for(Object filme: topRated) {
			JSONObject jsFilme = (JSONObject) filme;
			String titulo = (String) jsFilme.get("title");
			String url = "https://image.tmdb.org/t/p/w500" + jsFilme.get("poster_path");
			String voto = jsFilme.get("vote_average").toString();
			
			InputStream inputUrl = new URL(url).openStream();
			figuras.criar(inputUrl, titulo.replace(":", " -"), voto);
			
			System.out.println("Título Original: " + jsFilme.get("original_title"));
			System.out.println("Título: " + titulo.replace(":", " -"));
			System.out.println("Imagem: " + url);
			System.out.println("Avaliação: " + voto);
			System.out.println();
		}
	}
}
