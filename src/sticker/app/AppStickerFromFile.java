package sticker.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import gerador.figuras.GeradorDeFigurinhas;
import sticker.common.util.Extratores;
import sticker.common.util.UtilJson;
import sticker.model.Conteudo;

public class AppStickerFromFile {	
	final static String NEGRITO = "\u001B[1m";
	final static String RESET = "\u001B[0m";
	final static String COR_TITULO = "\u001B[38;2;254;181;0m";
	final static String FUNDO_TITULO = "\u001B[48;2;234;214;164m";	
	final static String COR_LINHA = "\u001B[38;2;178;129;7m";
	final static String COR_EMOJI = "\u001B[38;2;164;123;22m";
	
	public static void main(String[] args) {
		
		UtilJson utilJson = new UtilJson();
		Extratores extratores = new Extratores();
		GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
		
		//The Movie Db
		Object json = utilJson.getArquivoJson("themoviedb-topRated");
		List<Conteudo> conteudos = extratores.extraiConteudosTheMovieDb(json);
		
		//Nasa
		//Object json = utilJson.getArquivoJson("nasa-apod");
		//List<Conteudo> conteudos = extratores.extraiConteudosNasa(json);
		
		conteudos.forEach((conteudo) -> {
			try {
				InputStream inputStream;	
				inputStream = new URL(conteudo.getUrlImagem()).openStream();
				String nomeArquivo = conteudo.getTitulo().replace(":", " -");
				String voto = conteudo.getVoto();
				
				Double doubleVote = Double.parseDouble(voto);			
				Double macas = (doubleVote - doubleVote.intValue()) * 10;
				
				geradora.criar(inputStream, nomeArquivo, voto);
				
				System.out.print(COR_LINHA);
				System.out.println("-".repeat(100));  
				System.out.print(RESET);	
				
				System.out.println(NEGRITO + COR_TITULO);
				System.out.print("Título:");
				System.out.print(RESET + " ");
     			System.out.print(nomeArquivo);
				
				System.out.println(NEGRITO + COR_TITULO);
				System.out.print("Imagem:");
				System.out.print(RESET + " ");
				System.out.print(conteudo.getUrlImagem());
			
				System.out.println(NEGRITO + COR_TITULO);
				System.out.print("Avaliação:");
				System.out.print(RESET + " ");
				System.out.print(voto + " ");			
				
				System.out.print(COR_EMOJI);
				System.out.println("🍏".repeat((int)Math.round(macas)));			
				System.out.print(RESET);
			} catch (MalformedURLException e) {				
				throw new RuntimeException(e);
			} catch (IOException e) {				
				throw new RuntimeException(e);
			}
		});	
	}
}
