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
			Extratores extratores = new Extratores();
			ClienteHttp cliente = new ClienteHttp();
			GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
			
			//UtilProperties properties = new UtilProperties();
			//properties.setApiKey("themoviedb_key");
		
			//String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
			String url = "http://localhost:8080/linguagens";			
					
			String json = cliente.BuscaDados(url);	
		
			//List<Conteudo> conteudos = extratores.extraiConteudosNasa(json);
			List<Conteudo> conteudos = extratores.extraiConteudoImdb(json);
		
			for( int i = 0; i < 2; i++) {
				Conteudo conteudo = conteudos.get(i);
				
				InputStream inputStream;				
				inputStream = new URL(conteudo.urlImagem()).openStream();				
				String nomeArquivo = conteudo.titulo();
				String ranking = conteudo.voto();
				
				geradora.criar(inputStream, nomeArquivo, ranking);
				
				System.out.println(conteudo.titulo());
			}
	} catch (IOException e) {			
		new RuntimeException(e);
	} 
	}
}
