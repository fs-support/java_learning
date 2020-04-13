package fs05_Master_ProgramCreation;

//【パーティークラス】_プレイヤーをパーティー単位で管理するクラス
import java.util.ArrayList;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;


	// 課題４で追加_
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	// [作戦リスト][作戦番号][ダメージ処理完了済みプレイヤー]のフィールド
	private ArrayList<StrategyDraft> strategy = new ArrayList<StrategyDraft>();
	private int strategyNumber;
	private Player processedPlayer;
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


	// =======================
	// コンストラクタ
	// =======================
	Party() {
		members = new ArrayList<Player>();

		// 課題４で追加
		// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		strategy.add(new Strategy1("\"敵をミンチにするだけの簡単なオシゴト\""));
		// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	}


	// =======================
	// Getter / Setter
	// =======================
	/**
	 * パーティーメンバーをArrayList で取得する
	 */
	ArrayList<Player> GetMembers() {
		return members;
	}


	// 課題４で追加
	/**
	 * 作戦をArrayList で取得する
	 */
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	ArrayList<StrategyDraft> GetStrategyList(){
		return strategy;
	}
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


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
	 * パーティーにプレイヤーを追加する
	 * @param player : 追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {
		members.add(player);
	}


	/**
	 * パーティーからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemovePlayer(Player player) {
		members.remove(player);
	}


	// 課題４で追加
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
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
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

}
