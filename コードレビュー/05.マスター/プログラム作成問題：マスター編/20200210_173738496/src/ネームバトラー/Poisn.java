package �l�[���o�g���[;

public class Poisn extends Magic{
	
	public Poisn(String name,int mp) {
		super(name,mp);
	}
	
	public void UseMagic(Player attacker,Player affected) {
		int mp=attacker.GetMP();
		attacker.SetMP(mp-useMP);
		if (affected.GetCondition() == 2) {
			affected.SetCondition(3);
		} else {
			affected.SetCondition(1);
		}
		System.out.println(affected.GetName()+"�ɓł�^����");
	}
	public static void Poi(Player attacker) {
		System.out.println(attacker.GetName()+"�͓ł�HP���P�O������");
		attacker.SetHP(attacker.GetHP()-10);
	}

}
