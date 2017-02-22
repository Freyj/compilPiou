package structure;

import java.util.Vector;

/**
 * Classe qui gère les arbres (les cinq premiers sont générés à la main 
 * à partir de la G0)
 *
 */
public class Foret {
	private Vector<Arbre> foret;
	
	public Foret(Vector<Arbre> arbre) {
		foret = arbre;
	}
	
	public String printForet() {
		StringBuilder sb = new StringBuilder();
		int indent = 1;
		for (Arbre arbre : foret) {
			//sb.append(arbre.printArbre(indent));
			sb.append("****Séparation Arbres ****\n");
			++indent;
		}
		return sb.toString();
	}
	
	public Vector<Arbre> getArbres(){
		return foret;
	}
	
	public void genForet() {
		
	}

}
