package sticker.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sticker.model.Conteudo;

public class Extratores {
	
	public List<Conteudo> extraiConteudosNasa(String json){
		
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		for(Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImage = atributos.get("url");
			
			Conteudo conteudo = new Conteudo(titulo, urlImage, "0.0");
			
			conteudos.add(conteudo);
		}
		
		return conteudos;
		
	}
	
	public List<Conteudo> extraiConteudosNasa(Object json){
		
		JSONArray jsonArray = (JSONArray) json;	
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		jsonArray.forEach((elemento) -> {
			JSONObject conteudo = (JSONObject) elemento;
			conteudos.add(
					new Conteudo(conteudo.get("title").toString(), 
							     conteudo.get("url").toString(), 
							     "9.0")
					);
			
		});
		
		return conteudos;
		
	}
	
	public List<Conteudo> extraiConteudosTheMovieDb(Object json){
		
		JSONArray jsonArray = (JSONArray) ((JSONObject) json).get("results");
		
		List<Conteudo> conteudos = new ArrayList<>();

		jsonArray.forEach((elemento) -> {
			JSONObject conteudo = (JSONObject) elemento;			
			conteudos.add(
					new Conteudo(conteudo.get("title").toString(), 
							     "https://image.tmdb.org/t/p/w500" + conteudo.get("poster_path"), 
							     conteudo.get("vote_average").toString())								 
				);
			
		});
		
		return conteudos;
		
	}
	
	public List<Conteudo> extraiConteudoImdb(String json){
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		for(Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImage = atributos.get("image");
			String ranking = atributos.get("ranking");
			
			Conteudo conteudo = new Conteudo(titulo, urlImage, ranking);
			
			conteudos.add(conteudo);
		}
		
		return conteudos;
	}

}
