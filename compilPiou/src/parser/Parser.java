package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

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
 * Parser 
 *
 */
public class Parser {

	private File file;
	private String contenuFichier;
	private SymbolTable symTable;
	private Foret reglesCompilo;
	private UniteLexicale tokenActuel;
	private int compteurString = 0;
	//pile pour g0action
	private Stack<Noeud> sousArbres = new Stack<>();


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
	public Parser(File f) {
		file = f;
		symTable = new SymbolTable();
		reglesCompilo = new Foret(true);
		try {
			contenuFichier = getFileContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//on scanne une première fois
		tokenActuel = getUniteLexicaleSuivante();
		//System.out.println(tokenActuel.getChaine());
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

	/**
	 * Fonction d'analyse
	 * @param regle
	 * @return true si l'analyse s'est bien passée
	 * FIXME: bug
	 * TODO:debug
	 */
	public boolean analyse(Noeud regle) {
		regle.imprimNoeud(3);
		//si c'est une conc on teste les deux arbres
		if (regle instanceof Conc) {
			//System.out.println("\t if (analyse(((Conc) regle).getDroit()))");
			System.out.println("Une Conc !");
			if((analyse(((Conc) regle).getGauche()))) {
				if (analyse(((Conc) regle).getDroit())) {
					return true;
				}
			}
			return false;
		}
		//si c'est une union
		else if (regle instanceof Union) {
			System.out.println("Une Union!" );
			if (analyse(((Union) regle).getGauche())) {
				return true;
			}
			else {
				return analyse(((Union) regle).getDroit());
			}
		}
		//stare
		else if (regle instanceof Star) {
			System.out.println("Une Star!");
			while (analyse(((Star) regle).getStare())){
				System.out.println("star");
			}
			return true;
		}
		//un
		else if (regle instanceof Un) {
			System.out.println("Une Un!");
			return analyse(((Un) regle).getNoeud());
		}
		//atom
		else if (regle instanceof Atom) {
			System.out.println("Une Atom!");
			Atom regleAt = (Atom) regle;
			System.out.println("Je suis le code : " + regleAt.getCode());
			//System.out.println("Atome : " + regleAt.getType());
			//si c'est un terminal
			if (regleAt.getType() == AtomType.TERMINAL) {
				if (regleAt.getCode() == tokenActuel.getCode() ) {
					//le token correspond en terme de code
					if (regleAt.getAction() != 0) {
						g0Action(regleAt.getAction());
					}
					tokenActuel = getUniteLexicaleSuivante();
					return true;
				}
				else {
					return false;
				}
			}
			//c'est un non-terminal
			//FIXME: bug is here
			else if (regleAt.getType() == AtomType.NONTERMINAL){
				//on analyse si la regle correspond a une regle
				//(d'après le code de la regle
				Atom n = (Atom) reglesCompilo.getReglesb().get(regleAt.getCode());
				System.out.println(n.getCode());
				if(analyse(reglesCompilo.getReglesb().get(regleAt.getCode()))) {
					if (regleAt.getAction() != 0) {
						g0Action(regleAt.getAction());
					}
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		}
		return false;
	}

	/** 
	 * (équivalent de la fonction scan dans le cours)
	 * Scan la chaîne pour avancer au token suivant
	 */
	public UniteLexicale getUniteLexicaleSuivante() {
		//on prend le caractere suivant dans la chaine la où on 
		//s'était arrêté
		char nextChar = contenuFichier.charAt(compteurString);
		//on initialise le token pour avoir un retour
		//(si atomType null sur le retour > error)
		UniteLexicale token = new UniteLexicale("", "", 0, null);

		//on vérifie qu'on a pas atteint la fin du fichier
		while(compteurString < contenuFichier.length() -1 ) {
			//de base l'action est à 0
			int action = 0;
			//on mange les espaces, parce qu'on s'en fiche
			while (Character.isWhitespace(nextChar) && compteurString < contenuFichier.length() ) {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				//System.out.println("Y a un espace qu'on enlève");
			}
			//on gère les caractères terminaux direct
			if (nextChar == '.') {
				//System.out.println("J'ai vu un point.");
				++compteurString;
				return new UniteLexicale(".", ".", action, AtomType.TERMINAL);
			}
			else if (nextChar == ',') {
				//System.out.println("J'ai vu une virgule.");
				++compteurString;
				return new UniteLexicale(",", ",", action, AtomType.TERMINAL);
			}
			else if (nextChar == ';') {
				//System.out.println("J'ai vu un point-virgule");
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
				//System.out.println("J'ai vu un crochet ouvrant");
				++compteurString;
				return new UniteLexicale("[", "[", action, AtomType.TERMINAL);				
			}
			else if (nextChar == ']') {
				//System.out.println("J'ai vu un crochet fermant");				
				++compteurString;
				return new UniteLexicale("]", "]", action, AtomType.TERMINAL);	
			}
			else if (nextChar == '+') {
				//System.out.println("J'ai vu un plus");
				++compteurString;
				return new UniteLexicale("+", "+", action, AtomType.TERMINAL);				
			}
			else if (nextChar == '(') {
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == '/') {
					//System.out.println("J'ai vu une parenthese avec un antislash");
					++compteurString;
					return new UniteLexicale("(/", "(/", action, AtomType.TERMINAL);
				}
				//System.out.println("J'ai vu une parenthese toute seule");
				return new UniteLexicale("(", "(", action, AtomType.TERMINAL);
			}
			else if (nextChar =='/') {
				//System.out.println("Je vois un slash");
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				if (nextChar == ')') {
					//System.out.println("Je vois un slash avec une parenthese");
					++compteurString;
					return new UniteLexicale("/)", "/)", action, AtomType.TERMINAL);
				}
			}
			//si y a des guillements, on process jusqu'au guillement suivant
			else if (nextChar == '\'') {
				//System.out.println("Je vois un guillement ouvrant");
				++compteurString;
				nextChar = contenuFichier.charAt(compteurString);
				StringBuilder elterBuilder = new StringBuilder();
				while ((nextChar != '\'') && (compteurString < contenuFichier.length())) {
					//System.out.println("J'ai vu un caractere : " + nextChar);
					nextChar = contenuFichier.charAt(compteurString);
					//si y a une action entre les guillements
					if (nextChar == '#') {
						//System.out.println("J'ai vu un dièse");
						StringBuilder actionBuilder = new StringBuilder();
						++compteurString;
						nextChar = contenuFichier.charAt(compteurString);
						while(Character.isDigit(nextChar)) {
							//System.out.println("On est encore dans le chiffre de l'action");
							actionBuilder.append(nextChar);
							++compteurString;
							nextChar = contenuFichier.charAt(compteurString);
							//System.out.println(actionBuilder.toString());
						}
						action = Integer.parseInt(actionBuilder.toString());				
					}
					else if (nextChar != '\'') {
						elterBuilder.append(nextChar);
					}
					//System.out.println(elterBuilder.toString());
					++compteurString;
				}
				if (compteurString == contenuFichier.length()) {
					System.out.println("Il y a un oubli de guillement ' quelque part.");
				}
				else {
					String elter = elterBuilder.toString();
					//System.out.println("L'élément terminal : " + elter);
					return new UniteLexicale(elter,"ELTER", action, AtomType.TERMINAL);
				}
			}
			//si on a un debut de chaine (peut pas commencer par un chiffre)
			else if (Character.isAlphabetic(nextChar)) {
				//System.out.println("Oh un identifiant");
				StringBuilder identerBuilder = new StringBuilder();
				while(Character.isAlphabetic(nextChar) 
						|| Character.isDigit(nextChar)) {
					nextChar = contenuFichier.charAt(compteurString);
					if (nextChar == '#') {
						//System.out.println("Un dièse!");
						StringBuilder actionBuilder = new StringBuilder();
						++compteurString;
						nextChar = contenuFichier.charAt(compteurString);
						while(Character.isDigit(nextChar)) {
							actionBuilder.append(nextChar);
							++compteurString;
							nextChar = contenuFichier.charAt(compteurString);
							
						}
						//System.out.println(actionBuilder.toString());
						action = Integer.parseInt(actionBuilder.toString());
												
					}
					else if (!Character.isWhitespace(nextChar) 
							&& (Character.isAlphabetic(nextChar) 
							|| Character.isDigit(nextChar))) {
						//System.out.println("Un char ou int ou autre : " + nextChar);
						identerBuilder.append(nextChar);
						++compteurString;
					}
				}
				String ident = identerBuilder.toString();
				System.out.println(ident);
				return new UniteLexicale(ident, "IDNTER", action, AtomType.NONTERMINAL);
			}
		}

		return token;


	}
	
	/**
	 *  Affiche toutes les unités lexicales de la grammaire
	 *  prise en compte, et garde en mémoire le compteur 
	 *  pour éviter les soucis si jamais on l'appel en cours de route
	 */
	public void affichUnitesLexicales() {
		int oldcptValue = compteurString;
		compteurString = 0;
		System.out.println("Affichage des Unites Lexicales de la grammaire prise en compte");
		while(compteurString < contenuFichier.length()-1) {
			System.out.println(getUniteLexicaleSuivante());
		}
		compteurString = oldcptValue;
	}
	
	/**
	 * G0 action
	 * @param action
	 */
	private void g0Action(int action) {
		//pile
		switch(action) {
		case 1:
			Noeud t1a = sousArbres.pop();
			Noeud t1b = sousArbres.pop();
			Atom t2atome = (Atom) t1b;
			reglesCompilo.addNoeudMap(t2atome.getCode(), t1a);
			break;
		case 2:
			getUniteLexicaleSuivante();
			Noeud t2 = new Atom(rechercheDicoNT(tokenActuel.getCode()), action, tokenActuel.getType());
			sousArbres.push(t2);
			break;
		case 3:
			Noeud t3a = sousArbres.pop();
			Noeud t3b = sousArbres.pop();
			sousArbres.push(new Union(t3a, t3b));
			break;
		case 4:
			Noeud t4a = sousArbres.pop();
			Noeud t4b = sousArbres.pop();
			sousArbres.push(new Conc(t4a, t4b));
			break;
		case 5:
			getUniteLexicaleSuivante();			
			Noeud t5;
			if (tokenActuel.getType().equals(AtomType.TERMINAL)) {
				t5 = new Atom(rechercheDicoT(tokenActuel.getCode()), action, tokenActuel.getType());
			}
			else {
				t5 = new Atom(rechercheDicoNT(tokenActuel.getCode()), action, tokenActuel.getType());
			}
			break;
		case 6:
			Noeud t6 = sousArbres.pop();
			sousArbres.push(new Star(t6));
			break;
		case 7: 
			Noeud t7 = sousArbres.pop();
			sousArbres.push(new Un(t7));
			break;
		default:
			System.out.println("Erreur de G0 action : pas d'action " + action);
		}

	}

	/**
	 * Va chercher dans la table de symboles si le symbole existe
	 * l'ajoute si besoin
	 * @param code
	 * @return renvoi le code pour l'utilisation
	 */
	private String rechercheDicoT(String chaineToken) {
		System.out.println("TESTING DICO T");
		symTable.addSymbol(chaineToken, true);
		return chaineToken;
	}

	private String rechercheDicoNT(String chaineToken) {
		System.out.println("TESTING DICO NT");
		symTable.addSymbol(chaineToken, false);
		return chaineToken;
	}

}
