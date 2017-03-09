package scanner;

import java.util.Vector;

public class SymbolTable {
	
	//two maps one for terminal, one for non terminal??
	private Vector<String> symbolesTerminaux;
	public Vector<String> getSymbolesTerminaux() {
		return symbolesTerminaux;
	}

	public void setSymbolesTerminaux(Vector<String> symbolesTerminaux) {
		this.symbolesTerminaux = symbolesTerminaux;
	}

	public Vector<String> getSymbolesNonTerminaux() {
		return symbolesNonTerminaux;
	}

	public void setSymbolesNonTerminaux(Vector<String> symbolesNonTerminaux) {
		this.symbolesNonTerminaux = symbolesNonTerminaux;
	}

	private Vector<String> symbolesNonTerminaux;
	
	public SymbolTable() {
		symbolesTerminaux = new Vector<String>();
		symbolesNonTerminaux = new Vector<String>();
		remplirTableGZero();
	}
	
	/**
	 * On ajoute un symbole que si il n'est pas présent
	 * 
	 */
	public void addSymbol(String code, boolean isTerminal){
		if (isTerminal) {
			if (!symbolesTerminaux.contains(code)) {
				symbolesTerminaux.add(code);
			}
		}
		else {
			if (!symbolesNonTerminaux.contains(code)) {
				symbolesNonTerminaux.add(code);
			}
		}		
	}
	
	public void remplirTableGZero(){
		
		String[] tabZeroTerm = {";", ",", "->", "+", ".", "IDNTER", "ELTER", "[", "]", "(", ")", "(/", "/)" };
		String[] tabZeroNonTerm = {"N", "E", "T", "F"};
		
		for (String s : tabZeroTerm) {
			addSymbol(s, true);
		}
		
		for (String s : tabZeroNonTerm) {
			addSymbol(s, false);
		}
		
	}
	
	/**
	 * Détermine si un élément existe dans le symbolTable
	 * renvoie un int : 0 si l'élément n'est pas présent,
	 * 1 si l'élément est terminal, 2 si il est non terminal
	 * 
	 */
	public int contains(String item) {
		int resultat = 0;
		
		if (getSymbolesTerminaux().contains(item)) {
			resultat = 1;
		}
		
		if (getSymbolesNonTerminaux().contains(item)) {
			resultat = 2;
		}
		
		return resultat;
	}
	

	
}
