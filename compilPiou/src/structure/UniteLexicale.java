package structure;

public class UniteLexicale {

	private String chaine;
	private String code;
	private int action;
	private AtomType type;
	public String getChaine() {
		return chaine;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public AtomType getType() {
		return type;
	}
	public void setType(AtomType type) {
		this.type = type;
	}
	
	public UniteLexicale(String chaine, String code, int action, AtomType type) {
		this.chaine = chaine;
		this.code = code;
		this.action = action;
		this.type = type;
	}
	
	
}
