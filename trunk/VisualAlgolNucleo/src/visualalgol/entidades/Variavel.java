package visualalgol.entidades;

import java.io.Serializable;

public class Variavel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private int passo=-1;
	
	public Variavel(){
		
	}
	
	public Variavel(String name, String value, int passo) {
		super();
		this.name = name;
		this.value = value;
		this.passo = passo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	/**
	 * @return the passo
	 */
	public int getPasso() {
		return passo;
	}

	/**
	 * @param passo the passo to set
	 */
	public void setPasso(int passo) {
		this.passo = passo;
	}
}
