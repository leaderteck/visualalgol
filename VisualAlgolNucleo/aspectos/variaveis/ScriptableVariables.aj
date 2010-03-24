package variaveis;

import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.comandos.InterpretarWhy;

public aspect ScriptableVariables {
	pointcut publicOperation():
        execution(public void org.mozilla.javascript.IdScriptableObject.put(..));
	
    pointcut setVariable(String name, Scriptable start, Object value):
        publicOperation() && args(name, start, value);
    
    before(String name, Scriptable start, Object value):
    	setVariable(name, start, value) {
    	//System.out.println("Aspect of " + thisJoinPoint.getThis().getClass().getSimpleName());
    	//System.out.println("--> old " + name + " = " + start.get(name, start));
    	//System.out.println("--> before puts " + name + " = " + value);
    	InterpretarWhy.getInstance().informarVariavelAlterada(name,start.get(name, start),value);
    }
    after(String name, Scriptable start, Object value) returning:
    	setVariable(name, start, value) {
    }
    
    after (String name, Scriptable start, Object value) throwing (RuntimeException e):
    	setVariable(name, start, value) {
    }

}
