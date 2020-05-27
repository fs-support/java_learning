package fs05_Master_Refactoring;

//【作戦を管理するクラス】

public class StrategyDraft {

	// フィールド変数
	protected String strategyname;	// 作戦名
	protected Player Target;		// ダメージ処理完了済みのターゲット


	// コンストラクタ
	public StrategyDraft(String name){
		this.strategyname = name;
	}

	// 作戦名を取得する
	public String GetStrategyName(){
		return this.strategyname;
	}

	// 作戦を実行する
	public void Strategy(Player attacker , Party Enemy){
		// 作戦ごとにオーバーライドして処理を記述してください
	}

	// ダメージ処理完了済みのターゲットを返す
	public Player ProcessedDamage(){
		return this.Target;		// 作戦ごとにオーバーライドして処理を記述してください
	}

}
