package sticker.model;

public class Conteudo {
	
	private final String titulo;
	private final String urlImagem;
	private final String voto;
	
	public Conteudo(String titulo, String urlImage, String voto) {
		this.titulo= titulo;
		this.urlImagem = urlImage;
		this.voto = voto;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getUrlImagem() {
		return this.urlImagem;
	}
	
	public String getVoto() {
		return this.voto;
	}
	
}
