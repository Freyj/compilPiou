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
	
	public void initArbreWithGzero(){
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
	}
	
	
	
	
//	private Operation operation;
//	private Arbre arbreDroit;
//	private Arbre arbreGauche;
//	//TODO: deal with this (future me : hate past me)
//	private AtomType elements;
//	
//	/*
//	 * Constructeurs
//	 */
//	public Arbre(Operation op) {
//		operation = op;
//	}
//	
//	public Arbre(Operation op, Arbre droit, Arbre gauche){
//		operation = op;
//		arbreDroit = droit;
//		arbreGauche = gauche;
//	}
//	
//	public Arbre(Operation op, Arbre droit){
//		operation = op;
//		arbreDroit = droit;
//	}
//
//	/*
//	 * Getters & Setters
//	 */
//	
//	public Arbre getArbreDroit(){
//		return arbreDroit;
//	}
//	
//	public Arbre getArbreGauche(){
//		return arbreGauche;
//	}
//	
//	public Operation getOperation() {
//		return operation;
//	}
//	
//	public void setArbreDroit(Arbre droit) {
//		arbreDroit = droit;
//	}
//	
//	public void setArbreGauche(Arbre gauche) {
//		arbreGauche = gauche;
//	}
//	
//	public void setOperation(Operation op) {
//		operation = op;
//	}
//	
//	/**
//	 * Methode pour vérifier s'il s'agit d'un noeud sans enfants
//	 * @return boolean
//	 */
//	public boolean isLeaf() {
//		boolean result = false;
//		if (arbreDroit == null && arbreGauche == null) {
//			result = true;
//		}
//		return result;
//	}
//	
//	/**
//	 * Methode générant l'arbre avec GenConc
//	 */
//	public static Arbre arbreConc(Arbre a1, Arbre a2) {
//		Arbre arbre = new Arbre(Operation.CONC, a1, a2);
//		return arbre;
//	}
//	
//	/**
//	 * Methode générant l'arbre avec GenUnion
//	 */
//	public Arbre arbreUnion(Arbre a1, Arbre a2) {
//		Arbre arbre = new Arbre(Operation.UNION, a1, a2);
//		return arbre;
//	}
//	
//	/**
//	 * Methode générant l'arbre pour Atome
//	 */
//	
//	public Arbre arbreAtome(){
//		return new Arbre(Operation.ATOM);
//	}
//	
//	/**
//	 * Methode pour afficher les arbres
//	 */
//	public String printArbre(int indent) {
//		String result;
//		//for subsequents trees
//		int indentN = indent +4;
//		StringBuilder sb = new StringBuilder(); 
//		for (int i = 0; i< indent ; ++i) {
//			sb.append('-');
//		}
//		sb.append("> ");
//		switch (operation) {
//		case CONC:
//			sb.append("Conc");
//			sb.append("\n");
//			sb.append(arbreDroit.printArbre(indentN));
//			sb.append(arbreGauche.printArbre(indentN));
//			break;
//		case UNION:
//			sb.append("Union");
//			sb.append("\n");
//			sb.append(arbreDroit.printArbre(indentN));
//			sb.append(arbreGauche.printArbre(indentN));
//			break;
//		case UN:
//			sb.append("Un");
//			sb.append("\n");
//			break;
//		case ATOM:
//			sb.append("Atom");
//			sb.append("\n");
//			break;
//		case STAR:
//			sb.append("Star");
//			sb.append("\n");
//			sb.append(arbreDroit.printArbre(indentN));
//			break;
//		
//		}
//		result = sb.toString();
//		return result;
//	}
	
	
	

}
