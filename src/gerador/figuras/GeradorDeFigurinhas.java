package gerador.figuras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
	
	private String absPath = new File("").getAbsolutePath();	
	
	public String textoImagem(String voto) {
		
		return voto.equals("8.5") ? "The Third One" : 
               voto.equals("8.6") ? "The Second One" : 
	           voto.equals("8.7") ? "The First One" :
		                            "The Last One";
	}
	
	public void criar(InputStream input, String nomeArquivo, String voto) {
		String caminhoImagem = absPath + File.separator + "img" + File.separator;
		String nomeImagem = nomeArquivo + ".png";
		String textoImagem = textoImagem(voto);
		
		try {			
			BufferedImage imagemMemoria = ImageIO.read(input);
			
			int largura = imagemMemoria.getWidth();
			int altura = imagemMemoria.getHeight();			
			int novaAltura = altura + 200;
			
			BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
			
			Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
			graphics.drawImage(imagemMemoria, 0, 0, null);
			
			graphics.setFont(new Font("Impact", Font.BOLD, 40));
			graphics.setColor(Color.DARK_GRAY);
			graphics.drawString(textoImagem, 0, novaAltura - 100);
			
			ImageIO.write(novaImagem, "png", new File(caminhoImagem + nomeImagem));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GeradorDeFigurinhas gerar = new GeradorDeFigurinhas();		
		try {
			String nomeImagem = "O Senhor dos Anéis - O Retorno do Rei";
			InputStream url = new URL("https://image.tmdb.org/t/p/w500/izPNMzffsgZUvlbiYlPxjFr3TAa.jpg").openStream();
			gerar.criar(url, nomeImagem, "8.6");
			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}

}
