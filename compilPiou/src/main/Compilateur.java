package main;

import scanner.SymbolTable;
import structure.Arbre;
import structure.Operation;

public class Compilateur {
	
	SymbolTable symbTable;
	
	public static void main(String[] args) {
		Arbre arbre1 = new Arbre(Operation.ATOM);
		Arbre arbre = new Arbre(Operation.UN);
		Arbre arbre3 = new Arbre(Operation.CONC, arbre1, arbre);
		Arbre arbre4 = new Arbre(Operation.STAR, arbre3);
		
		System.out.println(arbre4.printArbre(3));
	}

}
