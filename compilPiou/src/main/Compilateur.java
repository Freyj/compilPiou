package main;

import java.io.File;

import parser.Parser;
import scanner.SymbolTable;
import structure.Atom;
import structure.AtomType;
import structure.Conc;
import structure.Star;

public class Compilateur {
	
	SymbolTable symbTable;
	static File gramFile = new File("/res/gzero");
	
	Parser p;
	
	public static void main(String[] args) {
		
		
		Parser p = new Parser(gramFile);
		
		//test affichage d'un noeud atome
		Atom a = new Atom("F", 2, AtomType.TERMINAL);
		//String s = a.imprimNoeud(3);
		
		Atom b = new Atom("G", 2, AtomType.TERMINAL);
		
		Conc c = new Conc(a, b);
		Conc d = new Conc(c, new Conc(b, a));
		Star e = new Star(d);
		String ba = e.imprimNoeud(3);
		System.out.println(ba);

	}

}
