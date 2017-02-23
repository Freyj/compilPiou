package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import scanner.SymbolTable;
import structure.Arbre;

public class Parser {
	File file;
	SymbolTable symTable;
	Arbre regles;
	
	public Parser(File f, Arbre regles) {
		file = f;
		symTable = new SymbolTable();
	}
	
	//shameful throw of exception
	public String getFileContent() throws Exception{
		String everything;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
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
	

}
