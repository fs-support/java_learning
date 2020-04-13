package mujinto;

public class Surviver {
	private int currentHP;
	private int maxHP;

	//コンストラクタ
	Surviver(int currentHP, int maxHP){
		this.currentHP = currentHP;
		this.maxHP = maxHP;
	}

	//-------------------------------------
	//メソッド
	//-------------------------------------
	//HP取得
	int HP() {
		return currentHP;
	}
	//食べる
	void Eat(boolean judge, int heelingHP) {
		if(judge) {
			//成功
			currentHP += heelingHP;
			//HP上限
			if(currentHP > maxHP) {
				currentHP = maxHP;
			}
		}else {
			//失敗
			currentHP = 0;
		}
	}
	//食べない
	void DoNotEat() {
		currentHP = currentHP - 10;
	}

}
