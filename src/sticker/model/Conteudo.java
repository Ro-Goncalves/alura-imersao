package sticker.model;

public class Conteudo {
	
	private final String titulo;
	private final String urlImagem;
	
	public Conteudo(String titulo, String urlImage) {
		this.titulo= titulo;
		this.urlImagem = urlImage;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getUrlImagem() {
		return urlImagem;
	}
	
}
