package structure;

import java.util.Vector;

/**
 * Structure pour gérer les arbres d'opération du compilateur
 * quand il n'y a qu'un sous arbre, il est toujours à droite
 *
 */

public class Arbre {
	private Vector<Noeud> regles;
	
	public Arbre() {
		regles = new Vector<Noeud>();
		initArbreWithGzero();
	}
	/**
	 * Methode pour initialiser l'arbre de règles avec les règles de G0
	 */
	public void initArbreWithGzero(){
		//regle 1
		//indentation bizarre pour bien voir les elements dedans
		Noeud ruleS = 
				new Conc(
						new Star(
								new Conc(
										new Conc(
												new Conc(new Atom("N", 0, AtomType.NONTERMINAL),
														 new Atom("->", 0, AtomType.TERMINAL )),
												new Atom("E", 0, AtomType.NONTERMINAL)), 
										new Atom(",", 1, AtomType.TERMINAL))
								),
						new Atom(";", 0, AtomType.TERMINAL)
						);
		regles.addElement(ruleS);
		
		//regle 2
		Noeud ruleN = new Atom("IDNTER", 2, AtomType.NONTERMINAL);
		regles.addElement(ruleN);
		
		//regle 3
		Noeud ruleE = 
				new Conc(
						new Atom("T", 0, AtomType.NONTERMINAL ),
						new Star(
								new Conc(
										new Atom("+", 0, AtomType.TERMINAL),
										new Atom("T", 3, AtomType.NONTERMINAL)))
						); 
		regles.addElement(ruleE);
		
		//regle 4
		Noeud ruleT = 
				new Conc(
						new Atom("F", 0, AtomType.NONTERMINAL),
						new Star(
								new Conc(
										new Atom(".", 0, AtomType.TERMINAL),
										new Atom("F", 4, AtomType.NONTERMINAL)
										)
								)
						);
		regles.addElement(ruleT);
		
		//regle 5
		Noeud ruleF =
				new Union(
						new Union(
								new Union(
										new Union(
												new Atom("IDNTER", 5, AtomType.NONTERMINAL),
												new Atom("ELTER", 5, AtomType.NONTERMINAL)
												),
										new Conc(
												new Atom("(", 0, AtomType.TERMINAL),
												new Conc(
														new Atom("E", 0, AtomType.NONTERMINAL),
														new Atom(")", 0, AtomType.TERMINAL)
														)
												)
										),
								new Star(
										new Atom("E", 6, AtomType.NONTERMINAL)
										)								
								),
						new Un(
								new Atom("E", 7, AtomType.NONTERMINAL)
								)
						);
		regles.addElement(ruleF);
	}
	
	
	public String imprimArbre() {
		StringBuilder sb = new StringBuilder();
		for (Noeud regle : regles) {
			sb.append("Regle ");
			sb.append(regles.indexOf(regle));
			sb.append("\n");
			int indent = 3;
			sb.append(regle.imprimNoeud(indent));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	public void addNoeud(Noeud n) {
		regles.addElement(n);
	}
	
	

}
