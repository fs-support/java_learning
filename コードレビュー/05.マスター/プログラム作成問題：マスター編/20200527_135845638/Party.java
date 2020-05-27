package fs05_Master_Refactoring;

//【パーティークラス】_プレイヤーをパーティー単位で管理するクラス
import java.util.ArrayList;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;


	// [作戦リスト][作戦番号][ダメージ処理完了済みプレイヤー]のフィールド
	private ArrayList<StrategyDraft> strategy = new ArrayList<StrategyDraft>();
	private int strategyNumber;
	private Player processedPlayer;

	// 瀬苦戦④専用変数
	private int attackCount;


	// =======================
	// コンストラクタ
	// =======================
	Party() {
		members = new ArrayList<Player>();

		// 作戦リスト
		strategy.add(new Strategy1("\"敵をミンチにするだけの簡単なオシゴト\""));
		strategy.add(new Strategy2("\"急で悪いんだけどコレ、なる早でよろしく(ﾟ∀ﾟ)\""));
		strategy.add(new Strategy3("\"..なる早っていつまでですか？タスクが組めないので具体的な納期を提示して下さいこの野郎\""));
		strategy.add(new Strategy4("\"この案件、なんかイイ感じにやっといてねん(*´з`)\""));
		strategy.add(new Strategy5("\"..イイ感じってなんですか？具体的にどうして欲しいんですか？怒っていいですか？怒りますよ\""));

		// 作戦④専用変数の初期化
		attackCount = 0;
	}


	// パーティーメンバーをArrayList で取得する
	ArrayList<Player> GetMembers() {
		return members;
	}


	// 作戦をArrayList で取得する
	ArrayList<StrategyDraft> GetStrategyList(){
		return strategy;
	}


	// パーティーにプレイヤーを追加する
	// @param player : 追加するプレイヤー
	public void AppendPlayer(Player player) {
		members.add(player);
	}


	// パーティーからプレイヤーを離脱させる
	// @param player : 離脱させるプレイヤー
	public void RemovePlayer(Player player) {
		members.remove(player);
	}


	// 番号を振って作戦を決定
	public void setStrategy(int number){
		this.strategyNumber = number;
	}


	// 決定した作戦を取得する
	public StrategyDraft getStrategy(){
		return strategy.get(strategyNumber);
	}


	// 決定した作戦を実行
	public void Operation(Player attacker , Party Enemy){
		this.strategy.get(strategyNumber).Strategy(attacker , Enemy);
	}


	// ダメージ処理完了済みのターゲットを呼び出してフィールドへ代入
	public void Processed(){
		processedPlayer = strategy.get(strategyNumber).ProcessedDamage();
	}


	// ダメージ処理完了済みのプレイヤーを返す
	public Player ProcessedPlayer(){
		return this.processedPlayer;
	}


	// 作戦④専用変数のカウント+１
	public void countUp(){
		attackCount++;
	}


	// 作戦④専用変数を取得する
	public int countGet(){
		return this.attackCount;
	}


	// 作戦④専用変数を(引数で「0」を受け取って)リセットする
	public void countSet(int reset){
		attackCount = reset;
	}

}
