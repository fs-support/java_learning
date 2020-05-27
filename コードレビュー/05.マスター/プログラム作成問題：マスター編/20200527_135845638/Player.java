package fs05_Master_Refactoring;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

// 【プレイヤークラス(各種ジョブの基底クラス)】

public class Player {

	// フィールド変数
	protected String name;	// 名前
	protected int hp;		// HP
	protected int str;		// 攻撃力
	protected int def;		// 防御力
	protected int luck;		// 運
	protected int agi;		// 素早さ
	protected int mp;		// MP

	final int maxhp;		// ヒールの使用条件用＋回復値の上限設定用

	protected int AbnormalState_paralysis;	// 状態回復(麻痺)のカウント用
	protected int AbnormalState_poison;		// 状態回復(毒)のカウント用
	protected boolean Status_paralysis;		// 状態異常(麻痺)判定用
	protected boolean Status_poison;		// 状態異常(毒)判定用
	int Status_Clear;						// 状態以上回復用の乱数用

	protected Party MyParty;		// 所属パーティー
	protected Party EnemyParty;		// 敵パーティー

	Random random = new Random();

	// 追加機能用リスト(ExtraFunction型)
	protected ArrayList<ExtraFunction> extra = new ArrayList<ExtraFunction>();


	// コンストラクタ
	public Player(String name){
		this.name = name;
		MakeCharacter();	// キャラクターのパラメータ生成

		maxhp = this.hp;
		this.AbnormalState_paralysis = 0;
		this.AbnormalState_poison = 0;
		this.Status_paralysis = false;
		this.Status_poison = false;

		extra.add(new InstantDeathAttack());
	}


	// プレイヤー名を取得する
	public String GetName(){
		return this.name;
	}

	// 現在HPを取得する
	public int GetHP(){
		return this.hp;
	}

	// 攻撃力を取得する
	public int GetSTR(){
		return this.str;
	}

	// 防御力を取得する
	public int GetDEF(){
		return this.def;
	}

	// 運を取得する
	public int GetLUCK(){
		return this.luck;
	}

	// 素早さを取得する
	public int GetAGI(){
		return this.agi;
	}

	// 現在MPを取得する
	public int GetMP(){
		return this.mp;
	}

	// 最大HPを取得する
	public int GetMAXHP(){
		return this.maxhp;
	}

	// 麻痺の判定
	public boolean GetStatus_paralysis(){
		return this.Status_paralysis;
	}

	// 毒の判定
	public boolean GetStatus_poison(){
		return this.Status_poison;
	}

	// 麻痺解除
	public void setStatusClear_paralysis(int clear){
		this.AbnormalState_paralysis = clear;
	}

	// 毒解除
	public void setStatusClear_poison(int clear){
		this.AbnormalState_poison = clear;
	}

	// 所属パーティー、敵パーティーを決定
	public void setPartyInformation(Party myparty , Party enemyparty){
		this.MyParty = myparty;
		this.EnemyParty = enemyparty;
	}

	// 所属パーティー情報を取得
	public Party GetMyPartyInformation(){
		return this.MyParty;
	}

	// 敵パーティー情報を取得
	public Party GetEnemyPartyInformation(){
		return this.EnemyParty;
	}


	// 名前(name)からキャラクターに必要なパラメータを生成する
	protected void MakeCharacter(){
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


	// 現在のステータスを System.out で表示する
	public void PrintStatus(){
		System.out.printf
		("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",  this.GetName(), this.GetHP(), this.GetMP(), this.GetSTR(), this.GetDEF(), this.GetLUCK(), this.GetAGI());
	}


	// Attackerの攻撃前に現在のステータス異常状態を判定
	public void Condition(Player defender){

		// 麻痺回復の処理
		paralysisClear();

		// 状態異常になっていない場合..ペナルティ無しでそのまま攻撃
		if(this.GetStatus_paralysis()==false){
			this.Attack(defender);
		}

		// 毒ダメージの処理
		poisonDamage();

	}


	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	public void Attack(Player defender){
		// ジョブごとにオーバーライドして処理を記述してください
	}

	// 対象プレイヤー(target)に対して与えるダメージを計算する
	// @param target : 対象プレイヤー
	// @return ダメージ値(0～)
	protected int CalcDamage(Player target){
		int damage = 0;

		if(this.luck>target.GetLUCK()){
			damage = this.GetSTR();
		} else {
			damage = this.GetSTR() - target.GetDEF();
		}

		if(damage<0){
			damage=0;
		}
		return damage;
	}

	// ダメージを受ける
	// @param damage : ダメージ値
	protected void Damage(int damage){
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0);
	}


	// 麻痺回復の処理
	public void paralysisClear(){
		// 状態異常(麻痺)になっていた場合
		if(this.GetStatus_paralysis()==true){
			Status_Clear = random.nextInt(100)+1;
			// 麻痺になったプレイヤーは２回分行動不能（３回目以降は確率５０%で麻痺から回復）
			if(this.AbnormalState_paralysis>=2 && Status_Clear>=50){
				this.Status_paralysis = false;
				this.setStatusClear_paralysis(0);
				System.out.println(this.name + "は麻痺から回復した");
			} else {
				System.out.println(this.name + "は麻痺で動けない");
				this.AbnormalState_paralysis++;
			}

		}
	}

	// 毒ダメージの処理
	public void poisonDamage(){
		// 状態異常(毒)になっていた場合
		if(this.GetStatus_poison()==true){
			// 毒になったプレイヤーは２回分行動する間、１回の行動につき２０ダメージ（３回目で自然回復）
			if(this.AbnormalState_poison>=2){
				this.Status_poison = false;
				this.setStatusClear_poison(0);
				System.out.println(this.GetName() + "の身体から毒が抜けた");
			} else {
				System.out.println(this.GetName() + "は毒により20のダメージを受けた");
				this.hp-=20;
				this.AbnormalState_poison++;
			}
		}

	}


}
