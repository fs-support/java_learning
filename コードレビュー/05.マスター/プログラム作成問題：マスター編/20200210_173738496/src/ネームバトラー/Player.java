package �l�[���o�g���[;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// �v���C���[�N���X(�e��W���u�̊��N���X)
public class Player {
	// =======================
	// �t�B�[���h�ϐ�
	// =======================
	// ���O
	protected String name;
	// HP
	protected int hp;
	// �U����
	protected int str;
	// �h���
	protected int def;

	protected int luck;

	protected int agi;

	protected int mp;
	
	protected int maxHP;

	protected Party party;
	
	protected int conditoin=0;
	// 0����@1�Ł@2��� 3�łƖ��
	
	
	protected List<Magic> magicList=new ArrayList<Magic>();

	// =======================
	// �R���X�g���N�^
	// =======================
	/**
	 * �R���X�g���N�^
	 * 
	 * @param name : �v���C���[��
	 */
	public Player(String name) {
		this.name = name;

		// �L�����N�^�[�̃p�����[�^����
		MakeCharacter();
		SetMaxHP(this.hp);
	}

	// =======================
	// Getter / Setter
	// =======================
	
	public void SetCondition(int a) {
		this.conditoin=a;
	}
	
	public int GetCondition() {
		return this.conditoin;
	}
	
	public void SetHP(int a) {
		this.hp=a;
	}
	
	public int GetMaxHP() {
		return this.maxHP;
	}
	
	public void SetMaxHP(int hp) {
		this.maxHP=hp;
	}
	
	public int GetAGI() {
		return this.agi;
	}
	
	public int GetMP() {
		return this.mp;
	}
	
	public void SetMP(int useMP) {
		this.mp=useMP;
	}

	public int GetLUCK() {
		return this.luck;
	}
	
	public Party GetParty() {
		return this.party;
	}

	public void SetParty(Party p) {
		this.party = p;
	}

	/**
	 * �v���C���[�����擾����
	 * 
	 * @return �v���C���[��
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * ����HP���擾����
	 * 
	 * @return ����HP
	 */
	public int GetHP() {
		return this.hp;
	}

	/**
	 * �U���͂��擾����
	 * 
	 * @return �U����
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * �h��͂��擾����
	 * 
	 * @return �h���
	 */
	public int GetDEF() {
		return this.def;
	}

	// =======================
	// protected ���\�b�h
	// =======================
	/**
	 * ���O(name)����L�����N�^�[�ɕK�v�ȃp�����[�^�𐶐�����
	 */
	protected void MakeCharacter() {
		// �W���u���ƂɃI�[�o�[���C�h���ď������L�q���Ă�������
	}

	/**
	 * ���O(name)����n�b�V���l�𐶐����A�w�肳�ꂽ�ʒu�̐��l�����o��
	 * 
	 * @param index : ���Ԗڂ̐��l�����o����
	 * @param max   : �ő�l(�����I��0�`255�̒l�𐶐����邪�A0�`max�܂ł̒l�ɕ␳)
	 * @return ���l(0�`max) ��max���܂�
	 */
	protected int GetNumber(int index, int max) {
		try {
			// ���O����n�b�V���l�𐶐�����
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// �n�b�V���l����w�肳�ꂽ�ʒu�̕���������o���i�Q�������j
			String hex = digest.substring(index * 2, index * 2 + 2);

			// ���o����������i16�i���j�𐔒l�ɕϊ�����
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// �G���[
			e.printStackTrace();
		}
		return 0;
	}

	// =======================
	// private ���\�b�h
	// =======================

	// =======================
	// public ���\�b�h
	// =======================
	/**
	 * ���݂̃X�e�[�^�X�� System.out �ŕ\������
	 */
	public void PrintStatus() {
		System.out.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : AGI=%3d : LUCK=%3d)\n", this.GetName(), this.GetHP(),this.GetMP(), this.GetSTR(),
				this.GetDEF(),this.GetAGI(),this.GetLUCK());
	}

	public void Action1(Player attacker, Party enemy) {

	}

	/**
	 * �Ώۃv���C���[�ɍU�����s��
	 * 
	 * @param defender : �Ώۃv���C���[
	 */
	public void Attack(Player defender) {
		// �W���u���ƂɃI�[�o�[���C�h���ď������L�q���Ă�������
	}
	public void Magic(Player attacker,Player defender) {
		
	}
	// a  ����
	public boolean goodAisyou(Player player) {
		return true;
	}
	public boolean badAisyou(Player player) {
		return true;
	}
	
	/**
	 * �Ώۃv���C���[(target)�ɑ΂��ė^����_���[�W���v�Z����
	 * 
	 * @param target : �Ώۃv���C���[
	 * @return �_���[�W�l(0�`)
	 */
	protected int CalcDamage(Player target) {
		int damage=0;
		if(Clit()) {
			damage=GetSTR();
			System.out.println("��S�̈ꌂ");
		}
		else {
		 damage = GetSTR() - target.GetDEF();
		if (damage < 0) {
			damage = 0;
		}
		}
		if(goodAisyou(target)) {
			System.out.println("���ʔ��Q");
			damage=damage*2;
		}
		else if(badAisyou(target)) {
			System.out.println("���ʂ͂��܂ЂƂ�");
			damage=damage/2;
		}
		
		return damage;
		
	}

	/**
	 * �_���[�W���󂯂�
	 * 
	 * @param damage : �_���[�W�l
	 */
	protected void Damage(int damage) {
		// �_���[�W�l���AHP������������
		
		this.hp = Math.max(this.GetHP() - damage, 0);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	protected boolean Clit() {
		Random ra = new Random();
		int random = ra.nextInt(100);
		if (random <= this.luck) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean aaction(Player attacker) {
		int a=GetCondition();
		if(a==1||a==3) {
			Poisn.Poi(attacker);
			if(GetHP()<=0) {
				GetName();
				return false;
			}
		}	
		if(a==2||a==3) {
			if(Paralysis.Para(attacker)) {
			}
			else {
				return false;
			}
		}
		return true;
	}
}