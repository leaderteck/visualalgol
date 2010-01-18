package visualalgol.casosdeuso.desenhistas;

import java.awt.image.BufferedImage;

import visualalgol.entidades.InstrucaoGenerica;

public interface Desenhista {
	public void desenhar(InstrucaoGenerica instrucao, BufferedImage bi);
}
