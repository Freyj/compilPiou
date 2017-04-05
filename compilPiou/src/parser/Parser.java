package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import scanner.SymbolTable;
import structure.Atom;
import structure.AtomType;
import structure.Conc;
import structure.Foret;
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
	public UniteLexicale getUniteLexicaleSuivante() {
		//on prend le caractere suivant dans la chaine la où on 
		//s'était arrêté
		char nextChar = contenuFichier.charAt(compteurString);
		//on initialise le token pour avoir un retour
		//(si atomType null sur le retour > error)
		UniteLexicale token = new UniteLexicale("", "", 0, null);

		//on vérifie qu'on a pas atteint la fin du fichier
		while(compteurString < contenuFichier.length()) {
			//de base l'action est à 0
			int action = 0;
			//on mange les espaces, parce qu'on s'en fiche
			while (Character.isWhitespace(nextChar)) {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				System.out.println("Y a un espace qu'on enlève");
			}
			//on gère les caractères terminaux direct
			if (nextChar == '.') {
				System.out.println("J'ai vu un point.");
				++compteurString;
				return new UniteLexicale(".", ".", action, AtomType.TERMINAL);
			}
			else if (nextChar == ',') {
				System.out.println("J'ai vu une virgule.");
				++compteurString;
				return new UniteLexicale(",", ",", action, AtomType.TERMINAL);
			}
			else if (nextChar == ';') {
				System.out.println("J'ai vu un point-virgule");
				++compteurString;
				return new UniteLexicale(";", ";", action, AtomType.TERMINAL);
			}
			else if (nextChar == '-') {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == '>') {
					++compteurString;
					return new UniteLexicale("->", "->", action, AtomType.TERMINAL);
				}
				else {
					System.out.println("Un - sans >, bizarre.");
				}
			}
			else if (nextChar == '[') {
				System.out.println("J'ai vu un crochet ouvrant");
				++compteurString;
				return new UniteLexicale("[", "[", action, AtomType.TERMINAL);				
			}
			else if (nextChar == ']') {
				System.out.println("J'ai vu un crochet fermant");				
				++compteurString;
				return new UniteLexicale("]", "]", action, AtomType.TERMINAL);	
			}
			else if (nextChar == '+') {
				System.out.println("J'ai vu un plus");
				++compteurString;
				return new UniteLexicale("+", "+", action, AtomType.TERMINAL);				
			}
			else if (nextChar == '(') {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == '/') {
					System.out.println("J'ai vu une parenthese avec un antislash");
					++compteurString;
					return new UniteLexicale("(/", "(/", action, AtomType.TERMINAL);
				}
				System.out.println("J'ai vu une parenthese toute seule");
				return new UniteLexicale("(", "(", action, AtomType.TERMINAL);
			}
			else if (nextChar =='/') {
				System.out.println("Je vois un slash");
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == ')') {
					System.out.println("Je vois un slash avec une parenthese");
					++compteurString;
					return new UniteLexicale("/)", "/)", action, AtomType.TERMINAL);
				}
			}
			//si y a des guillements, on process jusqu'au guillement suivant
			else if (nextChar == '\'') {
				System.out.println("Je vois un guillement ouvrant");
				++compteurString;
				StringBuilder elterBuilder = new StringBuilder();
				while ((nextChar != '\'') && (compteurString != contenuFichier.length())) {
					System.out.println("J'ai vu un caractere : " + nextChar);
					nextChar = contenuFichier.charAt(compteurString);
					//si y a une action entre les guillements
					if (nextChar == '#') {
						System.out.println("J'ai vu un dièse");
						StringBuilder actionBuilder = new StringBuilder();
						while(Character.isDigit(nextChar)) {
							System.out.println("On est encore dans le chiffre de l'action");
							actionBuilder.append(nextChar);
							++compteurString;
							nextChar = contenuFichier.charAt(compteurString);
						}
						action = Integer.parseInt(actionBuilder.toString());				
					}
					elterBuilder.append(nextChar);
					++compteurString;
				}
				if (compteurString == contenuFichier.length()) {
					System.out.println("Il y a un oubli de guillement ' quelque part.");
				}
				else {
					String elter = elterBuilder.toString();
					System.out.println("L'élément terminal : " + elter);
					return new UniteLexicale(elter,"ELTER", action, AtomType.NONTERMINAL);
				}
			}
			//si on a un debut de chaine (peut pas commencer par un chiffre)
			else if (Character.isAlphabetic(nextChar)) {
				System.out.println("Oh un identifiant");
				StringBuilder identerBuilder = new StringBuilder();
				while(Character.isAlphabetic(nextChar) 
						|| Character.isDigit(nextChar)
						|| (nextChar == '#')) {
					nextChar = contenuFichier.charAt(compteurString);
					if (nextChar == '#') {
						System.out.println("Un dièse!");
						StringBuilder actionBuilder = new StringBuilder();
						while(Character.isDigit(nextChar)) {
							actionBuilder.append(nextChar);
							++compteurString;
							nextChar = contenuFichier.charAt(compteurString);
						}
						action = Integer.parseInt(actionBuilder.toString());
												
					}
					else if (!Character.isWhitespace(nextChar)) {
						System.out.println("Un char ou int ou autre : " + nextChar);
						identerBuilder.append(nextChar);
						++compteurString;
					}
				}
				String ident = identerBuilder.toString();
				return new UniteLexicale(ident, "IDNTER", action, AtomType.NONTERMINAL);
			}
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
