package �l�[���o�g���[;

import java.util.*;

public class Strategy {
	String name;

	Strategy(String name) {
		this.name = name;
	}

	public void Action(Player attacker, Party defence) {

	}

	// �� ���P���� �_���Ώۂ����܂��Ă��Ȃ��Ƃ�
	protected void Aaction(Player attacker, Party defence) {
		Random random = new Random();
		int ra = 0, ran = 0;
		boolean boo = false;
		while (true) {
			ra = random.nextInt(defence.size());
			if (defence.get(ra).GetHP() > 0) {
				break;
			}
		}
		if (attacker.magicList.size() > 0) {
			ran = random.nextInt(100);
			// 50%�̊m���Ŗ��@���g��
			if (ran <= 50) {
				for (int i = 0; i < attacker.magicList.size(); i++) {
					// ���@���X�g�̒��ɍ��̂l�o�Ŏg���閂�@������Ȃ�g��
					if (attacker.GetMP() >= attacker.magicList.get(i).GetUseMP()) {
						boo = true;
					}
				}
			}

		}
		if (boo) {
			attacker.Magic(attacker, defence.get(ra));
		} else {
			attacker.Attack(defence.get(ra));
		}
	}

	// �� �_���Ώۂ����܂��Ă���Ƃ�
	protected void AAaction(Player attacker, Player defence) {
		// ���@�̎�true �U���̎�false
		boolean boo = false;
		if (attacker.magicList.size() > 0) {
			Random random = new Random();
			int ra = random.nextInt(100);
			// 50%�̊m���Ŗ��@���g��(MP�������)
			if (ra <= 50) {
				for (int j = 0; j < attacker.magicList.size(); j++) {
					if (attacker.GetMP() >= attacker.magicList.get(j).GetUseMP()) {
						ra = 1;
					}
				}
				if (ra == 1) {
					boo = true;
				}
			}
		}
		if (boo) {
			attacker.Magic(attacker, defence);
		} else {
			attacker.Attack(defence);
		}
	}
}
