package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import scanner.SymbolTable;
import structure.Arbre;
import structure.Noeud;

public class Parser {
	File file;
	SymbolTable symTable;
	Arbre regles;
	
	public Parser(File f, Arbre regles) {
		file = f;
		symTable = new SymbolTable();
	}
	
	//shameful throw of exception
	public String getFileContent() throws Exception {
		String everything;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		}
		finally {
		    br.close();
		}
		return everything;
	}
	
	/**
	 * Methode pour créer des noeuds à partir d'une string définissant une regle
	 * @return
	 */
	public Vector<Noeud> creerNoeuds(String fileContent){
		Vector<Noeud> noeuds = new Vector<Noeud>();
		
		
		
		return noeuds;
	}

}
