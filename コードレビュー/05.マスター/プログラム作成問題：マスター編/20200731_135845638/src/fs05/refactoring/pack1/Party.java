package fs05.refactoring.pack1;

//【パーティークラス】_プレイヤーをパーティー単位で管理するクラス

import fs05.refactoring.pack2.Player;
import fs05.refactoring.pack4.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;

	// [作戦リスト][作戦番号][ダメージ処理完了済みプレイヤー]のフィールド
	private ArrayList<StrategyDraft> strategy = new ArrayList<StrategyDraft>();
	private int strategyNumber;
	private Player processedPlayer;

	// パーティー参加枠
	private final int SLOTS;
	// 作戦選択用変数
	String strategySelection;
	// 作戦④専用変数
	private int attackCount;

	Scanner stdin = new Scanner(System.in);

	// =======================
	// コンストラクタ
	// =======================
	public Party() {
		members = new ArrayList<Player>();

		// 作戦リスト
		strategy.add(new Strategy1("\"敵をミンチにするだけの簡単なオシゴト\""));
		strategy.add(new Strategy2("\"急で悪いんだけどコレ、なる早でよろしく(ﾟ∀ﾟ)\""));
		strategy.add(new Strategy3(
				"\"..なる早っていつまでですか？タスクが組めないので具体的な納期を提示して下さいこの野郎\""));
		strategy.add(new Strategy4("\"この案件、なんかイイ感じにやっといてねん(*´з`)\""));
		strategy.add(new Strategy5(
				"\"..イイ感じってなんですか？具体的にどうして欲しいんですか？怒っていいですか？怒りますよ\""));

		// 作戦④専用変数の初期化
		attackCount = 0;

		// パーティーの参加人数を決定
		SLOTS = 3;
	}

	// パーティー作成のチュートリアルを表示する
	public void tutorial(String partyName, Player avatar) {
		String[] jobDisplay = avatar.getJobList();
		System.out.print(partyName + "チーム / ");
		System.out.print(SLOTS + "人分のキャラクター名とジョブを入力して下さい。");
		System.out.println(" (ジョブは番号で入力して下さい)\n");
		System.out.println("【ジョブリスト】");
		for (int i = 0; i < jobDisplay.length; i++) {
			System.out.println(" ■ " + (i + 1) + "." + jobDisplay[i]);
		}
	}

	// パーティー参加人数(固定値)を取得する
	public int getSlots() {
		return this.SLOTS;
	}

	// 作戦を選択する
	public void tactics(String partyName, Party party) {
		System.out.println("【作戦リスト】");
		for (int i = 0; i < party.getStrategyList().size(); i++) {
			System.out.println(" " + (i + 1) + (" ")
					+ party.getStrategyList().get(i).getStrategyName());
		}
		System.out.println();
		System.out.print
				(partyName + "チームの作戦を選択（該当する作戦の番号を入力）して下さい：");

		// 作戦を入力してセットする
		int decision = input();
		party.setStrategy(decision - 1);
		System.out.println();
	}

	// 作戦番号を入力する
	public int input() {
		while (true) {
			// 例外処理で入力ミス(数字以外を入力)の際は再入力させる
			try
			{
				strategySelection = stdin.next();
				int choice = Integer.parseInt(strategySelection);
				return choice;
			} catch (NumberFormatException e) {
				System.out.print
						("※作戦の入力内容が不適切です。作戦を再入力して下さい：");
			}
		}
	}

	// パーティーメンバーのステータスを表示する
	public void statusDisplay(String partyName, Party party, String pictogram) {
		System.out.println(partyName + "チーム（作戦："
				+ party.getStrategy().getStrategyName() + "）");
		for (int i = 0; i < party.getMembers().size(); i++) {
			System.out.print(pictogram + " ");
			party.getMembers().get(i).printStatus();
		}
	}

	// パーティーメンバーをArrayListで取得する
	public ArrayList<Player> getMembers() {
		return members;
	}

	// 作戦をArrayListで取得する
	public ArrayList<StrategyDraft> getStrategyList() {
		return strategy;
	}

	// パーティーにプレイヤーを追加する
	// @param player : 追加するプレイヤー
	public void appendPlayer(Player player) {
		members.add(player);
	}

	// パーティーからプレイヤーを離脱させる
	// @param player : 離脱させるプレイヤー
	public void removePlayer(Player player) {
		members.remove(player);
	}

	// 番号を振って作戦を決定
	public void setStrategy(int number) {
		while (true) {
			// 例外処理で入力ミス(範囲外の数字を入力)の際は再入力させる
			try
			{
				this.strategyNumber = number;
				// getStrategy()メソッドを呼び出して、変数「strategyNumber」の中身がリストの範囲内かどうかをチェック
				getStrategy();
				break;
			} catch (IndexOutOfBoundsException e) {
				System.out.print
						("※作戦の入力番号がリストに存在しません。作戦を再入力して下さい：");
				number = input() - 1;
			}
		}
	}

	// 決定した作戦を取得する
	public StrategyDraft getStrategy() {
		return strategy.get(strategyNumber);
	}

	// 決定した作戦を実行
	public void operation(Player attacker, Party enemy) {
		this.strategy.get(strategyNumber).strategy(attacker, enemy);
	}

	// ダメージ処理完了済みのターゲットを呼び出してフィールドへ代入
	public void processed() {
		processedPlayer = strategy.get(strategyNumber).processedDamage();
	}

	// ダメージ処理完了済みのプレイヤーを返す
	public Player processedPlayer() {
		return this.processedPlayer;
	}

	// 作戦④専用変数のカウント+１
	public void countUp() {
		attackCount++;
	}

	// 作戦④専用変数を取得する
	public int getCount() {
		return this.attackCount;
	}

	// 作戦④専用変数を(引数で「0」を受け取って)リセットする
	public void setCount(int reset) {
		attackCount = reset;
	}

}