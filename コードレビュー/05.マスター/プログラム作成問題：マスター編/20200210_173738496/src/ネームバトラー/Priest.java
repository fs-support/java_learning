package �l�[���o�g���[;

import java.util.Random;

public class Priest extends Player {

	// =======================
	// �t�B�[���h�ϐ�
	// =======================

	// =======================
	// �R���X�g���N�^
	// =======================
	public Priest(String name) {
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
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(0, 60) + 10;
		this.def = GetNumber(0, 60) + 10;
		this.luck = GetNumber(0, 100);
		this.agi = GetNumber(0, 40) + 20;
		this.magicList.add(new Cure("�L���A", 20));
		this.magicList.add(new Poisn("�|�C�Y��", 10));
		this.magicList.add(new Paralysis("�p�����C�Y", 10));
	}

	public void Action1(Player attacker, Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
		}
	}

	public boolean goodAisyou(Player player) {
		if (player instanceof Brave) {
			return true;
		} else {
			return false;
		}
	}

	public boolean badAisyou(Player player) {
		if (player instanceof Wizard) {
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
