package namebatoler;

public class Heel extends Mazic{

	public Heel() {
		MakeMazic();
	}
	
	protected void MakeMazic(){
		this.muzicNo = 3;
		
		this.magicname = "ヒール";
		
		this.magicmp = 20;
		
		this.effect = "HPを50回復";
	}
	
	public void MagicAttack(Player defender,Player attacker) {
		System.out.println("効果は" + effect + "だ\n");
		attacker.hp += 50;
		attacker.UseMazicpoint(this.magicmp);
	}

	
}
