package �l�[���o�g���[;

public class Brave extends Player {

	// =======================
	// �t�B�[���h�ϐ�
	// =======================


	// =======================
	// �R���X�g���N�^
	// =======================
	public Brave(String name)
	{
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
	protected void MakeCharacter()
	{
		// ��m�̃p�����[�^�𖼑O���琶������
		this.hp = GetNumber(0, 200)+100;
		this.mp= GetNumber(0,40);
		this.str = GetNumber(0, 70)+40;
		this.def = GetNumber(0, 70)+30;
		this.luck=GetNumber(0,100);
		this.agi=GetNumber(0,50);
	}
	public void Action1(Player attacker,Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
		}
	}
	
	public boolean goodAisyou(Player target) {
		if(target instanceof Fighter) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean badAisyou(Player target) {
		if(target instanceof Priest) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * �Ώۃv���C���[�ɍU�����s��
	 * @param defender : �Ώۃv���C���[
	 */
	@Override
	public void Attack(Player defender)
	{
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
     this.str+=5;
     System.out.println(GetName()+"�̍U���͂��オ����");
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
