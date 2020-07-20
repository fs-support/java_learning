package namebattler;


public class Fire extends Magic{

	public Fire() {
		MakeMazic();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	protected void MakeMazic(){
		this.muzicNo = 1;
		
		this.magicname = "ファイア";
		
		this.magicmp = 10;
		
		this.effect = "敵に10～30の防御力無視ダメージ";
	}
	
    /*
     * defender：攻撃を受けるプレイヤー
     */
	public void MagicAttack(Player defender,Player attacker) {
		System.out.println("効果は" + this.effect + "だ");
		
		int damage = CalcMazicDamage(defender);
		defender.Damage(damage,defender,attacker);
		attacker.UseMazicpoint(this.magicmp);
	}
	
}
