package namebatoler;

import java.util.Random;

public class Mazic {

	//サンダー：１、ファイア：２、ヒール：３、パラライズ：４、ポイズン：5
		protected int muzicNo;
		// 魔法の名前
		protected String magicname;
		// 消費MP
		protected int magicmp;
		//	効果	
		protected String effect;

	//コンストラクタ
	public Mazic() {

	}

	//魔法の名前を取得
	public String GetMagicName() {
		return magicname;
	}

	//消費MPを取得
	public int GetMagicMP() {
		return magicmp;
	}

	//効果を取得
	public String GetEffect() {
		return effect;
	}

	//魔法を作る
	protected void MakeMazic(Player player) {

	}

	//攻撃をするプレイヤーと攻撃を受けるプレイヤーを渡す
	protected void MagicAttack(Player defender,Player atacker) {
		// 魔法ごとにオーバーライドして処理を記述してください
	}
	
	
	
	/**
	 * 対象プレイヤー(target)に対して与える魔法でのダメージを計算する
	 *
	 * @param target
	 *            : 対象プレイヤー
	 * @return 魔法ダメージ値(0～)
	 */
	protected int CalcMazicDamage(Player target) {
		Random rand = new Random();
		int damage = rand.nextInt(20) + 10;

		return damage;
	}
	
	protected void magicprint() {
		System.out.println();
	}
	

	
}