package structure;

import java.util.Vector;

public class Foret {
	private Vector<Arbre> foret;
	
	public Foret() {
		foret = new Vector<Arbre>();
	}
	
	public void printForet() {
		int indent = 1;
		for (Arbre arbre : foret) {
			arbre.printArbre(indent);
			++indent;
		}
	}

}
