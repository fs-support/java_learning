package ネームバトラー;

import java.util.Random;

public class Thunder extends Magic{
	public Thunder(String name,int mp) {
		super(name,mp);
	}
	
	public void UseMagic(Player attacker,Player affected) {
		int mp=attacker.GetMP();
		attacker.SetMP(mp-useMP);
		Random ra=new Random();
		int damage=ra.nextInt(20)+10;
		if(attacker.goodAisyou(affected)) {
			System.out.println("効果抜群");
			damage=damage*2;
		}
		else if(attacker.badAisyou(affected)) {
			System.out.println("効果はいまひとつ");
			damage=damage/2;
		}
		affected.Damage(damage);
		System.out.println(affected.GetName()+"に"+damage+"のダメージ");
	}
}
