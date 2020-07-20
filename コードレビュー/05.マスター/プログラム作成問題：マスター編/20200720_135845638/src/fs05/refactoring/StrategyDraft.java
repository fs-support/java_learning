package fs05.refactoring;

//【作戦を管理するクラス】

public class StrategyDraft {

	// フィールド変数
	protected String name; // 作戦名
	protected Player target; // ダメージ処理完了済みのターゲット

	// コンストラクタ
	public StrategyDraft(String strategyName) {
		this.name = strategyName;
	}

	// 作戦名を取得する
	public String getStrategyName() {
		return this.name;
	}

	// 作戦を実行する
	public void strategy(Player attacker, Party enemy) {
		// 作戦ごとにオーバーライドして処理を記述してください
	}

	// 作戦用カウントをリセットする
	public void countReset() {
		// "該当する"作戦ごとにオーバーライドして処理を記述してください
		// (現状では作戦④でのみ使用)
	}

	// ダメージ処理完了済みのターゲットを返す
	public Player processedDamage() {
		return this.target; // 作戦ごとにオーバーライドして処理を記述してください
	}

}