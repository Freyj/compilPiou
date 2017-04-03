package main;

import java.io.File;

import parser.Parser;
import scanner.SymbolTable;
import structure.Arbre;
import structure.Atom;
import structure.AtomType;
import structure.Conc;
import structure.Star;

public class Compilateur {
	
	SymbolTable symbTable;
	static File gramFile = new File("res/gzero");
	static Arbre regles;
	
	Parser p;
	
	public static void main(String[] args) {

		regles = new Arbre(true);
		//System.out.println(regles.imprimArbre());
		Parser p = new Parser(gramFile, regles);
		System.out.println(p.getReglesCompilo().imprimArbre());
		

	}

}
