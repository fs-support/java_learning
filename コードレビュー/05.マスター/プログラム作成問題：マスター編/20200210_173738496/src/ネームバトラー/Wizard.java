package �l�[���o�g���[;

import java.util.Random;

public class Wizard extends Player {

	// =======================
	// �t�B�[���h�ϐ�
	// =======================

	// =======================
	// �R���X�g���N�^
	// =======================
	public Wizard(String name) {
		super(name);
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected ���\�b�h
	// =======================
	/**
	 * ���O(name)����L�����N�^�[�ɕK�v�ȃp�����[�^�𐶐�����
	 */
	@Override
	protected void MakeCharacter() {
		// ��m�̃p�����[�^�𖼑O���琶������
		this.hp = GetNumber(0, 100) + 50;
		this.mp = GetNumber(0, 50) + 30;
		this.str = GetNumber(0, 50);
		this.def = GetNumber(0, 50);
		this.luck = GetNumber(0, 100);
		this.agi = GetNumber(0, 40) + 20;
		this.magicList.add(new Fire("�t�@�C�A", 10));
		this.magicList.add(new Thunder("�T���_�[", 20));
	}

	public void Action1(Player attacker, Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
		}
	}

	public boolean goodAisyou(Player player) {
		if (player instanceof Priest) {
			return true;
		} else {
			return false;
		}
	}

	public boolean badAisyou(Player player) {
		if (player instanceof Fighter) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �Ώۃv���C���[�ɍU�����s��
	 * 
	 * @param defender : �Ώۃv���C���[
	 */
	@Override
	public void Attack(Player defender) {
		// �^����_���[�W�����߂�
		System.out.println(GetName() + "�̍U���I");
		int damage = CalcDamage(defender);

		// ���߂��_���[�W��Ώۃv���C���[�ɗ^����
		System.out.println(defender.GetName() + "��" + damage + "�̃_���[�W�I");
		defender.Damage(damage);

		// �|�ꂽ����
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "�͗͐s����...");
		}
	}

	// ���@ ���@���X�g�̒�����g���閂�@�������_���Ō��߂�
	public void Magic(Player attacker, Player affected) {
		System.out.println(GetName() + "�̖��@");
		Random ra = new Random();
		while (true) {
			int random = ra.nextInt(magicList.size());
			if (GetMP() >= magicList.get(random).GetUseMP()) {
				System.out.println(magicList.get(random).GetName());
				magicList.get(random).UseMagic(attacker, affected);
				if (affected.GetHP() <= 0) {
					System.out.println(affected.GetName() + "�͗͐s����...");
				}
				break;
			}
		}
	}

	// =======================
	// private ���\�b�h
	// =======================

	// =======================
	// public ���\�b�h
	// =======================
}
