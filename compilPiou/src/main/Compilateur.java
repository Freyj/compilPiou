package main;

import java.io.File;
import java.util.Vector;

import parser.Parser;
import scanner.SymbolTable;
import structure.Arbre;
import structure.Foret;
import structure.Operation;

public class Compilateur {
	
	SymbolTable symbTable;
	static File gramFile = new File("/res/gzero");
	
	Parser p;
	
	public static void main(String[] args) {
		
		System.out.println("Test de l'affichage des arbres");
//		Arbre arbre1 = new Arbre(Operation.ATOM);
//		Arbre arbre = new Arbre(Operation.UN);
//		Arbre arbre3 = new Arbre(Operation.CONC, arbre1, arbre);
//		Arbre arbre4 = new Arbre(Operation.STAR, arbre3);
//		Arbre arbre5 = new Arbre(Operation.STAR, arbre4);
//		Arbre arbre6 = new Arbre(Operation.CONC, arbre5, arbre4);
//
//		System.out.println(arbre6.printArbre(3));
//		
//		System.out.println("Test de l'affichage d'une forêt");
//		Vector<Arbre> arbres = new Vector<Arbre>();
//		arbres.add(arbre1);
//		arbres.add(arbre);
//		arbres.add(arbre3);
//		arbres.add(arbre4);
//		arbres.add(arbre5);
//		arbres.add(arbre6);
//		
//		Foret f = new Foret(arbres);
//
//		System.out.println(f.printForet());
//		
		
		Parser p = new Parser(gramFile);

	}

}
