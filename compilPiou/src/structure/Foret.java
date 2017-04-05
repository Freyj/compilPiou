package structure;

import java.util.HashMap;
import java.util.Vector;

/**
 * Structure pour gérer les arbres d'opération de la gzéro
 * quand il n'y a qu'un sous arbre, il est toujours à droite
 *
 */

public class Foret {
	private Vector<Noeud> regles;
	private HashMap<String, Noeud> regles2;
		
	public Vector<Noeud> getRegles() {
		return regles;
	}

	public void setRegles(Vector<Noeud> regles) {
		this.regles = regles;
	}
	
	public HashMap<String, Noeud> getReglesb() {
		return regles2;
	}
	
	public void setReglesb(HashMap<String, Noeud> map) {
		regles2 = map;
	}

	public Foret(boolean izGzero) {
		if (izGzero) {
			regles = new Vector<Noeud>();
			regles2 = new HashMap<String, Noeud>();
			initArbreWithGzero();
		}
		else{
			
		}
	}
	/**
	 * Methode pour générer la regle S
	 * @return
	 */
	public Noeud genS() {
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
		return ruleS;
	}
	
	public Noeud genN() {
		return new Atom("IDNTER", 2, AtomType.TERMINAL);
	}
	
	public Noeud genE() {
		Noeud ruleE = 
				new Conc(
						new Atom("T", 0, AtomType.NONTERMINAL ),
						new Star(
								new Conc(
										new Atom("+", 0, AtomType.TERMINAL),
										new Atom("T", 3, AtomType.NONTERMINAL)))
						); 
		return ruleE;
	}
	
	public Noeud genT() {
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
		return ruleT;
	}
	
	public Noeud genF() {
		Noeud ruleF = new Union(
				new Union(
						new Union(
								new Union(
										new Atom("IDNTER", 5, AtomType.TERMINAL),
										new Atom("ELTER", 5, AtomType.TERMINAL)
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
		return ruleF;
	}
	
	/**
	 * Methode pour initialiser la foret de règles avec les règles de G0
	 */
	private void initArbreWithGzero(){
		//regle 1
		Noeud ruleS = genS();
		regles.addElement(ruleS);
		regles2.put("S", ruleS);
		
		//regle 2
		Noeud ruleN = genN();
		regles.addElement(ruleN);
		regles2.put("N", ruleN);
		
		//regle 3
		Noeud ruleE = genE();				
		regles.addElement(ruleE);
		regles2.put("E", ruleE);
		
		//regle 4
		Noeud ruleT = genT();
		regles.addElement(ruleT);
		regles2.put("T", ruleT);
		
		//regle 5
		Noeud ruleF = genF();
		regles.addElement(ruleF);
		regles2.put("F", ruleF);
	}	
	/**
	 * Creation d'un String avec les regles dans le vecteur
	 * @return
	 */
	public String imprimArbre() {
		StringBuilder sb = new StringBuilder();
		sb.append("Affichage de la forêt de règles de la GZéro\n");
		for (Noeud regle : regles) {
			sb.append("Regle ");
			sb.append(regles.indexOf(regle)+1);
			sb.append("\n");
			int indent = 3;
			sb.append(regle.imprimNoeud(indent));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Creation d'un String avec les regles dans la map
	 * @return
	 */
	public String imprimArbreMap() {
		StringBuilder sb = new StringBuilder();
		sb.append("Affichage de la foret\n");
		for (String s : regles2.keySet()){
			sb.append("Regle ");
			sb.append(s);
			sb.append("\n");
			int indent = 3;
			sb.append(regles2.get(s).imprimNoeud(indent));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/*
	 *
	 */
	public void addNoeud(Noeud n) {
		regles.addElement(n);
	}
	
	public void addNoeudMap(String cle, Noeud n) {
		regles2.put(cle,  n);
	}
	
	

}
