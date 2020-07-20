package fs05.refactoring;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// 【プレイヤークラス(各種ジョブの基底クラス)】

public class Player {

	// フィールド変数
	protected String name; // 名前
	protected int hp; // HP
	protected int str; // 攻撃力
	protected int def; // 防御力
	protected int luck; // 運
	protected int agi; // 素早さ
	protected int mp; // MP

	protected final int MAX_HP; // ヒールの使用条件用＋回復値の上限設定用

	protected int abnormalStateParalysis; // 状態回復(麻痺)のカウント用
	protected int abnormalStatePoison; // 状態回復(毒)のカウント用
	protected boolean statusParalysis; // 状態異常(麻痺)判定用
	protected boolean statusPoison; // 状態異常(毒)判定用
	protected int statusClear; // 状態以上回復用の乱数用

	protected Party myParty; // 所属パーティー
	protected Party enemyParty; // 敵パーティー

	protected String job; // プレイヤージョブ
	String[] jobList = { "戦士", "魔法使い", "僧侶", "母ちゃん" };

	Scanner stdin = new Scanner(System.in);
	Random random = new Random();

	// 追加機能用リスト(ExtraFunction型)
	protected ArrayList<ExtraFunction> extra = new ArrayList<ExtraFunction>();

	// コンストラクタ
	public Player(String playerName) {
		this.name = playerName;
		makeCharacter(); // キャラクターのパラメータ生成

		MAX_HP = this.hp;
		this.abnormalStateParalysis = 0;
		this.abnormalStatePoison = 0;
		this.statusParalysis = false;
		this.statusPoison = false;

		extra.add(new InstantDeathAttack());
	}

	// キャラクターの命名
	public void playerNaming(String pictogram, int lap) {
		System.out.print(pictogram + "名前(" + (lap + 1) + "人目)：");
		this.name = stdin.next();
	}

	// ジョブを選択する
	public void playerEmployment(String pictogram, int lap) {
		System.out.print(pictogram + "ジョブ(" + (lap + 1) + "人目)：");
		// 例外処理と範囲判定のwhile文で入力ミスの際は再入力させる
		while (true) {
			try
			{
				this.job = stdin.next();
				int select = Integer.parseInt(job);
				while (select < 1 || 4 < select) {
					System.out.print
							("※ジョブの入力番号がリストに存在しません。ジョブを再入力して下さい：");
					this.job = stdin.next();
					select = Integer.parseInt(job);
				}
				break;
			} catch (NumberFormatException e) {
				System.out.print
						("※ジョブの入力内容が不適切です。ジョブを再入力して下さい：");
			}
		}
	}

	// ジョブリストを取得する
	public String[] getJobList() {
		return this.jobList;
	}

	// ジョブを取得する
	public String getJob() {
		return this.job;
	}

	// プレイヤー名を取得する
	public String getName() {
		return this.name;
	}

	// 現在HPを取得する
	public int getHp() {
		return this.hp;
	}

	// 攻撃力を取得する
	public int getStr() {
		return this.str;
	}

	// 防御力を取得する
	public int getDef() {
		return this.def;
	}

	// 運を取得する
	public int getLuck() {
		return this.luck;
	}

	// 素早さを取得する
	public int getAgi() {
		return this.agi;
	}

	// 現在MPを取得する
	public int getMp() {
		return this.mp;
	}

	// 最大HPを取得する
	public int getMaxHp() {
		return this.MAX_HP;
	}

	// 麻痺の判定
	public boolean isGetStatusParalysis() {
		return this.statusParalysis;
	}

	// 毒の判定
	public boolean isGetStatusPoison() {
		return this.statusPoison;
	}

	// 麻痺解除
	public void setStatusClearParalysis(int clear) {
		this.abnormalStateParalysis = clear;
	}

	// 毒解除
	public void setStatusClearPoison(int clear) {
		this.abnormalStatePoison = clear;
	}

	// 所属パーティー、敵パーティーを決定
	public void setPartyInformation(Party allyPlayer, Party enemyPlayer) {
		this.myParty = allyPlayer;
		this.enemyParty = enemyPlayer;
	}

	// 所属パーティー情報を取得
	public Party getMyPartyInformation() {
		return this.myParty;
	}

	// 敵パーティー情報を取得
	public Party getEnemyPartyInformation() {
		return this.enemyParty;
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	protected void makeCharacter() {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int getNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);
			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}

	// 現在のステータスを System.out で表示する
	public void printStatus() {
		System.out
				.printf
				("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",
						this.getName(), this.getHp(), this.getMp(),
						this.getStr(), this.getDef(), this.getLuck(),
						this.getAgi());
	}

	// attackerの攻撃前に現在のステータス異常状態を判定
	public void condition(Player defender) {
		// 麻痺回復の処理
		paralysisClear();
		// 状態異常になっていない場合（this.isGetStatusParalysis()==false）..ペナルティ無しでそのまま攻撃
		if (!isGetStatusParalysis()) {
			this.attack(defender);
		}
		// 毒ダメージの処理
		poisonDamage();
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	public void attack(Player defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	// 対象プレイヤー(target)に対して与えるダメージを計算する
	// @param target : 対象プレイヤー
	// @return ダメージ値(0～)
	protected int calcDamage(Player target) {
		int damage = 0;
		if (target.getLuck() < this.luck) {
			damage = this.getStr();
		} else {
			damage = this.getStr() - target.getDef();
		}

		if (damage < 0) {
			damage = 0;
		}
		return damage;
	}

	// ダメージを受ける
	// @param damage : ダメージ値
	protected void damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.getHp() - damage, 0);
	}

	// 麻痺回復の処理
	public void paralysisClear() {
		// 状態異常(麻痺)になっていた場合 ※this.isGetStatusParalysis()==true
		if (this.isGetStatusParalysis()) {
			statusClear = random.nextInt(100) + 1;
			// 麻痺になったプレイヤーは２回分行動不能（３回目以降は確率５０%で麻痺から回復）
			if (2 <= this.abnormalStateParalysis && 50 <= statusClear) {
				this.statusParalysis = false;
				this.setStatusClearParalysis(0);
				System.out.println
						(this.name + "は麻痺から回復した");
			} else {
				System.out.println
						(this.name + "は麻痺で動けない");
				this.abnormalStateParalysis++;
			}
		}
	}

	// 毒ダメージの処理
	public void poisonDamage() {
		// 状態異常(毒)になっていた場合 ※this.isGetStatusPoison()==true
		if (this.isGetStatusPoison()) {
			// 毒になったプレイヤーは２回分行動する間、１回の行動につき２０ダメージ（３回目で自然回復）
			if (2 <= this.abnormalStatePoison) {
				this.statusPoison = false;
				this.setStatusClearPoison(0);
				System.out.println
						(this.getName() + "の身体から毒が抜けた");
			} else {
				System.out.println
						(this.getName() + "は毒により20のダメージを受けた");
				this.hp -= 20;
				this.abnormalStatePoison++;
			}
		}
	}

}