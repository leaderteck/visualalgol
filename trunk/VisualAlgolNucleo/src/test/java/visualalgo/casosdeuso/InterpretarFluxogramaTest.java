package visualalgo.casosdeuso;

import visualalgol.casosdeuso.InterpretarFluxograma;



public class InterpretarFluxogramaTest{
	public static void main(String[] args) {
		//fluxo.set
		InterpretarFluxograma.tratarStringDeCondicao(null);
		String res = InterpretarFluxograma.tratarStringDeCondicao("a = \"teste = teste \"");
		if(!res.equals("a == \"teste = teste \"")){
			throw new RuntimeException(res);
		}else{
			System.out.println("Ok " + res);
		}
		res = InterpretarFluxograma.tratarStringDeCondicao("a = \"teste \\\" = teste \"");
		if(!res.equals("a == \"teste \\\" = teste \"")){
			throw new RuntimeException(res);
		}else{
			System.out.println("Ok " + res);
		}
	}
}
