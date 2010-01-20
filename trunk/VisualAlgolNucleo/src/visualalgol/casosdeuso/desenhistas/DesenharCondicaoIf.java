package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import visualalgol.entidades.InstrucaoGenerica;

/**
 * O nome da classe deve comecar com Desenhar e o fim dela deve ser o nome da
 * entidade neste caso CondicaoIf
 */
public class DesenharCondicaoIf implements Desenhista {

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		Graphics gra = bi.getGraphics();
		int wPor2 = instrucao.getW() / 2;
		int h = instrucao.getH();
		int hPor2 = h / 2;
		
		// losangulo com pontos A=Norte,B=Leste,C=Sul,D=Oeste
		Point a = new Point(instrucao.getX(), instrucao.getY()-hPor2);
		Point b = new Point(a.x + wPor2, a.y + hPor2);
		Point c = new Point(a.x, a.y + h);
		Point d = new Point(a.x - wPor2, a.y + hPor2);
		Polygon p = new Polygon();
		p.addPoint(a.x, a.y);
		p.addPoint(b.x, b.y);
		p.addPoint(c.x, c.y);
		p.addPoint(d.x, d.y);
		p.addPoint(a.x, a.y);
		instrucao.setPoligono(p);

		gra.setColor(new Color(instrucao.getCor()));
		gra.fillPolygon(p);
		gra.setColor(Color.BLACK);
		gra.drawPolygon(p);
	}

}
