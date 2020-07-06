package nameBattler;

import java.util.Random;

//Magicクラス：魔法の基底クラス
public class Magic {

	// =======================
	// フィールド変数
	// =======================
	//魔法名
	protected String magicName;

	//消費MP
	protected int useMP;

	//最小ダメージ
	protected int minDamage;

	//最大ダメージ
	protected int maxDamage;

	//回復量
	protected int healAmount;

	//行動不能確率
	protected int bindRate;

	//スリップダメージ
	protected int slipDamage;

	//効果継続ターン
	protected int keepTurn;

	//乱数
	Random rand = new Random();

	// =======================
	// コンストラクタ
	// =======================
	public Magic(String magicName, int useMP, int minDamage, int maxDamage, int healAmount, int bindRate,
			int slipDamage, int keepTurn) {
		this.magicName = magicName;
		this.useMP = useMP;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.healAmount = healAmount;
		this.bindRate = bindRate;
		this.slipDamage = slipDamage;
		this.keepTurn = keepTurn;
	}

	// =======================
	// Getter/Setter
	// =======================

	//名前の取得
	public String GetName() {
		return magicName;
	}

	//消費MPを取得
	public int GetUseMP() {
		return useMP;
	}

	//与ダメージを取得
	public int GetDamage() {
		int damage = rand.nextInt(this.maxDamage - this.minDamage + 1) + this.minDamage;
		return damage;
	}

	//回復量を取得
	public int GetHealAmount() {
		return healAmount;
	}

	//行動不能確率を取得
	public int GetBindRate() {
		return bindRate;
	}

	//スリップダメージを取得
	public int GetSlipDamage() {
		return slipDamage;
	}

	//効果継続ターンを取得
	public int GetKeepTurn() {
		return keepTurn;
	}

	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

	//拘束の判定
	public boolean JudgeBind() {
		int randomNum = rand.nextInt(100) + 1;
		//拘束確率以下で拘束成功
		if(randomNum <= this.bindRate) {
			return true;
		}
		//失敗
		else {
			return false;
		}
	}

}
