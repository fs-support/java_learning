package �l�[���o�g���[;
import java.util.Random;
public class Paralysis extends Magic {

	public Paralysis(String name, int mp) {
		super(name, mp);
	}

	public void UseMagic(Player attacker, Player affected) {
		int mp = attacker.GetMP();
		attacker.SetMP(mp - useMP);
		if (affected.GetCondition() == 1) {
			affected.SetCondition(3);
		} else {
			affected.SetCondition(2);
		}
		System.out.println(affected.GetName()+"�ɖ�Ⴢ�^����");
	}
	
	public static boolean Para(Player attacker) {
		Random ra=new Random();
		int random=ra.nextInt(100)+1;
		if(random<=20) {
			System.out.println(attacker.GetName()+"�͖�Ⴢœ����Ȃ�");
			return false;
		}
		else {
			return true;
		}
	}

}
