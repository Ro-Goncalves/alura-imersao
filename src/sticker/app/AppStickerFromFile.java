package sticker.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import sticker.common.util.UtilJson;

public class AppStickerFromFile {	
	
	public static void main(String[] args) throws ParseException {	
		UtilJson utilJson = new UtilJson();
		JSONArray topRated = utilJson.getArquivoJson("themoviedb-topRated");
		
		for(Object filme: topRated) {
			JSONObject jsFilme = (JSONObject) filme;
			
			System.out.println("Título Original: " + jsFilme.get("original_title"));
			System.out.println("Título: " + jsFilme.get("title"));
			System.out.println("Imagem: https://image.tmdb.org/t/p/w500" + jsFilme.get("poster_path"));
			System.out.println("Avaliação: " + jsFilme.get("vote_average"));
			System.out.println();
		}
	}
}
