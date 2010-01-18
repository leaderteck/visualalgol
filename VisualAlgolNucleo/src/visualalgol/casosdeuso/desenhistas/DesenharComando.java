package visualalgol.casosdeuso.desenhistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import visualalgol.entidades.InstrucaoGenerica;

public class DesenharComando implements Desenhista {

	@Override
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi) {
		Graphics gra = bi.getGraphics();
		gra.setColor(Color.BLACK);
		// losangulo com pontos A=Norte,B=Leste,C=Sul,D=Oeste
		Point a = new Point(instrucao.getX() - 30, instrucao.getY());
		Point b = new Point(a.x + 60, a.y);
		Point c = new Point(b.x, b.y + 30);
		Point d = new Point(a.x, a.y + 30);
		gra.drawLine(a.x, a.y, b.x, b.y);
		gra.drawLine(b.x, b.y, c.x, c.y);
		gra.drawLine(c.x, c.y, d.x, d.y);
		gra.drawLine(d.x, d.y, a.x, a.y);
	}

}
