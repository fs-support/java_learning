package �l�[���o�g���[;

import java.util.ArrayList;
import java.util.List;

// �h��@�h�䂪�Ⴂ�G��D��
public class Strategy3 extends Strategy{
	public Strategy3(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		List<Player> enemy = new ArrayList<Player>();
		int enumber=0;
		for (int i = 0; i < defence.size(); i++) {
			enemy.add(defence.get(i));
		}

		// �\�[�g ���@�h�䂪�Ⴂ���ɕ��ѕς�
		for (int i = 0; i < enemy.size(); i++) {
			for (int j = 0; j < enemy.size() - i - 1; j++) {
				if (enemy.get(j).GetDEF() > enemy.get(j + 1).GetDEF()) {
					Player player = enemy.get(j);
					enemy.set(j, enemy.get(j + 1));
					enemy.set(j + 1, player);
				}
			}
		}
		for(int i=0;i<enemy.size();i++) {
			if(enemy.get(i).GetHP()>0) {
				enumber=i;
				break;
			}
		}
		AAaction(attacker,enemy.get(enumber));
	}
}
