package sticker.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import gerador.figuras.GeradorDeFigurinhas;
import sticker.common.util.Extratores;
import sticker.common.util.UtilProperties;
import sticker.http.ClienteHttp;
import sticker.model.Conteudo;

public class AppStickerFromApi {
	public static void main(String[] args){	
		try {
		//UtilProperties properties = new UtilProperties();
		//properties.setApiKey("themoviedb_key");
		
		String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";		
		ClienteHttp cliente = new ClienteHttp();
		
		String json = cliente.BuscaDados(url);	
		
		Extratores extratores = new Extratores();
		List<Conteudo> conteudos = extratores.extraiConteudosNasa(json);
		
		GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
		
		for( int i = 0; i < 3; i++) {
			Conteudo conteudo = conteudos.get(i);
			
			InputStream inputStream;
			
				inputStream = new URL(conteudo.urlImagem()).openStream();
			
			String nomeArquivo = "saida/" + conteudo.titulo() + ".png";
			
			geradora.criar(inputStream, nomeArquivo, "");
			
			System.out.println(conteudo.titulo());
		}
	} catch (IOException e) {			
		new RuntimeException(e);
	} 
	}
}
