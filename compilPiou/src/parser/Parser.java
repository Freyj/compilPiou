package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import scanner.SymbolTable;
import structure.Foret;
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

	private File file;
	private SymbolTable symTable;
	private Foret reglesCompilo;
	private UniteLexicale tokenActuel;
	private int compteurString = 0;
	private String contenuFichier;

	
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

	public Foret getReglesCompilo() {
		return reglesCompilo;
	}

	public void setReglesCompilo(Foret reglesCompilo) {
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
	public Parser(File f, Foret regles) {
		file = f;
		symTable = new SymbolTable();
		reglesCompilo = regles;
		tokenActuel = null;
		try {
			contenuFichier = getFileContent();
		} catch (Exception e) {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return everything;
	}

	//analyse : 
	//prend une unité lexicale donnée par scan
	//analyse taff
	//rescan sur l'élément
	//si on fait scan on passe à l'élément suivant
	//il faut un truc qui récupère les unités lexicales
	
	/**
	 * Fonction d'analyse
	 * @param regle
	 * @return
	 * TODO: fonction d'analyse à finir
	 */
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
					tokenActuel = getUniteLexicaleSuivante();
				}
				else {
					return false;
				}
			}
			//c'est un non-terminal
			else if (regleAt.getType() == AtomType.NONTERMINAL){
				boolean a = true;
				//on analyse si la regle correspond a une regle
				//(d'après le code de la regle
				//TODO: trouver cette condition et enlever cette merde
				if(a == true) {
					//analyse(reglesCompilo.getRegles().get(index)))) {
					//revoir la condition proprement
					if (regleAt.getAction() != 0) {
						g0Action(regleAt.getAction());
					}
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	/** 
	 * (équivalent de la fonction scan dans le cours)
	 * Scan la chaîne pour avancer au token suivant
	 * TODO:Finish Scan
	 */
	private UniteLexicale getUniteLexicaleSuivante() {
		//un stringbuilder pour build le string
		StringBuilder sb = new StringBuilder();
		//on prend le caractere suivant dans la chaine la où on 
		//s'était arrêté
		char nextChar = contenuFichier.charAt(compteurString);
		//on initialise le token pour avoir un retour
		//(si atomType null sur le retour > error)
		UniteLexicale token = new UniteLexicale("", "", 0, null);
		
		//on vérifie qu'on a pas atteint la fin du fichier
		
		while(compteurString <= contenuFichier.length()) {
			//on mange les espaces, parce qu'on s'en fiche
			while (nextChar == ' ') {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
			}
			
			//si on a un ' on doit en avoir un à la fin (idem ([
			if (nextChar == '\'')  {

			}	
			//si c'est pas une action (#), et tant que c'est des chars ou des chiffres,
			//on continue à accumuler les strings
			while(
					nextChar != '#'
					&& (Character.isAlphabetic(nextChar) ||Character.isDigit(nextChar)) ) {
				sb.append(nextChar);
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
			}
			//si c'est une action on ignore le #
			if (nextChar == '#') {
				nextChar = contenuFichier.charAt(compteurString);
				tokenActuel.setChaine(sb.toString());
				StringBuilder sb2 = new StringBuilder();
				//tant que c'est un chiffre, on avance
				while(Character.isDigit(nextChar)) {
					sb2.append(nextChar);
					++compteurString;
					nextChar = contenuFichier.charAt(compteurString);
				}
				//on change la chaine en int pour l'action
				token.setAction(Integer.parseInt(sb2.toString()));
			}

			if (nextChar == '-') {
				sb.append(nextChar);
				++compteurString;
				//le > après -
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == '>') {
					sb.append(nextChar);
					++compteurString;
				}

			}

			String res = sb.toString();
			token = new UniteLexicale(res, res, compteurString, null);
			//Affichage pour test
			System.out.println("Token : " + token.getChaine() +
					"\nAction : " + token.getAction() +
					"\nCode : " + token.getCode());

		}

		return token;
		

	}

	private void g0Action(int action) {
		
		switch(action) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7: 
				break;
			default:
				System.out.println("Erreur de G0 action : pas d'action " + action);
		}

	}





}
