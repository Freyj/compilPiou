package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import scanner.SymbolTable;
import structure.Arbre;
import structure.Atom;
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
		//virer les espaces car ils ne sont pas importants
		fileContent = fileContent.replace(" ", "");
		
		//on parcourt la string
		//un booléen pour voir si on a fini le string
		//un i pour parcourir
		boolean done = false;
		int i = 0;
		while (!done) {
			String item = fileContent.substring(0,  i);
			if (symTable.contains(item) > 0) {
				
			}
			
			//si le String est vide, on sort de la boucle
			if (fileContent.equals("")) {
				done = true;
			}
		}
		
		
		return noeuds;
	}
	
	//analyse : 
	//prend une unité lexicale donnée par scan
	//analyse taff
	//rescan sur l'élément
	//si on fait scan on passe à l'élément suivant
	
	//il faut un truc qui récupère les unités lexicales
	
	Vector<Atom> getLexicalUnits(String totalThings) {
		Vector<Atom> resultVect = new Vector<Atom>();
		
		
		
		return resultVect;
	}
	

}
