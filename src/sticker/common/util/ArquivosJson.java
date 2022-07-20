package sticker.common.util;

public enum ArquivosJson {
	THE_MOVIE_DB("themoviedb-topRated"),
	NASA("nasa-apod");
	
	private String nomeArquivo;
	
	private ArquivosJson(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

}