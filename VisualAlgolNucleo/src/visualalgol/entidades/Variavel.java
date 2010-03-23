package visualalgol.entidades;

import java.io.Serializable;

public class Variavel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Object value;
	
	public Variavel(){
		
	}
	
	public Variavel(String name, Object value) {
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
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
}
