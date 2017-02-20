package structure;

import java.util.Vector;

public class NonTerminal extends AtomType{
	private Vector<AtomType> elements;
	
	public NonTerminal(String n) {
		isTerminal = false;
		nom = n;
	}

}
