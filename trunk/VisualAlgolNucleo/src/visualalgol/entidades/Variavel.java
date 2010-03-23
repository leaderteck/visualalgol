package visualalgol.entidades;

import java.io.Serializable;

public class Variavel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	
	public Variavel(){
		
	}
	
	public Variavel(String name, String value) {
		super();
		this.name = name;
		this.value = value;
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
}
