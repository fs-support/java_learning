package namebatoler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================

	// 職業
	//戦士：１、魔法使い：２、僧侶：３、勇者：４
	protected int job;
	//パーティー情報を保存
	//パーティー1なら「1」を、パーティ2なら「2」を保存
	protected int partynum;
	// 名前
	protected String name;
	// HP
	protected int hp;
	//HPの最大値
	protected int Max_hp;
	// MP
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// 幸運値
	protected int luk;
	// 素早さ
	protected int agi;
	//状態異常を保存
	//麻痺なら「1」毒なら「2」
	protected int condition;
	

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 *
	 * @param name
	 *            : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		MakeCharacter();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 *
	 * @return プレイヤー名
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 *
	 * @return 現在HP
	 */
	public int GetHP() {
		return this.hp;
	}
	
	/**
	 * HPの最大値を取得する
	 *
	 * @return 最大HP
	 */
	public int GetMax_HP() {
		return this.Max_hp;
	}

	/**
	 * 現在MPを取得する
	 *
	 * @return 現在MP
	 */
	public int GetMP() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 *
	 * @return 攻撃力
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 *
	 * @return 防御力
	 */
	public int GetDEF() {
		return this.def;
	}

	/**
	 * 幸運値を取得する
	 *
	 * @return 幸運値
	 */
	public int GetLUK() {
		return this.luk;
	}

	/**
	 * 素早さを取得する
	 *
	 * @return 素早さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 状態異常を取得する
	 *
	 * @return 状態異常
	 */
	public int Getcondition() {
		return this.condition;
	}

	/**
	 * 職業を取得する
	 *
	 * @return 職業
	 */
	public int GetJOB() {
		return this.job;
	}

	/**
	 * パーティ情報を取得する
	 *
	 * @return パーティNO
	 */
	public int GetPartyNum() {
		return this.partynum;
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
	 *
	 * @param index
	 *            : 何番目の数値を取り出すか
	 * @param max
	 *            : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int GetNumber(int index, int max) {
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
		if (this.partynum == 1) {
			System.out.println("パーティ1");

		} else {
			System.out.println("パーティ2");

		}

		System.out.printf(
				"%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUK=%3d : AGI=%3d)\n",
				this.GetName(), this.GetHP(), this.GetMP(), this.GetSTR(), this.GetDEF(),
				this.GetLUK(), this.GetAGI());
	}

	// 攻撃を受けるプレイヤーを決める

	public Player Choosedefender(Player atackplayer,Operation choiseoperation) {
		
		Player choosedefender = choiseoperation.Choosedefender(atackplayer);

		return choosedefender;
	}
	
	

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */

	public void Attack(Player atackplayer,Operation choiseoperation,int trunNumber) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 *
	 * @param target
	 *            : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		Random rand = new Random();
		int luk = rand.nextInt(100);
		int damage = 0;
		if (luk < target.GetLUK()) {
			damage = GetSTR();
			System.out.println("会心の一撃！\n");
		} else {
			damage = GetSTR() - target.GetDEF();
			if (damage < 0) {
				damage = 0;
			}
		}
		return damage;
	}

	/**
	 * 対象プレイヤー(target)が状態異常（麻痺）の時の処理
	 *
	 * @param mine
	 *            : 対象プレイヤー
	 */
	protected void Condition(Player mine) {
		System.out.println(mine.GetName() + "は麻痺して動けない！");
		mine.condition = 0;
	}
	
	/**
	 * 対象プレイヤー(target)が状態異常（毒）の時の処理
	 *
	 * @param mine
	 *            : 対象プレイヤー
	 */
	//毒になる最大ターン
	int Max_poizunturn = 4;
	//毒がにかかってからのターン数
	int poizunturn = 0;
	public void PoizunCondition(Player mine) {
		if(poizunturn < Max_poizunturn) {
			mine.hp -= 20;
			System.out.println(mine.GetName() + "は毒を受けている（HP－20）\n");
			poizunturn++;
			
		}else if(poizunturn == Max_poizunturn) {
			System.out.println("毒の効果はなくなった\n");
			poizunturn = 0;
			mine.condition = 0;
		}
	}
	
	

	/**
	 * ダメージを受ける
	 *
	 * @param damage
	 *            : ダメージ値
	 */
	protected void Damage(int damage,Player defender,Player atacker) {
		// ダメージ値分、HPを減少させる
		//戦士:1→僧侶:3,魔法使い:2→戦士:1,僧侶:3→魔法使い:2：ダメージ値1.5倍
		//勇者:4は与えるダメージは1.5倍、受けるダメージは1.25倍
		int Max_damage = damage;
		
		System.out.println("元々のダメージ：" + Max_damage);
		switch(atacker.job){
		case 1:
			if(defender.job == 3){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.5;
			}else if(defender.job == 4){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.25;
			}
			break;
		case 2:
			if(defender.job == 1){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.5;
			}else if(defender.job == 4){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.25;
			}
			break;
		case 3:
			if(defender.job == 2){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.5;
			}else if(defender.job == 4){
				System.out.println("相性によりダメージ増加");
				Max_damage *= 1.25;
			}
			break;
		case 4:
			System.out.println("相性によりダメージ増加");
			Max_damage *= 1.5;
		}
		System.out.println(defender.GetName() + "に" + Max_damage + "のダメージ！\n");
		this.hp = Math.max(this.GetHP() - Max_damage, 0);
	}

	//MPを減少させる
	protected void UseMazicpoint(int usemp) {
		this.mp = Math.max(this.GetMP() - usemp, 0);
	}

	//HPが0になったプレイヤーをパーティから抜く
		protected void RemoveParty(Player defenceplayer){
			
			if (defenceplayer.hp <= 0) {
			System.out.println(defenceplayer.name + "は敗北した！");

			if (defenceplayer.partynum == 1) {
				GameManager.makeparty.RemovePlayer(defenceplayer);
			} else {
				GameManager.makeparty2.RemovePlayer(defenceplayer);
			}

		}
			
		}
	
}