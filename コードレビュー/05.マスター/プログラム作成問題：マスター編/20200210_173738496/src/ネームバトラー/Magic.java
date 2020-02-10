package ネームバトラー;

public class Magic {
	
	protected String name;
	protected int useMP;
	
	public Magic(String name,int useMP) {
		this.name=name;
		this.useMP=useMP;
	}
	
	public String GetName() {
		return this.name;
	}
	
	public int GetUseMP() {
		return this.useMP;
	}
	
	public void UseMagic(Player attacker,Player affected) {
		
	}
}
