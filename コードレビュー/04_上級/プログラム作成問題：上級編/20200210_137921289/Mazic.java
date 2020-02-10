package namebatoler;

public class Mazic {

	// 魔法の名前
	private String magicname;
	// 消費MP
	private int magicmp;
	//	効果	
	private String effect;
	
	public Mazic(String magicname,int magicmp,String effect){
		this.magicname = magicname;
		this.magicmp = magicmp;
		this.effect = effect;
	}
	
	//魔法の名前を取得
	public String GetMagicName(){
		return magicname;
	}
	
	//消費MPを取得
	public int GetMagicMP(){
		return magicmp;
	}
	
	//効果を取得
	public String GetEffect(){
		return effect;
	}
}