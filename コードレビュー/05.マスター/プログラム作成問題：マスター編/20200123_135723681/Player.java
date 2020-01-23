package name_battlerM6;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {

	Random rnd = new Random();
	Scanner stdin = new Scanner(System.in);
	Tactics tac = new Tactics();


	HPminAim aim = new HPminAim();
	Support spt = new Support();
	AttackMain atm = new AttackMain();
	MPsaving mps = new MPsaving();
	WellBalanced wlb = new WellBalanced();

	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	// MP
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// 運
	protected int luck;
	//素早さ
	protected int agi;
	// 最大HP
	protected int maxhp;
	//状態
	protected Status status;
	//作戦
	protected Tactics.TacticsType tactics;
	//ガード
	protected Guard guard;
	//所属パーティー
	protected Party party;

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;
		this.status = Status.Nomal;
		this.guard = Guard.NoGuard;

		// キャラクターのパラメータ生成
		MakeCharacter();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 * @return プレイヤー名
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int GetHP() {
		return this.hp;
	}

	/**
	 * 現在MPを取得する
	 * @return 現在HP
	 */
	public int GetMP() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int GetDEF() {
		return this.def;
	}

	/**
	 * 運を取得する
	 * @return 運
	 */
	public int GetLuck() {
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 * @return 素早さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 最大HPを取得する
	 * @return 素早さ
	 */
	public int GetMAXHP() {
		return this.maxhp;
	}

	/**
	 * 現在の状態を取得する
	 * @return 現在の状態
	 */
	public Status GetStatus() {
		return this.status;
	}

	/**
	 * 現在の作戦を取得する
	 * @return 現在の状態
	 */
	public Tactics.TacticsType GetTactics()
	{
		return this.tactics;
	}

	/**
	 * 現在のガード体勢を取得する
	 * @return 現在のガード体勢
	 */
	public Guard GetGuard() {
		return this.guard;
	}

	/**
	 * 現在の所属パーティーを取得する
	 * @return 現在のパーティー
	 */
	public Party GetParty() {
		return this.party;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int GetNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
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

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 現在のステータスを System.out で表示する
	 */
	public void PrintStatus() {
		System.out.printf("%s (HP=%3d: MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d",
				this.GetName(), this.GetHP(), this.GetMP(), this.GetSTR(), this.GetDEF(), this.GetLuck(),
				this.GetAGI());
		//状態異常の場合のみ表示する
		if (!this.GetStatus().equals(Status.Nomal)) {

			System.out.print(" : 状態=" + StatusName(this.GetStatus()));
		}
		System.out.println(")");
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack() {

		// ジョブごとにオーバーライドして処理を記述してください

	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		int STR = rnd.nextInt(GetSTR()) + 1;
		int critical = rnd.nextInt(200);

		int damage = STR - target.GetDEF();

		if (critical < GetLuck()) {

			System.out.println("会心の一撃！");
			damage = STR;
		}
		if (damage < 0) {
			damage = 0;
		}
		return damage;
	}

	/**
	 * ダメージを受ける
	 * @param damage : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0);
	}

	//列挙型を使用して状態異常の定数を保持
	protected enum Status {

		Nomal, Paralysis, Poison
	};

	//列挙型を使用してガード体勢の定数を保持
	protected enum Guard {

		Guard, NoGuard
	};

	//状態異常の表記を変換(ノーマル時は表示しないため変換なし)
	protected String StatusName(Status status) {

		String Sresult = "";

		if (status.equals(Status.Paralysis)) {

			Sresult = "麻痺";
		} else if (status.equals(Status.Poison)) {

			Sresult = "毒";
		}
		return Sresult;
	}

	protected void Paralysis(Player target) {

		System.out.println(GetName() + "はパライズを唱えた！");
		this.mp = GetMP() - 10;
		System.out.println(target.GetName() + "は麻痺状態になってしまった！");
		target.status = Status.Paralysis;
	}

	protected void Poison(Player target) {

		System.out.println(GetName() + "はポイズンを唱えた！");
		this.mp = GetMP() - 10;
		System.out.println(target.GetName() + "は毒状態になってしまった！");
		target.status = Status.Poison;
	}

	//数字を返してその数字毎に処理を分ける /0:通常 1:麻痺行動不能 2:毒
	protected int Status_check() {

		int result = 0;

		if (this.status.equals(Status.Paralysis)) {

			int Paralysis_resuit = rnd.nextInt(4);
			if (Paralysis_resuit == 0) {

				result = 1;
			}
		} else if (this.status.equals(Status.Poison)) {

			result = 2;
		}

		return result;
	}

	//魔法の処理は職業によって共有できるようPlayerクラスで作成
	protected int Fire() {

		System.out.println(GetName() + "はファイアを唱えた！");
		this.mp = GetMP() - 10;
		int damage = rnd.nextInt(21) + 10;

		return damage;
	}

	protected int Thunder() {

		System.out.println(GetName() + "はサンダーを唱えた！");
		this.mp = GetMP() - 20;
		int damage = rnd.nextInt(21) + 10;

		return damage;
	}

	protected void Heal(Player target) {

		System.out.println(GetName() + "はヒールを唱えた！");
		this.mp = GetMP() - 20;
		System.out.println(target.GetName() + "のHPが 50 回復した！");
		target.hp = Math.min(target.GetHP() + 50, this.maxhp);
	}

	protected void PoisonDamage() {

		System.out.println(GetName() + "は毒に冒されている！");
		//今回は毒では体力は1以下にならない仕様を採用
		if (GetHP() == 1) {
			System.out.println(GetName() + "はなんとか持ちこたえた！");
		} else {
			this.hp = Math.max(GetHP() - 20, 1);
			System.out.println("HPが 20 減少した！");
		}
	}

	//確率で状態異常が自然治癒する
	protected void Recovery() {

		int recovery = rnd.nextInt(7);

		if (recovery == 0) {

			if (GetStatus().equals(Status.Paralysis)) {

				System.out.println(GetName() + "の身体の痺れがとれた！");
			} else {

				System.out.println(GetName() + "の身体の毒が治った！");
			}
			this.status = Status.Nomal;
		}
	}


}