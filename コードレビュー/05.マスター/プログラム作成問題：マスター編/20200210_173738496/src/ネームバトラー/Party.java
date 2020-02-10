package �l�[���o�g���[;

import java.util.ArrayList;

public class Party {
// =======================
// �t�B�[���h�ϐ�
// =======================
	private ArrayList<Player> members;
	private Strategy strategy;
// =======================
// �R���X�g���N�^
// =======================
	Party() {
		members = new ArrayList<Player>();
	}

// =======================
// Getter / Setter
// =======================
	/**
	 * �p�[�e�B�[�����o�[�� ArrayList �Ŏ擾����
	 */
	ArrayList<Player> GetMembers() {
		return members;
	}

// =======================
// protected ���\�b�h
// =======================
// =======================
// private ���\�b�h
// =======================
// =======================
// public ���\�b�h
// =======================
	/**
	 * �p�[�e�B�[�Ƀv���C���[��ǉ�����
	 * 
	 * @param player : �ǉ�����v���C���[
	 */
	public void AppendPlayer(Player player) {
		this.members.add(player);
	}

	/**
	 * �p�[�e�B�[����v���C���[�𗣒E������
	 * 
	 * @param player : ���E������v���C���[
	 */
	public void RemovePlayer(Player player) {
		this.members.remove(player);
	}

	public int size() {
	return this.members.size();
	}
	public Player get(int a) {
		return this.members.get(a);
	}
	public int totalHP() {
		int hp=0;
		for(int i=0;i<this.members.size();i++) {
			hp+=this.members.get(i).GetHP();
		}
		return hp;
	}
	
	public int totalMAXHP() {
		int hp=0;
		for(int i=0;i<this.members.size();i++) {
			hp+=this.members.get(i).GetMaxHP();
		}
		return hp;
	}
	
	public Strategy GetStrategy() {
		return this.strategy;
	}
	public void SetStrategy(Strategy s) {
		this.strategy=s;
	}
}