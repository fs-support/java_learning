// 麻痺を与える魔法
public class Paralyze extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "パライズ";
	// 消費MP
	protected int usemp = 10;

	// =======================
	// コンストラクタ
	// =======================
	Paralyze() {

	}

	// =======================
	// Getter / Setter
	// =======================
	// 魔法のMPの取得
	public String GetName() {
		return this.name;
	}

	public int GetUseMP() {
		return this.usemp;
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

	/**
	 * 防御側プレイヤーを確率で毒状態にする
	 * @param activePlayer : 魔法を行使するプレイヤー
	 * @param passivePlayer : 対象プレイヤー
	 */
	public void effect(Player activePlayer, Player passivePlayer) {

		// MPが足りている場合の処理
		// 魔法を行使する側のMPを使用する
		System.out.println(activePlayer.GetName() + " の " + this.name);

		activePlayer.UseMP(this.usemp);

		// 魔法の成功判定をする
		if (RandomGenerator.RandomJudge(20)) {
			// 判定に成功した時
			passivePlayer.SetParalyze(true);

			System.out.println(passivePlayer.GetName() + " は マヒ状態になった");

		} else {
			// 判定に失敗した時
			System.out.println(passivePlayer.GetName() + " には何も起こらなかった");
		}

	}
}
