package main;

import java.io.File;

import parser.Parser;
import scanner.SymbolTable;
import structure.Foret;

public class Compilateur {
	SymbolTable symbTable;
	static File gramFile = new File("res/gzero");
	//static File gramFile = new File("res/gplVeryBasic");
	static Foret regles;
	
	Parser p;
	
	public static void main(String[] args) {

		regles = new Foret(true);
		//System.out.println(regles.imprimArbre());
		Parser p = new Parser(gramFile, regles);
		System.out.println(p.getReglesCompilo().imprimArbre());
		
		/* Test affichage des unités lexicales
		  */
		for (int i = 0; i < 10 ; ++i ) {
		 
			System.out.println("Print Unite Lexicale");
			System.out.println(p.getUniteLexicaleSuivante().toString());
			//System.out.println(p.getContenuFichier().substring(0, p.getCompteurString()));
			
		}
		
	}

}
