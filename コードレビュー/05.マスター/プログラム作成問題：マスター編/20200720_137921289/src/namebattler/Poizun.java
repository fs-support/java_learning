package namebattler;

public class Poizun extends Magic {
	
	//コンストラクタ	
	public Poizun() {
		MakeMazic();
	}

	
	protected void MakeMazic(){
		this.muzicNo = 3;
		
		this.magicname = "ポイズン";
		
		this.magicmp = 10;
		
		this.effect = "3ターンの間、20の継続ダメージを与える";
	}

	public void MagicAttack(Player defender,Player attacker) {
		
			System.out.println(defender.name + "は毒状態になった");
			System.out.println("効果は" + this.effect);
			defender.hp -= 20;
			defender.condition = 2;
			attacker.UseMazicpoint(this.magicmp);
		
	}
	
}
