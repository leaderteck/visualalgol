package visualalgol.entidades;

public class Variavel {
	private String name;
	private Object value;
	
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
