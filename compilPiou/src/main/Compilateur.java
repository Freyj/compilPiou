package main;

import java.io.File;

import parser.Parser;
import scanner.SymbolTable;
import structure.Foret;

public class Compilateur {
	SymbolTable symbTable;
	static File gramFile = new File("res/gzero");
	//static File gramFile = new File("res/gplVeryBasic");
	
	Parser p;
	
	public static void main(String[] args) {
		//System.out.println(regles.imprimArbre());
		Parser p = new Parser(gramFile);
		/*
		 * Affichage des Arbres de la GZero
		 */
		System.out.println(p.getReglesCompilo().imprimArbreMap());
		
		/* Test affichage des unités lexicales
		  */
		//p.affichUnitesLexicales();
		
		/*
		 * Test analyse
		 */
		//boolean boo= p.analyse(p.getReglesCompilo().getReglesb().get("S"));
		
		//System.out.println(boo);
		
	}

}
