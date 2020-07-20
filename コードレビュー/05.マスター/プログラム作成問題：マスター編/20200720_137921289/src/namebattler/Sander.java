package namebattler;

public class Sander extends Magic {
	public Sander() {
		MakeMazic();
	}

	protected void MakeMazic(){
		this.muzicNo = 1;
		
		this.magicname = "サンダー";
		
		this.magicmp = 20;
		
		this.effect = "敵に10～30の防御力無視ダメージ";
	}
	
    /*
     * defender：攻撃を受けるプレイヤー
     * attacker：攻撃するプレイヤー
     */
	public void MagicAttack(Player defender,Player attacker) {
		System.out.println("効果は" + this.effect + "だ");
		
		int damage = CalcMazicDamage(defender);
		defender.Damage(damage,defender,attacker);
		attacker.UseMazicpoint(this.magicmp);
	}
	
}
