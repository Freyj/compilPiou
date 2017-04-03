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
import structure.AtomType;
import structure.Conc;
import structure.Noeud;
import structure.Star;
import structure.Un;
import structure.Union;
import structure.UniteLexicale;


/**
 * Parser lexical
 *
 */
public class Parser {
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public SymbolTable getSymTable() {
		return symTable;
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}

	public Arbre getReglesCompilo() {
		return reglesCompilo;
	}

	public void setReglesCompilo(Arbre reglesCompilo) {
		this.reglesCompilo = reglesCompilo;
	}

	public UniteLexicale getTokenActuel() {
		return tokenActuel;
	}

	public void setTokenActuel(UniteLexicale tokenActuel) {
		this.tokenActuel = tokenActuel;
	}

	public int getCompteurString() {
		return compteurString;
	}

	public void setCompteurString(int compteurString) {
		this.compteurString = compteurString;
	}

	public String getContenuFichier() {
		return contenuFichier;
	}

	public void setContenuFichier(String contenuFichier) {
		this.contenuFichier = contenuFichier;
	}

	private File file;
	private SymbolTable symTable;
	private Arbre reglesCompilo;
	private UniteLexicale tokenActuel;
	private int compteurString = 0;
	private String contenuFichier;

	public Parser(File f, Arbre regles) {
		file = f;
		symTable = new SymbolTable();
		reglesCompilo = regles;
		tokenActuel = null;
		try {
			contenuFichier = getFileContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//shameful throw of exception
	public String getFileContent() {
		String everything = "";
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		int deb = 0;
		while (!done) {
			String item = fileContent.substring(deb,  i);
			if (symTable.contains(item) == 0) {
				++i;
			}
			else {

			}
			//si le String est vide, on sort de la boucle
			if (fileContent.equals("")) {
				done = true;
			}

			++i;
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


	boolean analyse(Noeud regle) {
		//si c'est une conc on teste les deux arbres
		if (regle instanceof Conc) {
			if (analyse(((Conc) regle).getDroit()))  {
				return analyse(((Conc) regle).getGauche());
			}
			else {
				return false;
			}
		}
		//si c'est une union
		else if (regle instanceof Union) {
			if (analyse(((Union) regle).getGauche())) {
				return true;
			}
			else {
				return analyse(((Union) regle).getDroit());
			}
		}
		//stare
		else if (regle instanceof Star) {
			//TODO:check
			//boolean res = true;
			while (analyse(((Star) regle).getStare())){
				//System.out.println("star");
			}
		}
		//un
		else if (regle instanceof Un) {
			return analyse(((Un) regle).getNoeud());
		}
		//atom
		else if (regle instanceof Atom) {
			Atom regleAt = (Atom) regle;
			//si c'est un terminal
			if (regleAt.getType() == AtomType.TERMINAL) {
				if (regleAt.getCode() == tokenActuel.getCode() ) {
					//le token correspond en terme de code
					boolean res = true;
					if (regleAt.getAction() != 0) {
						g0Action(regleAt.getAction());
					}
					getUniteLexicaleSuivante();
				}
				else {
					return false;
				}
			}
			//c'est un non-terminal
			else if (regleAt.getType() == AtomType.NONTERMINAL){
				boolean a = true;
				//on analyse si la regle correspond a une regle (d'après le code de la regle
				if(a == true) {
					//analyse(reglesCompilo.getRegles().get(index)))) {
					//revoir la condition proprement
					if (regleAt.getAction() != 0) {
						g0Action(regleAt.getAction());
					}
					
				}
				else {
					return false;
				}
			}
		}



		return false;
	}

	/** 
	 * Scan la chaîne pour avancer au token suivant
	 */
	private void getUniteLexicaleSuivante() {
		StringBuilder sb = new StringBuilder();
		char nextChar = contenuFichier.charAt(compteurString);
		sb.append(nextChar);
		++compteurString;
		//si c'est un # -> action
		while(nextChar != '#') {
			
		}
		// TODO Auto-generated method stub

	}

	private void g0Action(int action) {
		// TODO Auto-generated method stub

	}





}
