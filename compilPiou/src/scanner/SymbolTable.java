package scanner;

import java.util.Vector;

public class SymbolTable {
	
	//two maps one for terminal, one for non terminal??
	private Vector<String> symbolesTerminaux;
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
		//TODO add les symbols de Gzero
		String[] tabZeroTerm = {""};
		String[] tabZeroNonTerm = {""};
		
		for (String s : tabZeroTerm) {
			addSymbol(s, true);
		}
		
		for (String s : tabZeroNonTerm) {
			addSymbol(s, false);
		}
		
	}

	
}
