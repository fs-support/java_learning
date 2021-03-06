package ネームバトラー;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;

	protected int luck;

	protected int agi;

	protected int mp;
	
	protected int maxHP;

	protected Party party;
	
	protected int conditoin=0;
	// 0正常　1毒　2麻痺 3毒と麻痺
	
	
	protected List<Magic> magicList=new ArrayList<Magic>();

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * 
	 * @param name : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		MakeCharacter();
		SetMaxHP(this.hp);
	}

	// =======================
	// Getter / Setter
	// =======================
	
	public void SetCondition(int a) {
		this.conditoin=a;
	}
	
	public int GetCondition() {
		return this.conditoin;
	}
	
	public void SetHP(int a) {
		this.hp=a;
	}
	
	public int GetMaxHP() {
		return this.maxHP;
	}
	
	public void SetMaxHP(int hp) {
		this.maxHP=hp;
	}
	
	public int GetAGI() {
		return this.agi;
	}
	
	public int GetMP() {
		return this.mp;
	}
	
	public void SetMP(int useMP) {
		this.mp=useMP;
	}

	public int GetLUCK() {
		return this.luck;
	}
	
	public Party GetParty() {
		return this.party;
	}

	public void SetParty(Party p) {
		this.party = p;
	}

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
	 * @param index : 何番目の数値を取り出すか
	 * @param max   : 最大値(内部的に0〜255の値を生成するが、0〜maxまでの値に補正)
	 * @return 数値(0〜max) ※maxも含む
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
		System.out.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : AGI=%3d : LUCK=%3d)\n", this.GetName(), this.GetHP(),this.GetMP(), this.GetSTR(),
				this.GetDEF(),this.GetAGI(),this.GetLUCK());
	}

	public void Action1(Player attacker, Party enemy) {

	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(Player defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}
	public void Magic(Player attacker,Player defender) {
		
	}
	// a  相性
	public boolean goodAisyou(Player player) {
		return true;
	}
	public boolean badAisyou(Player player) {
		return true;
	}
	
	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * 
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0〜)
	 */
	protected int CalcDamage(Player target) {
		int damage=0;
		if(Clit()) {
			damage=GetSTR();
			System.out.println("会心の一撃");
		}
		else {
		 damage = GetSTR() - target.GetDEF();
		if (damage < 0) {
			damage = 0;
		}
		}
		if(goodAisyou(target)) {
			System.out.println("効果抜群");
			damage=damage*2;
		}
		else if(badAisyou(target)) {
			System.out.println("効果はいまひとつ");
			damage=damage/2;
		}
		
		return damage;
		
	}

	/**
	 * ダメージを受ける
	 * 
	 * @param damage : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		
		this.hp = Math.max(this.GetHP() - damage, 0);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	protected boolean Clit() {
		Random ra = new Random();
		int random = ra.nextInt(100);
		if (random <= this.luck) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean aaction(Player attacker) {
		int a=GetCondition();
		if(a==1||a==3) {
			Poisn.Poi(attacker);
			if(GetHP()<=0) {
				GetName();
				return false;
			}
		}	
		if(a==2||a==3) {
			if(Paralysis.Para(attacker)) {
			}
			else {
				return false;
			}
		}
		return true;
	}
}