package �l�[���o�g���[;

//�v���C���[�F��m
public class Fighter extends Player {

	// =======================
	// �t�B�[���h�ϐ�
	// =======================

	// =======================
	// �R���X�g���N�^
	// =======================
	public Fighter(String name) {
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
		this.hp = GetNumber(0, 200) + 100;
		this.mp = GetNumber(0, 0);
		this.str = GetNumber(0, 70) + 30;
		this.def = GetNumber(0, 70) + 30;
		this.luck = GetNumber(0, 100);
		this.agi = GetNumber(0, 50);
	}
	
	public boolean goodAisyou(Player player) {
		if(player instanceof Wizard) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean badAisyou(Player player) {
		if(player instanceof Brave) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void Action1(Player attacker, Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
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

	public void Magic(Player defender) {

	}

	// =======================
	// private ���\�b�h
	// =======================

	// =======================
	// public ���\�b�h
	// =======================
}
