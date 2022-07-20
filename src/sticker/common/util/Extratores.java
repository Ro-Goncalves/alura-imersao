package sticker.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sticker.model.Conteudo;

public class Extratores {
	
	public List<Conteudo> extraiConteudosNasa(String json){
		
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		for(Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImage = atributos.get("url");
			
			Conteudo conteudo = new Conteudo(titulo, urlImage);
			
			conteudos.add(conteudo);
		}
		
		return conteudos;
		
	}
	
	public List<Conteudo> extraiConteudoImdb(String json){
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		for(Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImage = atributos.get("image");
			
			Conteudo conteudo = new Conteudo(titulo, urlImage);
			
			conteudos.add(conteudo);
		}
		
		return conteudos;
	}

}
