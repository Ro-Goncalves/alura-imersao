package gerador.figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
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
	           voto.equals("8.7") ? "The Big One" :
		                            "No One";
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
			
			FontRenderContext fontRenderContext = graphics.getFontRenderContext();
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);
			
			TextLayout textLayout = new TextLayout(textoImagem, font, fontRenderContext);

			Shape outline = textLayout.getOutline(null);			
			AffineTransform transform = graphics.getTransform();
			
			int xInicial = (int) ((largura - textLayout.getAdvance()) / 2);
			transform.translate(xInicial, novaAltura - 150);
			graphics.setTransform(transform);

			BasicStroke outlineStroke = new BasicStroke(largura * 0.004166f);
			graphics.setStroke(outlineStroke);

			graphics.setColor(Color.DARK_GRAY);
			graphics.draw(outline);
			graphics.setClip(outline);	
			
			ImageIO.write(novaImagem, "png", new File(caminhoImagem + nomeImagem));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
