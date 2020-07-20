package namebattler;

import java.util.Random;

public class Paralysis extends Magic{
	
	Random use = new Random();
	int paraizusucces = use.nextInt(100);

	public Paralysis() {
		MakeMazic();
	}
	
	protected void MakeMazic(){
		this.muzicNo = 1;
		
		this.magicname = "パラライズ";
		
		this.magicmp = 10;
		
		this.effect = "一定確率で1ターン行動不能";
	}
	
	public void MagicAttack(Player defender,Player attacker) {
		System.out.println("効果は" + this.effect);
		
		if (paraizusucces < 100) {
			System.out.println(defender.GetName() + "は麻痺した！（１ターン行動不能）\n");
			defender.condition = 1;
		} else {
			System.out.println("残念！レジストされた！\n");
		attacker.UseMazicpoint(this.magicmp);
		}
	}

	
}
