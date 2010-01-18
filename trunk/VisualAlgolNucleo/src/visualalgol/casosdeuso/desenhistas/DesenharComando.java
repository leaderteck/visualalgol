package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import visualalgol.entidades.InstrucaoGenerica;

public class DesenharComando implements Desenhista {

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		int w = instrucao.getW();
		int wPor2 = w / 2;
		int h = instrucao.getH();
		
		Graphics gra = bi.getGraphics();
		
		// losangulo com pontos A=Norte,B=Leste,C=Sul,D=Oeste
		Point a = new Point(instrucao.getX() - wPor2, instrucao.getY());
		Point b = new Point(a.x + w, a.y);
		Point c = new Point(b.x, b.y + h);
		Point d = new Point(a.x, a.y + h);
		Polygon p = new Polygon();
		p.addPoint(a.x,a.y);
		p.addPoint(b.x,b.y);
		p.addPoint(c.x,c.y);
		p.addPoint(d.x,d.y);
		p.addPoint(a.x,a.y);
		instrucao.setPoligono(p);
		gra.setColor(new Color(instrucao.getCor()));
		gra.fillPolygon(p);
		gra.setColor(Color.BLACK);
		gra.drawPolygon(p);
	}

}
